package com.github.webhook.client.handler.def;

import com.github.webhook.client.WebHookAction;
import com.github.webhook.client.expection.GithubWebHookException;
import com.github.webhook.client.WebHookClientConfig;
import com.github.webhook.client.cts.GlobalCts;
import com.github.webhook.client.handler.WebHookHandlerChain;
import com.github.webhook.client.handler.WebhookHandler;
import com.github.webhook.client.param.WebhookParam;
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;
import org.apache.commons.lang3.StringUtils;

import java.security.MessageDigest;

/**
 * 检查Header正确性
 *
 * @author lonecloud
 * @date 2021/11/29 23:09
 */
@WebHookAction
public class CheckHeaderHandler implements WebhookHandler {

    @Override
    public void handler(WebhookParam param, WebHookHandlerChain chain) {
        checkHeader(param);
        chain.doHandler(param);
    }

    /**
     * 检查Header是否正常
     *
     * @param param
     */
    private void checkHeader(WebhookParam param) {
        WebHookClientConfig clientConfig = param.getWebHookClientConfig();
        WebhookParam.WebHookHeaderParam webHookHeaderParam = param.getWebHookHeaderParam();
        if (!StringUtils.startsWith(webHookHeaderParam.getUserAgent(), GlobalCts.HEADER_NAME_GITHUB_PREFIX)) {
            throw new GithubWebHookException("this userAgent not Accept");
        }
        if (clientConfig.getCheckSign()) {
            String signature = webHookHeaderParam.getSignature();
            if (StringUtils.isBlank(signature)) {
                throw new GithubWebHookException("No signature");
            }
            String computed = String.format("sha1=%s", new HmacUtils(HmacAlgorithms.HMAC_SHA_1, clientConfig.getSecretKey()).hmacHex(param.getData()));
            if (!MessageDigest.isEqual(signature.getBytes(), computed.getBytes())) {
                throw new GithubWebHookException("Invalid signature.");
            }
            String signature256 = webHookHeaderParam.getSignature256();
            if (StringUtils.isBlank(signature256)) {
                throw new GithubWebHookException("No signature");
            }
            String computed256 = String.format("sha256=%s", new HmacUtils(HmacAlgorithms.HMAC_SHA_256, clientConfig.getSecretKey()).hmacHex(param.getData()));
            if (!MessageDigest.isEqual(signature256.getBytes(), computed256.getBytes())) {
                throw new GithubWebHookException("Invalid signature.");
            }
        }
    }
}
