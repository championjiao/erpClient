package qw.openapi.sdk.oauth.parser;


import qw.openapi.sdk.oauth.response.ErrorResponse;
import qw.openapi.sdk.utils.json.ExceptionErrorListener;
import qw.openapi.sdk.utils.json.JSONValidatingReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SimplifyJsonConverter implements Converter {
    public SimplifyJsonConverter() {
    }

    public <T extends ErrorResponse> T toResponse(String rsp, Class<T> clazz) {
        JSONValidatingReader reader = new JSONValidatingReader(new ExceptionErrorListener());
        Object rootObj = reader.read(rsp);
        if(rootObj instanceof Map) {
            Map rootJson = (Map)rootObj;
            Object errorJson = rootJson.get("error_response");
            return errorJson instanceof Map?(T)this.fromJson((Map)errorJson, clazz):(T)this.fromJson(rootJson, clazz);
        } else {
            return null;
        }
    }

    public <T> T fromJson(final Map<?, ?> json, Class<T> clazz) {
        return Converters.convert(clazz, new Reader() {
            public boolean hasReturnField(Object name) {
                return json.containsKey(name);
            }

            public Object getPrimitiveObject(Object name) {
                return json.get(name);
            }

            public Object getObject(Object name, Class<?> type) {
                Object tmp = json.get(name);
                if(tmp instanceof Map) {
                    Map map = (Map)tmp;
                    return SimplifyJsonConverter.this.fromJson(map, type);
                } else {
                    return tmp;
                }
            }

            public List<?> getListObjects(Object listName, Object itemName, Class<?> subType) {
                ArrayList listObjs = null;
                Object jsonList = json.get(listName);
                if(jsonList instanceof List) {
                    listObjs = new ArrayList();
                    List listObj = (List)jsonList;
                    Iterator var7 = listObj.iterator();

                    while(var7.hasNext()) {
                        Object tmp = var7.next();
                        if(tmp instanceof Map) {
                            Map subMap = (Map)tmp;
                            Object subObj = SimplifyJsonConverter.this.fromJson(subMap, subType);
                            if(subObj != null) {
                                listObjs.add(subObj);
                            }
                        } else if(!(tmp instanceof List)) {
                            listObjs.add(tmp);
                        }
                    }
                }

                return listObjs;
            }
        });
    }
}
