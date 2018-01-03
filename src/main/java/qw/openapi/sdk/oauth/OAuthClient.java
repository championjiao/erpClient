package qw.openapi.sdk.oauth;

import qw.openapi.sdk.config.Config;
import qw.openapi.sdk.oauth.impl.DefaultIOAuthClient;
import qw.openapi.sdk.oauth.impl.ServerOAuthCodeImpl;
import qw.openapi.sdk.oauth.request.ClientTokenRequest;
import qw.openapi.sdk.oauth.request.ServerRefreshTokenRequest;
import qw.openapi.sdk.oauth.request.ServerTokenRequest;
import qw.openapi.sdk.oauth.response.Token;

public class OAuthClient {
    private Config config;
    private IOAuthClient ioAuthClient = null;

    public OAuthClient(Config config) {
        this.config = config;
    }

    //获取token
    public Token getTokenInClientCredentials() {
        this.ioAuthClient = new DefaultIOAuthClient(this.config);
        ClientTokenRequest oAuthRequest = new ClientTokenRequest(this.config);
        Token token = (Token)this.ioAuthClient.execute(oAuthRequest);
        return token;
    }

    public String getAuthUrl(String redirect_uri, String scope, String state) {
        ServerOAuthCodeImpl serverOAuthCode = new ServerOAuthCodeImpl(this.config.getOauthCodeUrl(), this.config.getApp_key());
        return serverOAuthCode.getAuthUrl(redirect_uri, scope, state);
    }

    public Token getTokenByCode(String authCode, String redirect_uri) {
        this.ioAuthClient = new DefaultIOAuthClient(this.config);
        ServerTokenRequest serverTokenRequest = new ServerTokenRequest(this.config);
        serverTokenRequest.setCode(authCode);
        serverTokenRequest.setRedirectUri(redirect_uri);
        Token token = (Token)this.ioAuthClient.execute(serverTokenRequest);
        return token;
    }

    public Token getTokenByRefreshToken(String refreshToken) {
        this.ioAuthClient = new DefaultIOAuthClient(this.config);
        ServerRefreshTokenRequest refreshTokenRequest = new ServerRefreshTokenRequest(this.config);
        refreshTokenRequest.setRefreshToken(refreshToken);
        Token token = (Token)this.ioAuthClient.execute(refreshTokenRequest);
        return token;
    }
}
