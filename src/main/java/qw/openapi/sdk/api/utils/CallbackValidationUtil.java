package qw.openapi.sdk.api.utils;

import qw.openapi.sdk.api.entity.message.OMessage;
import qw.openapi.sdk.utils.SignatureUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

public class CallbackValidationUtil {
    public CallbackValidationUtil() {
    }

    public static boolean isValidMessage(OMessage message, String secret) {
        if(message == null) {
            return false;
        } else if(message.getSignature() == null) {
            return false;
        } else {
            HashMap map = new HashMap();
            map.put("requestId", message.getRequestId());
            map.put("message", message.getMessage());
            map.put("type", Integer.valueOf(message.getType()));
            map.put("shopId", Long.valueOf(message.getShopId()));
            map.put("timestamp", Long.valueOf(message.getTimestamp()));
            map.put("userId", Long.valueOf(message.getUserId()));
            map.put("appId", Integer.valueOf(message.getAppId()));
            String signature = null;

            try {
                signature = getSignature(map, secret);
            } catch (IOException var5) {
                throw new RuntimeException(var5);
            }

            return signature.toUpperCase().equals(message.getSignature().toUpperCase());
        }
    }

    public static String getSignature(Map<String, Object> params, String secret) throws IOException {
        TreeMap sortedParams = new TreeMap(params);
        Set entrys = sortedParams.entrySet();
        StringBuilder basestring = new StringBuilder();
        Iterator var5 = entrys.iterator();

        while(var5.hasNext()) {
            Entry param = (Entry)var5.next();
            basestring.append((String)param.getKey()).append("=").append(param.getValue());
        }

        basestring.append(secret);
        return SignatureUtil.md5(basestring.toString());
    }
}
