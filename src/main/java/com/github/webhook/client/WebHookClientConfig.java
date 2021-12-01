package com.github.webhook.client;

import lombok.Builder;
import lombok.Data;

/**
 * webHookClient
 *
 * @author lonecloud
 * @date 2021/11/29 22:13
 */
@Data
@Builder
public class WebHookClientConfig {

    /**
     * 是否验签
     */
    private Boolean checkSign;

    /**
     * contentType
     */
    private String contentType;

    /**
     * 密钥
     */
    private String secretKey;

}
