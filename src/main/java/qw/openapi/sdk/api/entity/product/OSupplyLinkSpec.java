package qw.openapi.sdk.api.entity.product;

import java.util.List;

public class OSupplyLinkSpec {
    private String name;
    private List<String> value;

    public OSupplyLinkSpec() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getValue() {
        return this.value;
    }

    public void setValue(List<String> value) {
        this.value = value;
    }
}
