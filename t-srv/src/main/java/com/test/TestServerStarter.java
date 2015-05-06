/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.apex.Router;
import io.vertx.ext.apex.handler.StaticHandler;
import io.vertx.ext.apex.handler.sockjs.BridgeOptions;
import io.vertx.ext.apex.handler.sockjs.PermittedOptions;
import io.vertx.ext.apex.handler.sockjs.SockJSHandler;
import lombok.extern.log4j.Log4j2;

/**
 *
 * @author lzeligowski
 */
@Log4j2
public class TestServerStarter {

    public static void main(String[] args) {
        VertxOptions options = new VertxOptions();
        options.setClustered(true);
        Vertx.clusteredVertx(options, new Handler<AsyncResult<Vertx>>() {

            public void handle(AsyncResult<Vertx> vertxHandler) {
                if (vertxHandler.succeeded()) {
                    Vertx vertx = vertxHandler.result();
                    HttpServer http = vertx.createHttpServer();

                    SockJSHandler sockJS = SockJSHandler.create(vertx);
                    BridgeOptions allAccessOptions = new BridgeOptions().
                            addInboundPermitted(new PermittedOptions(new JsonObject().put("addressRegex", "test.*"))).
                            addOutboundPermitted(new PermittedOptions());
                    sockJS.bridge(allAccessOptions);

                    Router router = Router.router(vertx);
                    router.route("/test/*").handler(StaticHandler.create("webroot"));

                    router.route("/eventbus/*").handler(sockJS);

                    http.requestHandler(router::accept).listen(8080);

                } else {
                    log.error("Error", vertxHandler.cause());
                }
            }
        });
    }

}
