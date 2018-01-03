package qw.openapi.sdk.oauth;

import qw.openapi.sdk.oauth.response.ErrorResponse;
import java.util.Map;

public interface OAuthRequest<T extends ErrorResponse> {
    Class<T> getResponseClass();

    Map<String, String> getHeaderMap();

    Map<String, String> getBodyMap();
}
