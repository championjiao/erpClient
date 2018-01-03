package qw.openapi.sdk.utils;

import java.security.MessageDigest;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

public class SignatureUtil {
    public SignatureUtil() {
    }

    public static String generateSignature(String appKey, String secret, long timestamp, String action, String token, Map<String, Object> parameters) {
        TreeMap sorted = new TreeMap();
        Iterator string = parameters.entrySet().iterator();

        while(string.hasNext()) {
            Entry splice = (Entry)string.next();
            sorted.put(splice.getKey(), splice.getValue());
        }

        sorted.put("app_key", appKey);
        sorted.put("timestamp", Long.valueOf(timestamp));
        StringBuffer string1 = new StringBuffer();
        Iterator splice1 = sorted.entrySet().iterator();

        while(splice1.hasNext()) {
            Entry calculatedSignature = (Entry)splice1.next();
            string1.append((String)calculatedSignature.getKey()).append("=").append(JacksonUtils.obj2json(calculatedSignature.getValue()));
        }

        String splice2 = String.format("%s%s%s%s", new Object[]{action, token, string1, secret});
        System.out.println("\n\n\n" + splice2);
        String calculatedSignature1 = md5(splice2);
        return calculatedSignature1.toUpperCase();
    }

    public static String md5(String str) {
        MessageDigest md = null;

        try {
            md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes("UTF-8"));
        } catch (Exception var5) {
            ;
        }

        byte[] byteData = md.digest();
        StringBuilder buffer = new StringBuilder();

        for(int i = 0; i < byteData.length; ++i) {
            buffer.append(Integer.toString((byteData[i] & 255) + 256, 16).substring(1));
        }

        return buffer.toString();
    }
}
