package qw.openapi.sdk.api.enumeration.product;

public enum OItemUpdateProperty {
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

    private OItemUpdateProperty(String productDesc) {
        this.productDesc = productDesc;
    }
}