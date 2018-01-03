package qw.openapi.sdk.api.enumeration.product;

public enum OCategoryType {
    NORMAL("NORMAL"),
    REQUIRED("REQUIRED"),
    INDEPENDENT("INDEPENDENT");

    private String productDesc;

    private OCategoryType(String productDesc) {
        this.productDesc = productDesc;
    }
}