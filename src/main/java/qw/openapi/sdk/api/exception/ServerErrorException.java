package qw.openapi.sdk.api.exception;

public class ServerErrorException extends RuntimeException {
    private String code;

    public ServerErrorException() {
        super("服务异常，请重试");
        this.code = "SERVER_ERROR";
    }

    public ServerErrorException(String message) {
        super(message);
        this.code = "SERVER_ERROR";
    }

    public ServerErrorException(String code, String message) {
        this(message);
        this.setCode(code);
    }

    public ServerErrorException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = "SERVER_ERROR";
        this.setCode(code);
    }

    public ServerErrorException(String code, Throwable cause) {
        super(cause);
        this.code = "SERVER_ERROR";
        this.setCode(code);
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
