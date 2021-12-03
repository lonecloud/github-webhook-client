/*
 * Copyright © ${project.inceptionYear} organization lonecloud
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
