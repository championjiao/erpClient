package qw.openapi.sdk.api.entity.product;

import java.util.List;

public class ClearStock {
    private Long shopId;
    private List<Long> itemIds;

    public ClearStock() {
    }

    public Long getShopId() {
        return this.shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public List<Long> getItemIds() {
        return this.itemIds;
    }

    public void setItemIds(List<Long> itemIds) {
        this.itemIds = itemIds;
    }
}
