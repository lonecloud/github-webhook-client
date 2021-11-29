package com.github.webhook.client.cts;

public class GlobalCts {
    public static final String USER_AGENT = "User-Agent";
    public static final String HEADER_NAME_Event="X-GitHub-Event";
    public static final String HEADER_NAME_SIGN="X-Hub-Signature";
    public static final String HEADER_NAME_SIGN_256="X-Hub-Signature-256";
    public static final String HEADER_NAME_HOOK_ID="X-GitHub-Hook-ID";
    public static final String HEADER_NAME_TARGET_ID="X-GitHub-Hook-Installation-Target-ID";
    public static final String HEADER_NAME_TARGET_TYPE="X-GitHub-Hook-Installation-Target-Type";
    /**
     * GitHub版本
     */
    public static final String HEADER_NAME_GITHUB_VERSION="X-Github-Webhook-Client-Version";
    /**
     * Github头部信息
     */
    public static final String HEADER_NAME_GITHUB_PREFIX="GitHub-Hookshot";
}
