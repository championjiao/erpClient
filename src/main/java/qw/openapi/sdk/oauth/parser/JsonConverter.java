package qw.openapi.sdk.oauth.parser;

import qw.openapi.sdk.oauth.response.ErrorResponse;
import qw.openapi.sdk.utils.json.ExceptionErrorListener;
import qw.openapi.sdk.utils.json.JSONValidatingReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class JsonConverter implements Converter {
    public JsonConverter() {
    }

    public <T extends ErrorResponse> T toResponse(String rsp, Class<T> clazz) {
        JSONValidatingReader reader = new JSONValidatingReader(new ExceptionErrorListener());
        Object rootObj = reader.read(rsp);
        if(rootObj instanceof Map) {
            Map rootJson1 = (Map)rootObj;
            return (T)this.fromJson(rootJson1, clazz);
        } else {
            if(rootObj instanceof List) {
                List rootJson = (List)rootObj;
                Iterator var6 = rootJson.iterator();
                if(var6.hasNext()) {
                    Object o = var6.next();
                    Map rj = (Map)o;
                    return (T)this.fromJson(rj, clazz);
                }
            }

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
                    return JsonConverter.this.fromJson(map, type);
                } else {
                    return tmp;
                }
            }

            public List<?> getListObjects(Object listName, Object itemName, Class<?> subType) {
                ArrayList listObjs = null;
                Object listTmp = json.get(listName);
                if(listTmp instanceof Map) {
                    Map jsonMap = (Map)listTmp;
                    Object itemTmp = jsonMap.get(itemName);
                    if(itemTmp == null && listName != null) {
                        String tmpList = listName.toString();
                        itemTmp = jsonMap.get(tmpList.substring(0, tmpList.length() - 1));
                    }

                    if(itemTmp instanceof List) {
                        listObjs = new ArrayList();
                        List tmpList1 = (List)itemTmp;
                        Iterator var9 = tmpList1.iterator();

                        while(var9.hasNext()) {
                            Object subTmp = var9.next();
                            if(subTmp instanceof Map) {
                                Map subMap = (Map)subTmp;
                                Object subObj = JsonConverter.this.fromJson(subMap, subType);
                                if(subObj != null) {
                                    listObjs.add(subObj);
                                }
                            } else if(!(subTmp instanceof List)) {
                                listObjs.add(subTmp);
                            }
                        }
                    }
                }

                return listObjs;
            }
        });
    }
}
