package qw.openapi.sdk.api.entity.product;

import java.util.List;

public class CategoryWithChildrenIds {
    private long parentId;
    private List<Long> childrenIds;

    public CategoryWithChildrenIds() {
    }

    public long getParentId() {
        return this.parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public List<Long> getChildrenIds() {
        return this.childrenIds;
    }

    public void setChildrenIds(List<Long> childrenIds) {
        this.childrenIds = childrenIds;
    }
}