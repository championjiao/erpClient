package qw.openapi.sdk.api.entity.product;

import java.util.List;

public class OSupplyLink {
    private Integer type;
    private List<OSupplyLinkSpec> minorSpec;

    public OSupplyLink() {
    }

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<OSupplyLinkSpec> getMinorSpec() {
        return this.minorSpec;
    }

    public void setMinorSpec(List<OSupplyLinkSpec> minorSpec) {
        this.minorSpec = minorSpec;
    }
}
