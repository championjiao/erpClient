package qw.openapi.sdk.api.entity.message;

public class OMessage {
    private String requestId;
    private int type;
    private int appId;
    private String message;
    private long shopId;
    private long timestamp;
    private String signature;
    private long userId;

    public OMessage() {
    }

    public String getRequestId() {
        return this.requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getAppId() {
        return this.appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getShopId() {
        return this.shopId;
    }

    public void setShopId(long shopId) {
        this.shopId = shopId;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getSignature() {
        return this.signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public long getUserId() {
        return this.userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public static class Message {
        private String order_id;

        public Message() {
        }

        public String getOrder_id() {
            return this.order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }
    }
}
