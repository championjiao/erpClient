package qw.openapi.sdk.oauth;

public class OAuthException extends Exception {
    private String errCode;
    private String errMsg;

    public OAuthException() {
    }

    public OAuthException(String message, Throwable cause) {
        super(message, cause);
    }

    public OAuthException(String message) {
        super(message);
    }

    public OAuthException(Throwable cause) {
        super(cause);
    }

    public OAuthException(String errCode, String errMsg) {
        super(errCode + ":" + errMsg);
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public String getErrCode() {
        return this.errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return this.errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
