package com.github.webhook.client.handler;


import com.github.webhook.client.param.WebhookParam;

/**
 * webHookHandler处理器
 *
 * @author lonecloud
 */
public interface WebhookHandler {
    /**
     * webhook处理器
     * @param param 入参
     * @param chain 调用链
     */
    void handler(WebhookParam param,WebHookHandlerChain chain);
}
