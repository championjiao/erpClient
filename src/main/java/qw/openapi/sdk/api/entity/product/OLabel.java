package qw.openapi.sdk.api.entity.product;

public class OLabel {
    private int isFeatured;
    private int isGum;
    private int isNew;
    private int isSpicy;

    public OLabel() {
    }

    public int getIsFeatured() {
        return this.isFeatured;
    }

    public void setIsFeatured(int isFeatured) {
        this.isFeatured = isFeatured;
    }

    public int getIsGum() {
        return this.isGum;
    }

    public void setIsGum(int isGum) {
        this.isGum = isGum;
    }

    public int getIsNew() {
        return this.isNew;
    }

    public void setIsNew(int isNew) {
        this.isNew = isNew;
    }

    public int getIsSpicy() {
        return this.isSpicy;
    }

    public void setIsSpicy(int isSpicy) {
        this.isSpicy = isSpicy;
    }
}
