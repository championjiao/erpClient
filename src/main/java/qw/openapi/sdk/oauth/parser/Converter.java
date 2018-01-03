package qw.openapi.sdk.oauth.parser;

import qw.openapi.sdk.oauth.response.ErrorResponse;

public interface Converter {
    <T extends ErrorResponse> T toResponse(String var1, Class<T> var2);
}