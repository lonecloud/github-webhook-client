package com.github.webhook.client;

import com.github.webhook.client.enums.ActionEnum;
import com.github.webhook.client.handler.WebHookHandler;
import com.github.webhook.client.param.WebHookParam;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * webHookHandler管理器
 *
 * @author lonecloud
 * @version v1.0
 * @date 2021/12/110:26 下午
 */
public class WebHookHandlerManager {

    private Map<ActionEnum, List<WebHookHandler>> handlerMap;


    public WebHookHandlerManager(WebHookHandler[] handlers) {
        handlerMap = new HashMap<>();
        if (handlers == null || handlers.length == 0) {
            return;
        }
        for (WebHookHandler handler : handlers) {
            WebHookAction annotation = handler.getClass().getAnnotation(WebHookAction.class);
            if (null == annotation) {
                return;
            }
            ActionEnum actionEnum = annotation.value();
            List<WebHookHandler> webHookHandlers = handlerMap.computeIfAbsent(actionEnum, k -> new LinkedList<>());
            webHookHandlers.add(handler);
        }
    }

    public void doHandler(WebHookParam webhookParam) {
        WebHookParam.WebHookHeaderParam webHookHeaderParam = webhookParam.getWebHookHeaderParam();
        List<WebHookHandler> webHookHandlers = handlerMap.get(webHookHeaderParam.getAction());
        for (WebHookHandler webhookHandler : webHookHandlers) {
            webhookHandler.handler(webhookParam);
        }
    }
}
