package qw.openapi.sdk.oauth.response;

import qw.openapi.sdk.oauth.mapping.TokenField;

public class Token extends ErrorResponse {
    @TokenField("access_token")
    private String accessToken;
    @TokenField("token_type")
    private String tokenType;
    @TokenField("expires_in")
    private long expires;
    @TokenField("refresh_token")
    private String refreshToken;

    public Token() {
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return this.tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public long getExpires() {
        return this.expires;
    }

    public void setExpires(long expires) {
        this.expires = expires;
    }

    public String getRefreshToken() {
        return this.refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String toString() {
        return "Token{accessToken=\'" + this.accessToken + '\'' + ", tokenType=\'" + this.tokenType + '\'' + ", expires=" + this.expires + ", refreshToken=\'" + this.refreshToken + '\'' + '}';
    }
}
