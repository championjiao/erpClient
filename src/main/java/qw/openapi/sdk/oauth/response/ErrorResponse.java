package qw.openapi.sdk.oauth.response;

import qw.openapi.sdk.oauth.mapping.TokenField;
import qw.openapi.sdk.utils.StringUtils;
import java.io.Serializable;

public class ErrorResponse implements Serializable {

    private static final long serialVersionUID = 8287038168833863570L;
    @TokenField("error")
    private String error;
    @TokenField("error_description")
    private String error_description;

    public ErrorResponse() {
    }

    public String getError() {
        return this.error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError_description() {
        return this.error_description;
    }

    public void setError_description(String error_description) {
        this.error_description = error_description;
    }

    public boolean isSuccess() {
        return StringUtils.isEmpty(this.error);
    }
}
