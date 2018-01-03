package qw.openapi.sdk.utils.json;

public class StdoutStreamErrorListener extends BufferErrorListener {
    public StdoutStreamErrorListener() {
    }

    public void end() {
        System.out.print(this.buffer.toString());
    }
}
