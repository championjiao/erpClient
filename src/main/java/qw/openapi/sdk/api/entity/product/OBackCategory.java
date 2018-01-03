package qw.openapi.sdk.api.entity.product;

import java.util.List;

public class OBackCategory {
    private long id;
    private String name;
    private long parentId;
    private int lev;
    private List<OBackCategory> children;
    private boolean leaf;

    public OBackCategory() {
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

    public long getParentId() {
        return this.parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public int getLev() {
        return this.lev;
    }

    public void setLev(int lev) {
        this.lev = lev;
    }

    public List<OBackCategory> getChildren() {
        return this.children;
    }

    public void setChildren(List<OBackCategory> children) {
        this.children = children;
    }

    public boolean getLeaf() {
        return this.leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }
}
