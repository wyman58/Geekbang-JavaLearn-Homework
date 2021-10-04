package io.github.kimmking.gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

import java.util.Optional;
import java.util.function.Consumer;

public class AuthenticationRequestFilter implements HttpRequestFilter{

    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        Optional.ofNullable(fullRequest.headers().get("Authentication")).ifPresent(new Consumer() {
            @Override
            public void accept(Object o) {
                if(o.equals("Password")) {
                    System.out.println("Authentication Passed");
                } else {
                    System.out.println("Authentication Failed");
                }
            }
        });

    }
}
