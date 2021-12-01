package com.github.webhook.client.handler.def;

import com.github.webhook.client.handler.WebHookHandlerChain;
import com.github.webhook.client.handler.WebhookHandler;
import com.github.webhook.client.param.WebhookParam;

/**
 * 默认WebHook处理器
 *
 * @author lonecloud
 * @date 2021/11/29 22:02
 */
public class DefaultWebHootHandlerChain implements WebHookHandlerChain {

    private int index = 0;

    private final WebhookHandler[] handlers;

    public DefaultWebHootHandlerChain(WebhookHandler[] handlers) {
        this.handlers = handlers;
    }

    @Override
    public void doHandler(WebhookParam param) {
        if (index < handlers.length) {
            handlers[index++].handler(param, this);
        }
    }

}
