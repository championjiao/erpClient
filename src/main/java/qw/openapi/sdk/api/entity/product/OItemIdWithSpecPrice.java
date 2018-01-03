package qw.openapi.sdk.api.entity.product;

import java.util.Map;

public class OItemIdWithSpecPrice {
    private long itemId;
    private Map<String, Double> priceMap;

    public OItemIdWithSpecPrice() {
    }

    public long getItemId() {
        return this.itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public Map<String, Double> getPriceMap() {
        return this.priceMap;
    }

    public void setPriceMap(Map<String, Double> priceMap) {
        this.priceMap = priceMap;
    }
}