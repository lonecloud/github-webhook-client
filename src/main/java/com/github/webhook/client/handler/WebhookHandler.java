package com.github.webhook.client.handler;


import com.github.webhook.client.param.WebhookParam;

/**
 * webHookHandler处理器
 *
 * @author lonecloud
 */
public interface WebhookHandler {

    void handler(WebhookParam param,WebHookHandlerChain chain);
}
