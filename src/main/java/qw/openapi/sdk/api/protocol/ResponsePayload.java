package qw.openapi.sdk.api.protocol;

import java.io.Serializable;

public class ResponsePayload implements Serializable {
    private String id;
    private Object result;
    private ErrorPayload error;

    public ResponsePayload() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getResult() {
        return this.result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public ErrorPayload getError() {
        return this.error;
    }

    public void setError(ErrorPayload error) {
        this.error = error;
    }
}
