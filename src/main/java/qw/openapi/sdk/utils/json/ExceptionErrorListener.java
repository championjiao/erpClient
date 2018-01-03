package qw.openapi.sdk.utils.json;

public class ExceptionErrorListener extends BufferErrorListener {
    public ExceptionErrorListener() {
    }

    public void error(String type, int col) {
        super.error(type, col);
        throw new IllegalArgumentException(this.buffer.toString());
    }
}
