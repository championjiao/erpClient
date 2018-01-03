package qw.openapi.sdk.api.enumeration.product;

public enum OItemCreateProperty {
    name("name"),
    description("description"),
    imageHash("imageHash"),
    labels("labels"),
    specs("specs"),
    sellingTime("sellingTime"),
    attributes("attributes"),
    backCategoryId("backCategoryId"),
    minPurchaseQuantity("minPurchaseQuantity"),
    unit("unit");

    private String productDesc;

    private OItemCreateProperty(String productDesc) {
        this.productDesc = productDesc;
    }
}