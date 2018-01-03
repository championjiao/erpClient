package qw.openapi.sdk.api.service;

import qw.openapi.sdk.api.annotation.Service;
import qw.openapi.sdk.api.base.BaseNopService;
import qw.openapi.sdk.api.entity.product.CategoryWithChildrenIds;
import qw.openapi.sdk.api.entity.product.ClearStock;
import qw.openapi.sdk.api.entity.product.OBackCategory;
import qw.openapi.sdk.api.entity.product.OCategory;
import qw.openapi.sdk.api.entity.product.OItem;
import qw.openapi.sdk.api.entity.product.OItemIdWithSpecIds;
import qw.openapi.sdk.api.entity.product.OItemIdWithSpecPrice;
import qw.openapi.sdk.api.entity.product.OItemIdWithSpecStock;
import qw.openapi.sdk.api.enumeration.product.OCategoryType;
import qw.openapi.sdk.api.enumeration.product.OItemCreateProperty;
import qw.openapi.sdk.api.enumeration.product.OItemUpdateProperty;
import qw.openapi.sdk.api.exception.ServiceException;
import qw.openapi.sdk.config.Config;
import qw.openapi.sdk.oauth.response.Token;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("eleme.product")
public class ProductService extends BaseNopService {

    //构造函数 传入token和请求地址  所有请求数据都有父类完成  这里只需要处理具体的业务逻辑
    public ProductService(Config config, Token token) {
        super(config, token, ProductService.class);
    }

    public String uploadImage(String image) throws ServiceException {
        HashMap params = new HashMap();
        params.put("image", image);
        return (String)this.call("eleme.file.uploadImage", params);
    }

    public String uploadImageWithRemoteUrl(String url) throws ServiceException {
        HashMap params = new HashMap();
        params.put("url", url);
        return (String)this.call("eleme.file.uploadImageWithRemoteUrl", params);
    }

    public String getUploadedUrl(String hash) throws ServiceException {
        HashMap params = new HashMap();
        params.put("hash", hash);
        return (String)this.call("eleme.file.getUploadedUrl", params);
    }

    public List<OCategory> getShopCategories(long shopId) throws ServiceException {
        HashMap params = new HashMap();
        params.put("shopId", Long.valueOf(shopId));
        return (List)this.call("eleme.product.category.getShopCategories", params);
    }

    public List<OCategory> getShopCategoriesWithChildren(long shopId) throws ServiceException {
        HashMap params = new HashMap();
        params.put("shopId", Long.valueOf(shopId));
        return (List)this.call("eleme.product.category.getShopCategoriesWithChildren", params);
    }

    public OCategory getCategory(Long categoryId) throws ServiceException {
        HashMap params = new HashMap();
        params.put("categoryId", categoryId);
        return (OCategory)this.call("eleme.product.category.getCategory", params);
    }

    public OCategory getCategoryWithChildren(long categoryId) throws ServiceException {
        HashMap params = new HashMap();
        params.put("categoryId", Long.valueOf(categoryId));
        return (OCategory)this.call("eleme.product.category.getCategoryWithChildren", params);
    }

    public OCategory createCategory(long shopId, String name, String description) throws ServiceException {
        HashMap params = new HashMap();
        params.put("shopId", Long.valueOf(shopId));
        params.put("name", name);
        params.put("description", description);
        return (OCategory)this.call("eleme.product.category.createCategory", params);
    }

    public OCategory createCategoryWithChildren(long shopId, String name, long parentId, String description) throws ServiceException {
        HashMap params = new HashMap();
        params.put("shopId", Long.valueOf(shopId));
        params.put("name", name);
        params.put("parentId", Long.valueOf(parentId));
        params.put("description", description);
        return (OCategory)this.call("eleme.product.category.createCategoryWithChildren", params);
    }

    public OCategory updateCategory(long categoryId, String name, String description) throws ServiceException {
        HashMap params = new HashMap();
        params.put("categoryId", Long.valueOf(categoryId));
        params.put("name", name);
        params.put("description", description);
        return (OCategory)this.call("eleme.product.category.updateCategory", params);
    }

    public OCategory updateCategoryWithChildren(long categoryId, String name, long parentId, String description) throws ServiceException {
        HashMap params = new HashMap();
        params.put("categoryId", Long.valueOf(categoryId));
        params.put("name", name);
        params.put("parentId", Long.valueOf(parentId));
        params.put("description", description);
        return (OCategory)this.call("eleme.product.category.updateCategoryWithChildren", params);
    }

    public OCategory removeCategory(long categoryId) throws ServiceException {
        HashMap params = new HashMap();
        params.put("categoryId", Long.valueOf(categoryId));
        return (OCategory)this.call("eleme.product.category.removeCategory", params);
    }

    public void setCategoryPositions(Long shopId, List<Long> categoryIds) throws ServiceException {
        HashMap params = new HashMap();
        params.put("shopId", shopId);
        params.put("categoryIds", categoryIds);
        this.call("eleme.product.category.setCategoryPositions", params);
    }

    public void setCategoryPositionsWithChildren(Long shopId, List<CategoryWithChildrenIds> categoryWithChildrenIds) throws ServiceException {
        HashMap params = new HashMap();
        params.put("shopId", shopId);
        params.put("categoryWithChildrenIds", categoryWithChildrenIds);
        this.call("eleme.product.category.setCategoryPositionsWithChildren", params);
    }

    public List<OBackCategory> getBackCategory(Long shopId) throws ServiceException {
        HashMap params = new HashMap();
        params.put("shopId", shopId);
        return (List)this.call("eleme.product.category.getBackCategory", params);
    }

