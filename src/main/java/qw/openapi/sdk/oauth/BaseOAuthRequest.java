package qw.openapi.sdk.oauth;

import qw.openapi.sdk.oauth.response.ErrorResponse;
import qw.openapi.sdk.utils.Base64;
import qw.openapi.sdk.utils.EleHashMap;
import java.util.Map;

public abstract class BaseOAuthRequest<T extends ErrorResponse> implements OAuthRequest<T> {
    protected Map<String, String> headerMap;
    protected Map<String, String> bodyMap;
    protected EleHashMap customParams;

    public BaseOAuthRequest() {
    }

    protected void setAuthorization(String appKey, String appSecret) {
        String headOauthKey = "Authorization";
        this.putHeaderParam(headOauthKey, this.getBasic(appKey, appSecret));
    }

    public void putHeaderParam(String key, String value) {
        if(this.headerMap == null) {
            this.headerMap = new EleHashMap();
        }

        this.headerMap.put(key, value);
    }

    public void putBodyParam(String key, String value) {
        if(this.bodyMap == null) {
            this.bodyMap = new EleHashMap();
        }

        this.bodyMap.put(key, value);
    }

    private String getBasic(String appKey, String appSecret) {
        StringBuilder sb = new StringBuilder();
        StringBuilder basicContent = new StringBuilder();
        basicContent.append(appKey).append(":").append(appSecret);
        String encodeToString = Base64.encodeToString(basicContent.toString().getBytes(), false);
        sb.append("Basic").append(" ").append(encodeToString);
        return sb.toString();
    }
}
