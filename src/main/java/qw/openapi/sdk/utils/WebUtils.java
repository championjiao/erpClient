package qw.openapi.sdk.utils;

import com.fasterxml.jackson.databind.JavaType;
import qw.openapi.sdk.api.exception.AccessDeniedException;
import qw.openapi.sdk.api.exception.BusinessException;
import qw.openapi.sdk.api.exception.ExceedLimitException;
import qw.openapi.sdk.api.exception.InvalidSignatureException;
import qw.openapi.sdk.api.exception.InvalidTimestampException;
import qw.openapi.sdk.api.exception.MethodNotAllowedException;
import qw.openapi.sdk.api.exception.PermissionDeniedException;
import qw.openapi.sdk.api.exception.ServerErrorException;
import qw.openapi.sdk.api.exception.ServiceException;
import qw.openapi.sdk.api.exception.SourceTimeoutException;
import qw.openapi.sdk.api.exception.UnauthorizedException;
import qw.openapi.sdk.api.exception.ValidationFailedException;
import qw.openapi.sdk.api.protocol.ErrorPayload;
import qw.openapi.sdk.api.protocol.ResponsePayload;
import qw.openapi.sdk.config.Config;
import qw.openapi.sdk.oauth.response.Token;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.zip.GZIPInputStream;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public abstract class WebUtils {
    private static final String DEFAULT_CHARSET = "UTF-8";
    private static final String METHOD_POST = "POST";

    public WebUtils() {
    }

    public static String doPost(Config context, String url, Map<String, String> params, String charset, int connectTimeout, int readTimeout, Map<String, String> headerMap) throws IOException {
        String ctype = "application/x-www-form-urlencoded;charset=" + charset;
        String query = buildQuery(params, charset);
        setLogInfo(context, "request: " + query);
        byte[] content = new byte[0];
        if(query != null) {
            content = query.getBytes(charset);
        }

        return _doPost(context, url, ctype, content, connectTimeout, readTimeout, headerMap);
    }

    public static String doPost(Config context, String url, String ctype, byte[] content, int connectTimeout, int readTimeout) throws SocketTimeoutException, IOException {
        return _doPost(context, url, ctype, content, connectTimeout, readTimeout, (Map)null);
    }

    private static String _doPost(Config context, String url, String ctype, byte[] content, int connectTimeout, int readTimeout, Map<String, String> headerMap) throws SocketTimeoutException, IOException {
        HttpURLConnection conn = null;
        OutputStream out = null;
        String rsp = null;

        try {
            conn = getConnection(new URL(url), "POST", ctype, headerMap);
            conn.setConnectTimeout(connectTimeout);
            conn.setReadTimeout(readTimeout);
            out = conn.getOutputStream();
            out.write(content);
            rsp = getResponseAsString(conn);
        } finally {
            if(out != null) {
                out.close();
            }

            if(conn != null) {
                conn.disconnect();
            }

        }

        setLogInfo(context, "response: " + rsp);
        return rsp;
    }

    private static HttpURLConnection getConnection(URL url, String method, String ctype, Map<String, String> headerMap) throws IOException {
        Object conn;
        if("https".equals(url.getProtocol())) {
            SSLContext ctx;
            try {
                ctx = SSLContext.getInstance("TLS");
                ctx.init(new KeyManager[0], new TrustManager[]{new WebUtils.DefaultTrustManager()}, new SecureRandom());
            } catch (Exception var7) {
//                throw new IOException(var7);
                throw new IOException(var7.getMessage());
            }

            HttpsURLConnection entry = (HttpsURLConnection)url.openConnection();
            entry.setSSLSocketFactory(ctx.getSocketFactory());
            entry.setHostnameVerifier(new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            conn = entry;
        } else {
            conn = (HttpURLConnection)url.openConnection();
        }

        ((HttpURLConnection)conn).setRequestMethod(method);
        ((HttpURLConnection)conn).setDoInput(true);
        ((HttpURLConnection)conn).setDoOutput(true);
        ((HttpURLConnection)conn).setRequestProperty("Accept", "text/xml,text/javascript,text/html");
        ((HttpURLConnection)conn).setRequestProperty("Content-Type", ctype);
        ((HttpURLConnection)conn).setRequestProperty("Accept-Encoding", "gzip");
        ((HttpURLConnection)conn).setRequestProperty("User-Agent", "eleme-openapi-java-sdk");
        if(headerMap != null) {
            Iterator ctx1 = headerMap.entrySet().iterator();

            while(ctx1.hasNext()) {
                Entry entry1 = (Entry)ctx1.next();
                ((HttpURLConnection)conn).setRequestProperty((String)entry1.getKey(), (String)entry1.getValue());
            }
        }

        return (HttpURLConnection)conn;
    }

    public static String buildQuery(Map<String, String> params, String charset) throws IOException {
        if(params != null && !params.isEmpty()) {
            StringBuilder query = new StringBuilder();
            Set entries = params.entrySet();
            boolean hasParam = false;
            Iterator var5 = entries.iterator();

            while(var5.hasNext()) {
                Entry entry = (Entry)var5.next();
                String name = (String)entry.getKey();
                String value = (String)entry.getValue();
                if(StringUtils.areNotEmpty(new String[]{name, value})) {
                    if(hasParam) {
                        query.append("&");
                    } else {
                        hasParam = true;
                    }

                    query.append(name).append("=").append(URLEncoder.encode(value, charset));
                }
            }

            return query.toString();
        } else {
            return null;
        }
    }

    protected static String getResponseAsString(HttpURLConnection conn) throws IOException {
        String charset = getResponseCharset(conn.getContentType());
        InputStream es = conn.getErrorStream();
        if(es == null) {
            return getStreamAsString(conn.getInputStream(), charset, conn);
        } else {
            String msg = getStreamAsString(es, charset, conn);
            if(StringUtils.isEmpty(msg)) {
                throw new IOException(conn.getResponseCode() + ":" + conn.getResponseMessage());
            } else {
                return msg;
            }
        }
    }

    private static String getStreamAsString(InputStream stream, String charset, HttpURLConnection conn) throws IOException {
        try {
            InputStreamReader reader;
            if("gzip".equals(conn.getContentEncoding())) {
                reader = new InputStreamReader(new GZIPInputStream(stream), charset);
            } else {
                reader = new InputStreamReader(stream, charset);
            }

            StringBuilder response = new StringBuilder();
            char[] buff = new char[1024];
            boolean read = false;

            int read1;
            while((read1 = reader.read(buff)) > 0) {
                response.append(buff, 0, read1);
            }

            String var7 = response.toString();
            return var7;
        } finally {
            if(stream != null) {
                stream.close();
            }

        }
    }

    private static String getResponseCharset(String ctype) {
        String charset = "UTF-8";
        if(!StringUtils.isEmpty(ctype)) {
            String[] params = ctype.split(";");
            String[] var3 = params;
            int var4 = params.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                String param = var3[var5];
                param = param.trim();
                if(param.startsWith("charset")) {
                    String[] pair = param.split("=", 2);
                    if(pair.length == 2 && !StringUtils.isEmpty(pair[1])) {
                        charset = pair[1].trim();
                    }
                    break;
                }
            }
        }

        return charset;
    }

    public static <T> T call(Config context, String action, Map<String, Object> parameters, Token token, Type type) throws ServiceException {
        long timestamp = System.currentTimeMillis() / 1000L;
        String appKey = context.getApp_key();
        String secret = context.getApp_secret();
        String accessToken = token.getAccessToken();
        String requestId = UUID.randomUUID().toString().toLowerCase();
        System.out.println("requestId: " + requestId);
        HashMap requestPayload = new HashMap();
        requestPayload.put("nop", "1.0.0");
        requestPayload.put("id", requestId);
        requestPayload.put("action", action);
        requestPayload.put("token", accessToken);
        HashMap metasHashMap = new HashMap();
        metasHashMap.put("app_key", appKey);
        metasHashMap.put("timestamp", Long.valueOf(timestamp));
        requestPayload.put("metas", metasHashMap);
        requestPayload.put("params", parameters);
        String signature = SignatureUtil.generateSignature(appKey, secret, timestamp, action, accessToken, parameters);
        requestPayload.put("signature", signature);
        String requestJson = JacksonUtils.obj2json(requestPayload);

        ResponsePayload responsePayload;
        try {
            responsePayload = doRequest(context, requestJson);
        } catch (SocketTimeoutException var18) {
            throw new SourceTimeoutException();
        } catch (IOException var19) {
            throw new ServiceException(var19.getClass().getName(), var19);
        }

        setLogInfo(context, "request: " + requestJson);
        if(responsePayload != null && null != responsePayload.getError()) {
            ServiceException s21 = toException(responsePayload.getError());
            if(s21 != null) {
                setLogError(context, "error: " + s21.getMessage());
                throw s21;
            } else {
                throw new ServerErrorException();
            }
        } else if(type == Void.TYPE) {
            return null;
        } else {
            String s2 = JacksonUtils.obj2json(responsePayload.getResult());
            JavaType javaType = JacksonUtils.getInstance().getTypeFactory().constructType(type);
            return JacksonUtils.json2pojo(s2, javaType);
        }
    }

    private static ResponsePayload doRequest(Config context, String requestJson) throws SocketTimeoutException, IOException {
        String response = doPost(context, context.getApiUrl(), "application/json; charset=utf-8", requestJson.getBytes("UTF-8"), 15000, 15000);
        setLogInfo(context, "response: " + response);
        return (ResponsePayload)JacksonUtils.json2pojo(response, ResponsePayload.class);
    }

    private static ServiceException toException(ErrorPayload error) throws ServiceException {
        String code = error.getCode();
        if(StringUtils.isEmpty(code)) {
            return null;
        } else {
            String message = error.getMessage();
            return (ServiceException)("ACCESS_DENIED".equals(code)?new AccessDeniedException(message):("EXCEED_LIMIT".equals(code)?new ExceedLimitException(message):("INVALID_SIGNATURE".equals(code)?new InvalidSignatureException(message):("INVALID_TIMESTAMP".equals(code)?new InvalidTimestampException(message):("METHOD_NOT_ALLOWED".equals(code)?new MethodNotAllowedException(message):("PERMISSION_DENIED".equals(code)?new PermissionDeniedException(message):("UNAUTHORIZED".equals(code)?new UnauthorizedException(message):("VALIDATION_FAILED".equals(code)?new ValidationFailedException(message):(code.startsWith("BIZ_")?new BusinessException(error.getCode(), error.getMessage()):null)))))))));
        }
    }

    private static void setLogInfo(Config context, String msg) {
        if(null != context.getQwSdkLogger()) {
            context.getQwSdkLogger().info(msg);
        }

    }

    private static void setLogError(Config context, String msg) {
        if(null != context.getQwSdkLogger()) {
            context.getQwSdkLogger().error(msg);
        }

    }

    private static class DefaultTrustManager implements X509TrustManager {
        private DefaultTrustManager() {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }
    }
}
