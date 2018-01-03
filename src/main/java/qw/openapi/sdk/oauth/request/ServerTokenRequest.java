package qw.openapi.sdk.oauth.request;

import qw.openapi.sdk.config.Config;
import qw.openapi.sdk.oauth.BaseOAuthRequest;
import qw.openapi.sdk.oauth.response.Token;
import java.util.Map;

public class ServerTokenRequest extends BaseOAuthRequest<Token> {
    private Config context;
    private String code;
    private String redirectUri;

    public ServerTokenRequest(Config context) {
        this.context = context;
    }

    public Class<Token> getResponseClass() {
        return Token.class;
    }

    public Map<String, String> getHeaderMap() {
        this.setAuthorization(this.context.getApp_key(), this.context.getApp_secret());
        return super.headerMap;
    }

    public Map<String, String> getBodyMap() {
        this.putBodyParam("grant_type", "authorization_code");
        this.putBodyParam("code", this.code);
        this.putBodyParam("redirect_uri", this.redirectUri);
        this.putBodyParam("client_id", this.context.getApp_key());
        return super.bodyMap;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }
}
