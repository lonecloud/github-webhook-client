package com.github.webhook.client;

import com.alibaba.fastjson.JSON;
import com.github.webhook.client.cts.GlobalCts;
import com.github.webhook.client.enums.ActionEnum;
import com.github.webhook.client.handler.CheckParamHandler;
import com.github.webhook.client.handler.WebHookHandler;
import com.github.webhook.client.handler.def.CheckHeaderHandler;
import com.github.webhook.client.param.WebHookBodyParam;
import com.github.webhook.client.param.WebhookParam;
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
 * @date 2021/11/29 22:06
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
        WebhookParam.WebHookHeaderParam webHookHeaderParam = buildHeaderParam(request);
        webHookHandlerManager.doHandler(WebhookParam.builder()
                .webHookBodyParam(JSON.parseObject(bis.toString(StandardCharsets.UTF_8.name()), WebHookBodyParam.class))
                .webHookClientConfig(clientConfig)
                .webHookHeaderParam(webHookHeaderParam).build());
    }

    private WebhookParam.WebHookHeaderParam buildHeaderParam(HttpServletRequest request) {
        WebhookParam.WebHookHeaderParam.WebHookHeaderParamBuilder rawParamBuilder = WebhookParam.WebHookHeaderParam.builder();
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
