package com.github.webhook.client.handler;


import com.github.webhook.client.param.WebhookParam;

/**
 * WebHook链式调用处理器
 *
 * @author lonecloud
 * @date 2021/11/29 21:59
 */
public interface WebHookHandlerChain {
    /**
     * 处理Handler
     * @param param
     */
    void doHandler(WebhookParam param);
}
