package qw.openapi.sdk.oauth;

import qw.openapi.sdk.oauth.response.ErrorResponse;

public interface IOAuthClient {
    <T extends ErrorResponse> T execute(OAuthRequest<T> var1);
}
