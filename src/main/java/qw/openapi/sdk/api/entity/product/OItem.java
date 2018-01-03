package qw.openapi.sdk.api.entity.product;

import java.util.List;

public class OItem {
    private String description;
    private long id;
    private String name;
    private int isValid;
    private int recentPopularity;
    private long categoryId;
    private long shopId;
    private String shopName;
    private String imageUrl;
    private OLabel labels;
    private List<OSpec> specs;
    private OItemSellingTime sellingTime;
    private List<OItemAttribute> attributes;
    private long backCategoryId;
    private int minPurchaseQuantity;
    private String unit;

    public OItem() {
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public int getIsValid() {
        return this.isValid;
    }

    public void setIsValid(int isValid) {
        this.isValid = isValid;
    }

    public int getRecentPopularity() {
        return this.recentPopularity;
    }

    public void setRecentPopularity(int recentPopularity) {
        this.recentPopularity = recentPopularity;
    }

    public long getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public long getShopId() {
        return this.shopId;
    }

    public void setShopId(long shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return this.shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public OLabel getLabels() {
        return this.labels;
    }

    public void setLabels(OLabel labels) {
        this.labels = labels;
    }

    public List<OSpec> getSpecs() {
        return this.specs;
    }

    public void setSpecs(List<OSpec> specs) {
        this.specs = specs;
    }

    public OItemSellingTime getSellingTime() {
        return this.sellingTime;
    }

    public void setSellingTime(OItemSellingTime sellingTime) {
        this.sellingTime = sellingTime;
    }

    public List<OItemAttribute> getAttributes() {
        return this.attributes;
    }

    public void setAttributes(List<OItemAttribute> attributes) {
        this.attributes = attributes;
    }

    public long getBackCategoryId() {
        return this.backCategoryId;
    }

    public void setBackCategoryId(long backCategoryId) {
        this.backCategoryId = backCategoryId;
    }

    public int getMinPurchaseQuantity() {
        return this.minPurchaseQuantity;
    }

    public void setMinPurchaseQuantity(int minPurchaseQuantity) {
        this.minPurchaseQuantity = minPurchaseQuantity;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
