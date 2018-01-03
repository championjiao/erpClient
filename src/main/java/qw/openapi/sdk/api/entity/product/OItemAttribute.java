package qw.openapi.sdk.api.entity.product;

import java.util.List;

public class OItemAttribute {
    private String name;
    private List<String> details;

    public OItemAttribute() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getDetails() {
        return this.details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }
}
