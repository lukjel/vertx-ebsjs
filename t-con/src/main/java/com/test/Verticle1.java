/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test;

import io.vertx.core.AbstractVerticle;

/**
 *
 * @author lzeligowski
 */
public class Verticle1 extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        vertx.eventBus().consumer("test.test", m -> {
            // Just ECHO
            m.reply(m.body());
        });
    }

}
