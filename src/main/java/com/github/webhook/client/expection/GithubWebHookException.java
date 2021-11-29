package com.github.webhook.client.expection;

/**
 * TODO
 *
 * @author lonecloud
 * @date 2021/11/29 23:04
 */
public class GithubWebHookException extends RuntimeException{
    public GithubWebHookException() {
        super();
    }

    public GithubWebHookException(String message) {
        super(message);
    }

    public GithubWebHookException(String message, Throwable cause) {
        super(message, cause);
    }
}
