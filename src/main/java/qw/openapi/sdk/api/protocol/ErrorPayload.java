package qw.openapi.sdk.api.protocol;

public class ErrorPayload {
    private String code;
    private String message;

    public ErrorPayload() {
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}