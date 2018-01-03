package qw.openapi.sdk.api.entity.product;

import qw.openapi.sdk.api.enumeration.product.OCategoryType;

import java.util.List;

public class OCategory {
    private long id;
    private String name;
    private String description;
    private int isValid;
    private long parentId;
    private OCategoryType categoryType;
    private List<OCategory> children;

    public OCategory() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIsValid() {
        return this.isValid;
    }

    public void setIsValid(int isValid) {
        this.isValid = isValid;
    }

    public long getParentId() {
        return this.parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public OCategoryType getCategoryType() {
        return this.categoryType;
    }

    public void setCategoryType(OCategoryType categoryType) {
        this.categoryType = categoryType;
    }

    public List<OCategory> getChildren() {
        return this.children;
    }

    public void setChildren(List<OCategory> children) {
        this.children = children;
    }
}
