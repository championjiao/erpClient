package qw.openapi.sdk.oauth.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class ServerOAuthCodeImpl {
    protected String serverUrl;
    protected String appKey;

    public ServerOAuthCodeImpl(String serverUrl, String appKey) {
        this.serverUrl = serverUrl;
        this.appKey = appKey;
    }

    public String getAuthUrl(String redirect_uri, String scope, String state) {
        String responseType = "code";

        try {
            redirect_uri = URLEncoder.encode(redirect_uri, "utf-8");
        } catch (UnsupportedEncodingException var6) {
            throw new RuntimeException(var6);
        }

        return String.format("%s?response_type=%s&client_id=%s&state=%s&redirect_uri=%s&scope=%s", new Object[]{this.serverUrl, responseType, this.appKey, state, redirect_uri, scope});
    }
}
