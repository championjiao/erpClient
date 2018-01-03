package qw.openapi.sdk.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Map.Entry;

public class PropertiesUtils {
    public PropertiesUtils() {
    }

    public static void main(String[] args) throws Exception {
    }

    public static String getPropValueByKey(String pKey) {
        ResourceBundle rs = ResourceBundle.getBundle("token");
        return rs.getString(pKey);
    }

    public static void setProps(Map<String, String> map) {
        if(map != null) {
            URL resource = Thread.currentThread().getContextClassLoader().getResource("token.properties");
            FileOutputStream out = null;

            try {
                Properties ex = new Properties();
                File f = new File(resource.getPath());
                if(f.exists()) {
                    ex.load(new FileReader(f));
                    Iterator var5 = map.entrySet().iterator();

                    while(var5.hasNext()) {
                        Entry entry = (Entry)var5.next();
                        ex.setProperty((String)entry.getKey(), entry.getValue() == null?"":(String)entry.getValue());
                    }
                } else {
                    ex.setProperty("access_token", "");
                    ex.setProperty("token_type", "");
                    ex.setProperty("expires_in", "");
                    ex.setProperty("refresh_token", "");
                    f.createNewFile();
                }

                out = new FileOutputStream(f);
                ex.store(out, "Token Info");
            } catch (Exception var15) {
                var15.printStackTrace();
            } finally {
                if(out != null) {
                    try {
                        out.close();
                    } catch (IOException var14) {
                        System.out.println("IOException: Could not close token.properties output stream; " + var14.getMessage());
                        var14.printStackTrace();
                    }
                }

            }

        }
    }
}
