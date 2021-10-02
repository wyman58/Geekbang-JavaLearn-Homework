package io.github.kimmking.gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

import java.util.Optional;
import java.util.function.Consumer;

public class AuthenticationRequestFilter implements HttpRequestFilter{

    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        Optional header = Optional.ofNullable(fullRequest.headers().get("Authentication"));
        header.ifPresent(new Consumer() {
            @Override
            public void accept(Object o) {
                System.out.println("Authentication Passed");
            }
        });

    }
}