    public void setCategoryType(Long shopId, Long categoryId, OCategoryType categoryType) throws ServiceException {
        HashMap params = new HashMap();
        params.put("shopId", shopId);
        params.put("categoryId", categoryId);
        params.put("categoryType", categoryType);
        this.call("eleme.product.category.setCategoryType", params);
    }

    public Map<Long, OItem> getItemsByCategoryId(long categoryId) throws ServiceException {
        HashMap params = new HashMap();
        params.put("categoryId", Long.valueOf(categoryId));
        return (Map)this.call("eleme.product.item.getItemsByCategoryId", params);
    }

    public OItem getItem(long itemId) throws ServiceException {
        HashMap params = new HashMap();
        params.put("itemId", Long.valueOf(itemId));
        return (OItem)this.call("eleme.product.item.getItem", params);
    }

    public Map<Long, OItem> batchGetItems(List<Long> itemIds) throws ServiceException {
        HashMap params = new HashMap();
        params.put("itemIds", itemIds);
        return (Map)this.call("eleme.product.item.batchGetItems", params);
    }

    public OItem createItem(long categoryId, Map<OItemCreateProperty, Object> properties) throws ServiceException {
        HashMap params = new HashMap();
        params.put("categoryId", Long.valueOf(categoryId));
        params.put("properties", properties);
        return (OItem)this.call("eleme.product.item.createItem", params);
    }

    public Map<Long, OItem> batchCreateItems(long categoryId, List<Map<OItemCreateProperty, Object>> items) throws ServiceException {
        HashMap params = new HashMap();
        params.put("categoryId", Long.valueOf(categoryId));
        params.put("items", items);
        return (Map)this.call("eleme.product.item.batchCreateItems", params);
    }

    public OItem updateItem(long itemId, long categoryId, Map<OItemUpdateProperty, Object> properties) throws ServiceException {
        HashMap params = new HashMap();
        params.put("itemId", Long.valueOf(itemId));
        params.put("categoryId", Long.valueOf(categoryId));
        params.put("properties", properties);
        return (OItem)this.call("eleme.product.item.updateItem", params);
    }

    public void batchFillStock(List<OItemIdWithSpecIds> specIds) throws ServiceException {
        HashMap params = new HashMap();
        params.put("specIds", specIds);
        this.call("eleme.product.item.batchFillStock", params);
    }

    public void batchClearStock(List<OItemIdWithSpecIds> specIds) throws ServiceException {
        HashMap params = new HashMap();
        params.put("specIds", specIds);
        this.call("eleme.product.item.batchClearStock", params);
    }

    public void batchOnShelf(List<OItemIdWithSpecIds> specIds) throws ServiceException {
        HashMap params = new HashMap();
        params.put("specIds", specIds);
        this.call("eleme.product.item.batchOnShelf", params);
    }

    public void batchOffShelf(List<OItemIdWithSpecIds> specIds) throws ServiceException {
        HashMap params = new HashMap();
        params.put("specIds", specIds);
        this.call("eleme.product.item.batchOffShelf", params);
    }

    public OItem removeItem(long itemId) throws ServiceException {
        HashMap params = new HashMap();
        params.put("itemId", Long.valueOf(itemId));
        return (OItem)this.call("eleme.product.item.removeItem", params);
    }

    public Map<Long, OItem> batchRemoveItems(List<Long> itemIds) throws ServiceException {
        HashMap params = new HashMap();
        params.put("itemIds", itemIds);
        return (Map)this.call("eleme.product.item.batchRemoveItems", params);
    }

    public void batchUpdateSpecStocks(List<OItemIdWithSpecStock> specStocks) throws ServiceException {
        HashMap params = new HashMap();
        params.put("specStocks", specStocks);
        this.call("eleme.product.item.batchUpdateSpecStocks", params);
    }

    public void setItemPositions(Long categoryId, List<Long> itemIds) throws ServiceException {
        HashMap params = new HashMap();
        params.put("categoryId", categoryId);
        params.put("itemIds", itemIds);
        this.call("eleme.product.item.setItemPositions", params);
    }

    public void clearAndTimingMaxStock(List<ClearStock> clearStocks) throws ServiceException {
        HashMap params = new HashMap();
        params.put("clearStocks", clearStocks);
        this.call("eleme.product.item.clearAndTimingMaxStock", params);
    }

    public OItem getItemByShopIdAndExtendCode(Long shopId, String extendCode) throws ServiceException {
        HashMap params = new HashMap();
        params.put("shopId", shopId);
        params.put("extendCode", extendCode);
        return (OItem)this.call("eleme.product.item.getItemByShopIdAndExtendCode", params);
    }

    public OItem getItemsByShopIdAndBarCode(Long shopId, String barCode) throws ServiceException {
        HashMap params = new HashMap();
        params.put("shopId", shopId);
        params.put("barCode", barCode);
        return (OItem)this.call("eleme.product.item.getItemsByShopIdAndBarCode", params);
    }

    public void batchUpdatePrices(Long shopId, List<OItemIdWithSpecPrice> specPrices) throws ServiceException {
        HashMap params = new HashMap();
        params.put("shopId", shopId);
        params.put("specPrices", specPrices);
        this.call("eleme.product.item.batchUpdatePrices", params);
    }

    public List<Long> getItemIdsHasActivityByShopId(Long shopId) throws ServiceException {
        HashMap params = new HashMap();
        params.put("shopId", shopId);
        return (List)this.call("eleme.product.item.getItemIdsHasActivityByShopId", params);
    }

    public void setOrderPackingFee(Long shopId, boolean status, Double packingFee) throws ServiceException {
        HashMap params = new HashMap();
        params.put("shopId", shopId);
        params.put("status", Boolean.valueOf(status));
        params.put("packingFee", packingFee);
        this.call("eleme.product.item.setOrderPackingFee", params);
    }
}