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

import com.alibaba.fastjson.JSON;
import com.github.webhook.client.cts.GlobalCts;
import com.github.webhook.client.enums.ActionEnum;
import com.github.webhook.client.handler.CheckParamHandler;
import com.github.webhook.client.handler.WebHookHandler;
import com.github.webhook.client.handler.def.CheckHeaderHandler;
import com.github.webhook.client.param.WebHookBodyParam;
import com.github.webhook.client.param.WebHookParam;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static com.github.webhook.client.cts.GlobalCts.USER_AGENT;

/**
 * WenHookClient
 *
 * @author lonecloud
 */
public class WebHookClient {

    private final WebHookClientConfig clientConfig;

    private final WebHookHandlerManager webHookHandlerManager;

    private final CheckParamHandler checkParamHandler;


    public WebHookClient(WebHookClientConfig clientConfig, WebHookHandlerManager webHookHandlerManager) {
        this.clientConfig = clientConfig;
        this.webHookHandlerManager = webHookHandlerManager;
        this.checkParamHandler = new CheckHeaderHandler();
    }

    public WebHookClient(WebHookClientConfig clientConfig, WebHookHandler[] webHookHandlers, CheckParamHandler checkParamHandler) {
        this.clientConfig = clientConfig;
        this.webHookHandlerManager = new WebHookHandlerManager(webHookHandlers);
        this.checkParamHandler = checkParamHandler;
    }


    public void hook(HttpServletRequest request) throws IOException {
        ByteArrayOutputStream bis = new ByteArrayOutputStream();
        IOUtils.copy(request.getInputStream(), bis);
        WebHookParam.WebHookHeaderParam webHookHeaderParam = buildHeaderParam(request);
        webHookHandlerManager.doHandler(WebHookParam.builder()
                .webHookBodyParam(JSON.parseObject(bis.toString(StandardCharsets.UTF_8.name()), WebHookBodyParam.class))
                .webHookClientConfig(clientConfig)
                .webHookHeaderParam(webHookHeaderParam).build());
    }

    private WebHookParam.WebHookHeaderParam buildHeaderParam(HttpServletRequest request) {
        WebHookParam.WebHookHeaderParam.WebHookHeaderParamBuilder rawParamBuilder = WebHookParam.WebHookHeaderParam.builder();
        rawParamBuilder.appVersion(request.getHeader(GlobalCts.HEADER_NAME_GITHUB_VERSION))
                .hookId(request.getHeader(GlobalCts.HEADER_NAME_HOOK_ID))
                .targetId(request.getHeader(GlobalCts.HEADER_NAME_TARGET_ID))
                .targetType(request.getHeader(GlobalCts.HEADER_NAME_TARGET_TYPE))
                .action(ActionEnum.getEnum(request.getHeader(GlobalCts.HEADER_NAME_Event)))
                .contentType(request.getContentType())
                .userAgent(request.getHeader(USER_AGENT))
                .signature(request.getHeader(GlobalCts.HEADER_NAME_SIGN))
                .signature256(request.getHeader(GlobalCts.HEADER_NAME_SIGN_256))
        ;
        return rawParamBuilder.build();
    }
}
