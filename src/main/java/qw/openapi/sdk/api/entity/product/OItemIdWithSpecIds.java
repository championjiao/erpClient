package qw.openapi.sdk.api.entity.product;

import java.util.List;

public class OItemIdWithSpecIds {
    private long itemId;
    private List<Long> itemSpecIds;

    public OItemIdWithSpecIds() {
    }

    public long getItemId() {
        return this.itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public List<Long> getItemSpecIds() {
        return this.itemSpecIds;
    }

    public void setItemSpecIds(List<Long> itemSpecIds) {
        this.itemSpecIds = itemSpecIds;
    }
}
