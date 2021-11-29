package com.github.webhook.client.param;

import com.github.webhook.client.WebHookClientConfig;
import com.github.webhook.client.enums.ActionEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WebhookParam {


    /**
     * webHookParam
     */
    private WebHookHeaderParam webHookHeaderParam;

    private WebHookClientConfig webHookClientConfig;

    /**
     * 数据
     */
    private String data;

    @Builder
    @Data
    public static class WebHookHeaderParam {
        /**
         * 那种类型的webHook
         */
        private ActionEnum action;
        /**
         * gitHub Version
         */
        private String appVersion;

        /**
         * hookId
         */
        private String hookId;
        /**
         * targetId
         */
        private String targetId;
        /**
         * targetType
         */
        private String targetType;

        /**
         * content-Type
         */
        private String contentType;

        /**
         * 验签数据
         */
        private String signature;

        private String signature256;
        /**
         *
         */
        private String userAgent;
    }
}
