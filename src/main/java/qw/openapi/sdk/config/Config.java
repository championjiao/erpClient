package qw.openapi.sdk.config;

import qw.openapi.sdk.config.QwSdkLogger;
import qw.openapi.sdk.utils.StringUtils;

public class Config {
    private String app_key;
    private String app_secret;
    private String oauthCodeUrl;
    private String oauthTokenUrl;
    private String apiUrl;
    private QwSdkLogger qwSdkLogger;

    public Config(boolean isSandbox, String appKey, String appSecret) {
        if(StringUtils.areNotEmpty(new String[]{appKey, appKey})) {
            System.out.println("init Config ...");
        } else {
            System.out.println("appKey and appSecret is required.");
        }

        this.app_key = appKey;
        this.app_secret = appSecret;
        //配置成全维的
        if(isSandbox) {
            this.oauthCodeUrl = "https://open-api-sandbox.shop.ele.me/authorize";
            this.oauthTokenUrl = "https://open-api-sandbox.shop.ele.me/token";
            this.apiUrl = "https://open-api-sandbox.shop.ele.me/api/v1/";
        } else {
            this.oauthCodeUrl = "https://open-api.shop.ele.me/authorize";
            this.oauthTokenUrl = "https://open-api.shop.ele.me/token";
            this.apiUrl = "https://open-api.shop.ele.me/api/v1/";
        }

    }

    public void setLog(QwSdkLogger qwSdkLogger) {
        this.qwSdkLogger = qwSdkLogger;
    }

    public QwSdkLogger getQwSdkLogger() {
        return this.qwSdkLogger;
    }

    public void setOauthCodeUrl(String oauthCodeUrl) {
        this.oauthCodeUrl = oauthCodeUrl;
    }

    public void setOauthTokenUrl(String oauthTokenUrl) {
        this.oauthTokenUrl = oauthTokenUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getOauthCodeUrl() {
        return this.oauthCodeUrl;
    }

    public String getOauthTokenUrl() {
        return this.oauthTokenUrl;
    }

    public String getApiUrl() {
        return this.apiUrl;
    }

    public String getApp_key() {
        return this.app_key;
    }

    public String getApp_secret() {
        return this.app_secret;
    }
}
