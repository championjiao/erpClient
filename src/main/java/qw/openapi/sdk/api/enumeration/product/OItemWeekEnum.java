package qw.openapi.sdk.api.enumeration.product;

public enum OItemWeekEnum {
    MONDAY("MONDAY"),
    TUESDAY("TUESDAY"),
    WEDNESDAY("WEDNESDAY"),
    THURSDAY("THURSDAY"),
    FRIDAY("FRIDAY"),
    SATURDAY("SATURDAY"),
    SUNDAY("SUNDAY");

    private String productDesc;

    private OItemWeekEnum(String productDesc) {
        this.productDesc = productDesc;
    }
}