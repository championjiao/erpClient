package qw.openapi.sdk.oauth.impl;

import qw.openapi.sdk.config.Config;
import qw.openapi.sdk.oauth.IOAuthClient;
import qw.openapi.sdk.oauth.OAuthRequest;
import qw.openapi.sdk.oauth.parser.ObjectJsonParser;
import qw.openapi.sdk.oauth.response.ErrorResponse;
import qw.openapi.sdk.utils.WebUtils;
import java.io.IOException;

public class DefaultIOAuthClient implements IOAuthClient {
    private int connectTimeout = 15000;
    private int readTimeout = 30000;
    private boolean useSimplifyJson = false;
    private Config context;

    public DefaultIOAuthClient(Config context) {
        this.context = context;
    }

    //post请求 看能不能转换成我们的
    public <T extends ErrorResponse> T execute(OAuthRequest<T> request) {
        try {
            String e = WebUtils.doPost(this.context, this.context.getOauthTokenUrl(), request.getBodyMap(), "UTF-8", this.connectTimeout, this.readTimeout, request.getHeaderMap());
            ObjectJsonParser parser = new ObjectJsonParser(request.getResponseClass(), this.useSimplifyJson);
            return (T)parser.parse(e);
        } catch (IOException var4) {
            var4.printStackTrace();
            return null;
        }
    }
}
