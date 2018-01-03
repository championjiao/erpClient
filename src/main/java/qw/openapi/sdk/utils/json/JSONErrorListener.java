package qw.openapi.sdk.utils.json;

public interface JSONErrorListener {
    void start(String var1);

    void error(String var1, int var2);

    void end();
}
