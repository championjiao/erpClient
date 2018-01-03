package qw.openapi.sdk.api.entity.product;

import java.util.Map;

public class OItemIdWithSpecStock {
    private long itemId;
    private Map<Long, Integer> stockMap;

    public OItemIdWithSpecStock() {
    }

    public long getItemId() {
        return this.itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public Map<Long, Integer> getStockMap() {
        return this.stockMap;
    }

    public void setStockMap(Map<Long, Integer> stockMap) {
        this.stockMap = stockMap;
    }
}
