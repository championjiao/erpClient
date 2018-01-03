package qw.openapi.sdk.oauth.parser;

import qw.openapi.sdk.oauth.OAuthException;
import qw.openapi.sdk.oauth.mapping.ApiListField;
import qw.openapi.sdk.oauth.mapping.TokenField;
import qw.openapi.sdk.oauth.response.ErrorResponse;
import qw.openapi.sdk.utils.StringUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Converters {

    public static boolean isCheckJsonType = false;
    private static final Map<String, Set<String>> baseProps = new HashMap();
    private static final Map<String, Field> fieldCache = new ConcurrentHashMap();

    private Converters() {
    }

    public static <T> T convert(Class<T> clazz, Reader reader) {
        Object rsp = null;

        try {
            rsp = clazz.newInstance();
            BeanInfo e = Introspector.getBeanInfo(clazz);
            PropertyDescriptor[] pds = e.getPropertyDescriptors();
            PropertyDescriptor[] var5 = pds;
            int var6 = pds.length;

            for(int var7 = 0; var7 < var6; ++var7) {
                PropertyDescriptor pd = var5[var7];
                Method method = pd.getWriteMethod();
                if(method != null) {
                    String itemName = pd.getName();
                    String listName = null;
                    Field field = null;
                    Set stopProps = (Set)baseProps.get(clazz.getSuperclass().getName());
                    if(stopProps != null && stopProps.contains(itemName)) {
                        field = getField(clazz.getSuperclass(), pd);
                    } else {
                        field = getField(clazz, pd);
                    }

                    if(field != null) {
                        TokenField jsonField = (TokenField)field.getAnnotation(TokenField.class);
                        if(jsonField != null) {
                            itemName = jsonField.value();
                        }

                        ApiListField jsonListField = (ApiListField)field.getAnnotation(ApiListField.class);
                        if(jsonListField != null) {
                            listName = jsonListField.value();
                        }

                        if(reader.hasReturnField(itemName) || listName != null && reader.hasReturnField(listName)) {
                            Class typeClass = field.getType();
                            Object obj;
                            if(String.class.isAssignableFrom(typeClass)) {
                                obj = reader.getPrimitiveObject(itemName);
                                if(obj instanceof String) {
                                    method.invoke(rsp, new Object[]{obj.toString()});
                                } else {
                                    if(isCheckJsonType && obj != null) {
                                        throw new OAuthException(itemName + " is not a String");
                                    }

                                    if(obj != null) {
                                        method.invoke(rsp, new Object[]{obj.toString()});
                                    } else {
                                        method.invoke(rsp, new Object[]{""});
                                    }
                                }
                            } else if(Long.class.isAssignableFrom(typeClass)) {
                                obj = reader.getPrimitiveObject(itemName);
                                if(obj instanceof Long) {
                                    method.invoke(rsp, new Object[]{(Long)obj});
                                } else {
                                    if(isCheckJsonType && obj != null) {
                                        throw new OAuthException(itemName + " is not a Number(Long)");
                                    }

                                    if(StringUtils.isNumeric(obj)) {
                                        method.invoke(rsp, new Object[]{Long.valueOf(obj.toString())});
                                    }
                                }
                            } else if(Boolean.class.isAssignableFrom(typeClass)) {
                                obj = reader.getPrimitiveObject(itemName);
                                if(obj instanceof Boolean) {
                                    method.invoke(rsp, new Object[]{(Boolean)obj});
                                } else {
                                    if(isCheckJsonType && obj != null) {
                                        throw new OAuthException(itemName + " is not a Boolean");
                                    }

                                    if(obj != null) {
                                        method.invoke(rsp, new Object[]{Boolean.valueOf(obj.toString())});
                                    }
                                }
                            } else if(Date.class.isAssignableFrom(typeClass)) {
                                obj = reader.getPrimitiveObject(itemName);
                                if(obj instanceof String) {
                                    method.invoke(rsp, new Object[]{StringUtils.parseDateTime(obj.toString())});
                                }
                            } else if(List.class.isAssignableFrom(typeClass)) {
                                Type var23 = field.getGenericType();
                                if(var23 instanceof ParameterizedType) {
                                    ParameterizedType paramType = (ParameterizedType)var23;
                                    Type[] genericTypes = paramType.getActualTypeArguments();
                                    if(genericTypes != null && genericTypes.length > 0 && genericTypes[0] instanceof Class) {
                                        Class subType = (Class)genericTypes[0];
                                        List listObjs = reader.getListObjects(listName, itemName, subType);
                                        if(listObjs != null) {
                                            method.invoke(rsp, new Object[]{listObjs});
                                        }
                                    }
                                }
                            } else if(Integer.class.isAssignableFrom(typeClass)) {
                                obj = reader.getPrimitiveObject(itemName);
                                if(obj instanceof Integer) {
                                    method.invoke(rsp, new Object[]{(Integer)obj});
                                } else {
                                    if(isCheckJsonType && obj != null) {
                                        throw new OAuthException(itemName + " is not a Number(Integer)");
                                    }

                                    if(StringUtils.isNumeric(obj)) {
                                        method.invoke(rsp, new Object[]{Integer.valueOf(obj.toString())});
                                    }
                                }
                            } else if(Double.class.isAssignableFrom(typeClass)) {
                                obj = reader.getPrimitiveObject(itemName);
                                if(obj instanceof Double) {
                                    method.invoke(rsp, new Object[]{(Double)obj});
                                } else if(isCheckJsonType && obj != null) {
                                    throw new OAuthException(itemName + " is not a Double");
                                }
                            } else if(Number.class.isAssignableFrom(typeClass)) {
                                obj = reader.getPrimitiveObject(itemName);
                                if(obj instanceof Number) {
                                    method.invoke(rsp, new Object[]{(Number)obj});
                                } else if(isCheckJsonType && obj != null) {
                                    throw new OAuthException(itemName + " is not a Number");
                                }
                            } else {
                                obj = reader.getObject(itemName, typeClass);
                                if(obj != null) {
                                    method.invoke(rsp, new Object[]{obj});
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception var22) {
            var22.printStackTrace();
        }

        return (T)rsp;
    }

    private static Field getField(Class<?> clazz, PropertyDescriptor pd) throws Exception {
        String key = clazz.getName() + "_" + pd.getName();
        Field field = (Field)fieldCache.get(key);
        if(field == null) {
            try {
                field = clazz.getDeclaredField(pd.getName());
                fieldCache.put(key, field);
            } catch (NoSuchFieldException var5) {
                return null;
            }
        }

        return field;
    }

    static {
        baseProps.put(ErrorResponse.class.getName(), StringUtils.getClassProperties(ErrorResponse.class, false));
    }
}