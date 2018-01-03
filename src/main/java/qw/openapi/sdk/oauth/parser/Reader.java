package qw.openapi.sdk.oauth.parser;

import qw.openapi.sdk.oauth.OAuthException;
import java.util.List;

public interface Reader {
    boolean hasReturnField(Object var1);

    Object getPrimitiveObject(Object var1);

    Object getObject(Object var1, Class<?> var2) throws OAuthException;

    List<?> getListObjects(Object var1, Object var2, Class<?> var3) throws OAuthException;
}
