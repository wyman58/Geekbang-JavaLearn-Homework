package io.kimmking.rpcfx.client;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import io.kimmking.rpcfx.api.*;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.springframework.aop.framework.ProxyFactory;
import sun.net.www.http.HttpClient;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public final class Rpcfx {

    static {
        ParserConfig.getGlobalInstance().addAccept("io.kimmking");
    }

    public static <T, filters> T createFromRegistry(final Class<T> serviceClass, final String zkUrl, Router router, LoadBalancer loadBalance, Filter filter) {

        // 加filte之一

        // curator Provider list from zk
        List<String> invokers = new ArrayList<>();
        // 1. 简单：从zk拿到服务提供的列表
        // 2. 挑战：监听zk的临时节点，根据事件更新这个list（注意，需要做个全局map保持每个服务的提供者List）

        List<String> urls = router.route(invokers);

        String url = loadBalance.select(urls); // router, loadbalance

        return (T) create(serviceClass, url, filter);

    }

    public static <T> T create(final Class<T> serviceClass, final String url, Filter... filters) {

        // 0. 替换动态代理 -> 字节码生成
        //return (T) Proxy.newProxyInstance(Rpcfx.class.getClassLoader(), new Class[]{serviceClass}, new RpcfxInvocationHandler(serviceClass, url, filters));
        return (T) new ProxyFactory(serviceClass, url, filters).getProxyInstance();

    }

    public static class RpcfxInvocationHandler implements InvocationHandler {

        public static final MediaType JSONTYPE = MediaType.get("application/json; charset=utf-8");

        private final Class<?> serviceClass;
        private final String url;
        private final Filter[] filters;

        public <T> RpcfxInvocationHandler(Class<T> serviceClass, String url, Filter... filters) {
            this.serviceClass = serviceClass;
            this.url = url;
            this.filters = filters;
        }

        // 可以尝试，自己去写对象序列化，二进制还是文本的，，，rpcfx是xml自定义序列化、反序列化，json: code.google.com/p/rpcfx
        // int byte char float double long bool
        // [], data class

        @Override
        public Object invoke(Object proxy, Method method, Object[] params) throws Throwable {

            // 加filter地方之二
            // mock == true, new Student("hubao");

            RpcfxRequest request = new RpcfxRequest();
            request.setServiceClass(this.serviceClass.getName());
            request.setMethod(method.getName());
            request.setParams(params);

            if (null!=filters) {
                for (Filter filter : filters) {
                    if (!filter.filter(request)) {
                        return null;
                    }
                }
            }

            RpcfxResponse response = post(request, url);

            // 加filter地方之三
            // Student.setTeacher("cuijing");

            // 这里判断response.status，处理异常
            // 考虑封装一个全局的RpcfxException

            return JSON.parse(response.getResult().toString());
        }

        private RpcfxResponse post(RpcfxRequest req, String url) throws IOException {
            String reqJson = JSON.toJSONString(req);
            System.out.println("req json: "+reqJson);

            // 1.可以复用client
            // 2.尝试使用httpclient或者netty client
            OkHttpClient client = new OkHttpClient();
            final Request request = new Request.Builder()
                    .url(url)
                    .post(RequestBody.create(JSONTYPE, reqJson))
                    .build();
            String respJson = client.newCall(request).execute().body().string();
            System.out.println("resp json: "+respJson);
            return JSON.parseObject(respJson, RpcfxResponse.class);
        }
    }

    public static class ProxyFactory implements MethodInterceptor{

        public static final MediaType JSONTYPE = MediaType.get("application/json; charset=utf-8");

        private final Class target;
        private final String url;
        private final Filter[] filters;

        public ProxyFactory(Class target, String url, Filter[] filters){
            this.target = target;
            this.url = url;
            this.filters = filters;
        }

        public Object getProxyInstance(){
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(target);
            enhancer.setCallback(this);
            return enhancer.create();
        }

        @Override
        public Object intercept(Object o, Method method, Object[] params, MethodProxy methodProxy) throws Throwable {
            //Object returnValue = method.invoke(target, params);
            //return returnValue;

            RpcfxRequest request = new RpcfxRequest();
            request.setServiceClass(this.target.getName());
            request.setMethod(method.getName());
            request.setParams(params);

            if (null!=filters) {
                for (Filter filter : filters) {
                    if (!filter.filter(request)) {
                        return null;
                    }
                }
            }
            log.info("Implement by CGLib, before invoking Remote ");
            RpcfxResponse response = post(request, this.url);

            // 加filter地方之三
            // Student.setTeacher("cuijing");

            // 这里判断response.status，处理异常
            // 考虑封装一个全局的RpcfxException

            return JSON.parse(response.getResult().toString());

        }

        private RpcfxResponse post(RpcfxRequest req, String url) throws IOException, Exception {
            String reqJson = JSON.toJSONString(req);
            System.out.println("req json: "+reqJson);

            // 1.可以复用client
            // 2.尝试使用httpclient或者netty client
            //OkHttpClient client = new OkHttpClient();
            HttpClient client = new HttpClient();

//            final Request request = new Request.Builder()
//                    .url(url)
//                    .post(RequestBody.create(JSONTYPE, reqJson))
//                    .build();
            client.connect(url, 8080, reqJson);

            //String respJson = client.newCall(request).execute().body().string();
            //System.out.println("resp json: "+respJson);
            return JSON.parseObject("Response body test", RpcfxResponse.class);

        }
    }

    public static class HttpClient {
        public void connect(String host, int port, String msg) throws Exception {
            EventLoopGroup workerGroup = new NioEventLoopGroup();

            try {
                Bootstrap b = new Bootstrap();
                b.group(workerGroup);
                b.channel(NioSocketChannel.class);
                b.option(ChannelOption.SO_KEEPALIVE, true);
                b.handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        // 客户端接收到的是httpResponse响应，所以要使用HttpResponseDecoder进行解码
                        ch.pipeline().addLast(new HttpResponseDecoder());
                        // 客户端发送的是httprequest，所以要使用HttpRequestEncoder进行编码
                        ch.pipeline().addLast(new HttpRequestEncoder());
                        ch.pipeline().addLast(new HttpClientInboundHandler());
                    }
                });

                // Start the client.
                ChannelFuture f = b.connect(host, port).sync();

                URI uri = new URI("http://127.0.0.1:8080");
                //String msg = "Are you ok?";
                DefaultFullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.POST,
                        uri.toASCIIString(), Unpooled.wrappedBuffer(msg.getBytes("UTF-8")));

                // 构建http请求
                request.headers().set(HttpHeaders.Names.HOST, host);
                request.headers().set(HttpHeaders.Names.CONNECTION, HttpHeaders.Values.KEEP_ALIVE);
                request.headers().set(HttpHeaders.Names.CONTENT_LENGTH, request.content().readableBytes());
                request.headers().set(HttpHeaders.Names.CONTENT_TYPE, "application/json");
                // 发送http请求
                f.channel().write(request);
                f.channel().flush();
                f.channel().closeFuture().sync();
            } finally {
                workerGroup.shutdownGracefully();
            }

        }
    }

    public static class HttpClientInboundHandler extends ChannelInboundHandlerAdapter {

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            if (msg instanceof HttpResponse)
            {
                HttpResponse response = (HttpResponse) msg;
                System.out.println("CONTENT_TYPE:" + response.headers().get(HttpHeaders.Names.CONTENT_TYPE));
            }
            if(msg instanceof HttpContent)
            {
                HttpContent content = (HttpContent)msg;
                ByteBuf buf = content.content();
                System.out.println(buf.toString(io.netty.util.CharsetUtil.UTF_8));
                buf.release();
            }
        }
    }
}
