package io.github.kimmking.gateway.outbound.okhttp;

import io.github.kimmking.gateway.filter.HeaderHttpResponseFilter;
import io.github.kimmking.gateway.filter.HttpRequestFilter;
import io.github.kimmking.gateway.filter.HttpResponseFilter;
import io.github.kimmking.gateway.outbound.httpclient4.NamedThreadFactory;
import io.github.kimmking.gateway.router.HttpEndpointRouter;
import io.github.kimmking.gateway.router.RandomHttpEndpointRouter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class OkhttpOutboundHandler {

    private List<String> backendList;
    private ExecutorService proxyService;
    private OkHttpClient client;

    HttpResponseFilter responseFilter = new HeaderHttpResponseFilter();
    HttpEndpointRouter router = new RandomHttpEndpointRouter();

    public OkhttpOutboundHandler(List<String> urlList){
        backendList = urlList.stream().map(this::formatUrl).collect(Collectors.toList());

        //Create Thread Pool Here
        int cores = Runtime.getRuntime().availableProcessors();
        System.out.println("Available Processor: " + cores);
        long keepAliveTime = 1000;
        int queueSize = 2048;
        RejectedExecutionHandler rejectionPolicy = new ThreadPoolExecutor.CallerRunsPolicy();//.DiscardPolicy();
        proxyService = new ThreadPoolExecutor(cores, cores,
                keepAliveTime, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(queueSize),
                new NamedThreadFactory("proxyService"), rejectionPolicy);

        //create OkHttpClient
        client = new OkHttpClient();
    }

    private String formatUrl(String backend) {
        return backend.endsWith("/")?backend.substring(0,backend.length()-1):backend;
    }

    public void handle(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx, HttpRequestFilter requestFilter) {
        String backendUrl = router.route(this.backendList);
        final String url = backendUrl + fullRequest.uri();
        System.out.println("Will call: " + url);
        requestFilter.filter(fullRequest, ctx);
        //proxyService.submit(()->fetchGet(fullRequest, ctx, url));

        Request request = new Request.Builder()
                .url(url)
                .build();

        proxyService.submit(
                () -> client.newCall(request).enqueue(new Callback() {

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                        Headers responseHeaders = response.headers();
                        for (int i = 0; i < responseHeaders.size(); i++) {
                            System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                        }

                        System.out.println("Get Response Body from Business: " + response.body().string());
                    }

                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        e.printStackTrace();
                    }
                }));
    }
}
