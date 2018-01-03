package qw.openapi.sdk.oauth.parser;

import qw.openapi.sdk.oauth.response.ErrorResponse;

public interface OAuthParser<T extends ErrorResponse> {
    T parse(String var1);

    Class<T> getResponseClass();
}
