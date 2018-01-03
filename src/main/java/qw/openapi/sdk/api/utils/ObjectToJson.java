package qw.openapi.sdk.api.utils;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ObjectToJson {
    public ObjectToJson() {
    }

    public static String objectToJson(Object obj) {
        StringBuilder json = new StringBuilder();
        if(obj == null) {
            json.append("\"\"");
        } else if(!(obj instanceof String) && !(obj instanceof Integer) && !(obj instanceof Float) && !(obj instanceof Boolean) && !(obj instanceof Short) && !(obj instanceof Double) && !(obj instanceof Long) && !(obj instanceof BigDecimal) && !(obj instanceof BigInteger) && !(obj instanceof Byte)) {
            if(obj instanceof Object[]) {
                json.append(arrayToJson((Object[])((Object[])obj)));
            } else if(obj instanceof List) {
                json.append(listToJson((List)obj));
            } else if(obj instanceof Map) {
                json.append(mapToJson((Map)obj));
            } else if(obj instanceof Set) {
                json.append(setToJson((Set)obj));
            } else {
                json.append(beanToJson(obj));
            }
        } else {
            json.append("\"").append(stringToJson(obj.toString())).append("\"");
        }

        return json.toString();
    }

    public static String stringToJson(String str) {
        if(str == null) {
            return "";
        } else {
            StringBuilder sb = new StringBuilder();

            for(int i = 0; i < str.length(); ++i) {
                char ch = str.charAt(i);
                switch(ch) {
                case '\b':
                    sb.append("\\b");
                    continue;
                case '\t':
                    sb.append("\\t");
                    continue;
                case '\n':
                    sb.append("\\n");
                    continue;
                case '\f':
                    sb.append("\\f");
                    continue;
                case '\r':
                    sb.append("\\r");
                    continue;
                case '\"':
                    sb.append("\\\"");
                    continue;
                case '/':
                    sb.append("\\/");
                    continue;
                case '\\':
                    sb.append("\\\\");
                    continue;
                }

                if(ch >= 0 && ch <= 31) {
                    String ss = Integer.toHexString(ch);
                    sb.append("\\u");

                    for(int k = 0; k < 4 - ss.length(); ++k) {
                        sb.append('0');
                    }

                    sb.append(ss.toUpperCase());
                } else {
                    sb.append(ch);
                }
            }

            return sb.toString();
        }
    }

    public static String arrayToJson(Object[] array) {
        StringBuilder json = new StringBuilder();
        json.append("[");
        if(array != null && array.length > 0) {
            Object[] var2 = array;
            int var3 = array.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                Object obj = var2[var4];
                json.append(objectToJson(obj));
                json.append(",");
            }

            json.setCharAt(json.length() - 1, ']');
        } else {
            json.append("]");
        }

        return json.toString();
    }

    public static String listToJson(List<?> list) {
        StringBuilder json = new StringBuilder();
        json.append("[");
        if(list != null && list.size() > 0) {
            Iterator var2 = list.iterator();

            while(var2.hasNext()) {
                Object obj = var2.next();
                json.append(objectToJson(obj));
                json.append(",");
            }

            json.setCharAt(json.length() - 1, ']');
        } else {
            json.append("]");
        }

        return json.toString();
    }

    public static String mapToJson(Map<?, ?> map) {
        StringBuilder json = new StringBuilder();
        json.append("{");
        if(map != null && map.size() > 0) {
            Iterator var2 = map.keySet().iterator();

            while(var2.hasNext()) {
                Object key = var2.next();
                json.append(objectToJson(key));
                json.append(":");
                json.append(objectToJson(map.get(key)));
                json.append(",");
            }

            json.setCharAt(json.length() - 1, '}');
        } else {
            json.append("}");
        }

        return json.toString();
    }

    public static String setToJson(Set<?> set) {
        StringBuilder json = new StringBuilder();
        json.append("[");
        if(set != null && set.size() > 0) {
            Iterator var2 = set.iterator();

            while(var2.hasNext()) {
                Object obj = var2.next();
                json.append(objectToJson(obj));
                json.append(",");
            }

            json.setCharAt(json.length() - 1, ']');
        } else {
            json.append("]");
        }

        return json.toString();
    }

    public static String beanToJson(Object bean) {
        StringBuilder json = new StringBuilder();
        json.append("{");
        PropertyDescriptor[] props = null;

        try {
            props = Introspector.getBeanInfo(bean.getClass(), Object.class).getPropertyDescriptors();
        } catch (IntrospectionException var7) {
            ;
        }

        if(props != null) {
            for(int i = 0; i < props.length; ++i) {
                try {
                    String name = objectToJson(props[i].getName());
                    String value = objectToJson(props[i].getReadMethod().invoke(bean, new Object[0]));
                    json.append(name);
                    json.append(":");
                    json.append(value);
                    json.append(",");
                } catch (Exception var6) {
                    ;
                }
            }

            json.setCharAt(json.length() - 1, '}');
        } else {
            json.append("}");
        }

        return json.toString();
    }
}
