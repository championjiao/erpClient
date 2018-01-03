package qw.openapi.sdk.api.entity.product;

public class OSpec {
    private long specId;
    private String name;
    private double price;
    private int stock;
    private int maxStock;
    private double packingFee;
    private int onShelf;
    private String extendCode;
    private String barCode;
    private Integer weight;
    private int activityLevel;
    private OSupplyLink supplyLink;

    public OSpec() {
    }

    public long getSpecId() {
        return this.specId;
    }

    public void setSpecId(long specId) {
        this.specId = specId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return this.stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getMaxStock() {
        return this.maxStock;
    }

    public void setMaxStock(int maxStock) {
        this.maxStock = maxStock;
    }

    public double getPackingFee() {
        return this.packingFee;
    }

    public void setPackingFee(double packingFee) {
        this.packingFee = packingFee;
    }

    public int getOnShelf() {
        return this.onShelf;
    }

    public void setOnShelf(int onShelf) {
        this.onShelf = onShelf;
    }

    public String getExtendCode() {
        return this.extendCode;
    }

    public void setExtendCode(String extendCode) {
        this.extendCode = extendCode;
    }

    public String getBarCode() {
        return this.barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public Integer getWeight() {
        return this.weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public int getActivityLevel() {
        return this.activityLevel;
    }

    public void setActivityLevel(int activityLevel) {
        this.activityLevel = activityLevel;
    }

    public OSupplyLink getSupplyLink() {
        return this.supplyLink;
    }

    public void setSupplyLink(OSupplyLink supplyLink) {
        this.supplyLink = supplyLink;
    }
}
