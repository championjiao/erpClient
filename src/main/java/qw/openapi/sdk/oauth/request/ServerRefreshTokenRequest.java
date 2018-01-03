package qw.openapi.sdk.oauth.request;

import qw.openapi.sdk.config.Config;
import qw.openapi.sdk.oauth.BaseOAuthRequest;
import qw.openapi.sdk.oauth.response.Token;
import java.util.Map;

public class ServerRefreshTokenRequest extends BaseOAuthRequest<Token> {
    private Config context;
    private String refreshToken;

    public ServerRefreshTokenRequest(Config context) {
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
        this.putBodyParam("grant_type", "refresh_token");
        this.putBodyParam("refresh_token", this.refreshToken);
        return super.bodyMap;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
