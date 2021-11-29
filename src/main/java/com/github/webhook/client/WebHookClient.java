package com.github.webhook.client;

import com.github.webhook.client.cts.GlobalCts;
import com.github.webhook.client.enums.ActionEnum;
import com.github.webhook.client.handler.WebHookHandlerChain;
import com.github.webhook.client.handler.def.DefaultWebHootHandlerChain;
import com.github.webhook.client.param.WebhookParam;
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Optional;

import static com.github.webhook.client.cts.GlobalCts.USER_AGENT;

/**
 * WenHookClient
 *
 * @author lonecloud
 * @date 2021/11/29 22:06
 */
public class WebHookClient {

    private final WebHookClientConfig clientConfig;

    private final WebHookHandlerChain webHookHandlerChain;

    public WebHookClient() {
        this(WebHookClientConfig.builder().checkSign(false).build());
    }

    public WebHookClient(WebHookClientConfig clientConfig) {
        this.clientConfig = clientConfig;
        this.webHookHandlerChain = new DefaultWebHootHandlerChain();
    }

    public void webhook(HttpServletRequest request) throws IOException {
        ByteArrayOutputStream bis = new ByteArrayOutputStream();
        IOUtils.copy(request.getInputStream(), bis);
        WebhookParam.WebHookHeaderParam webHookHeaderParam = buildHeaderParam(request);
        webHookHandlerChain.doHandler(WebhookParam.builder()
                .data(bis.toString(StandardCharsets.UTF_8.name()))
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
        ;
        return rawParamBuilder.build();
    }
}
