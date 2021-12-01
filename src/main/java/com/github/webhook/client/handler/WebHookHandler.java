package com.github.webhook.client.handler;


import com.github.webhook.client.param.WebhookParam;

/**
 * webHookHandler处理器
 *
 * @author lonecloud
 */
public interface WebHookHandler {
    /**
     * webhook处理器
     * @param param 入参
     */
    void handler(WebhookParam param);
}
