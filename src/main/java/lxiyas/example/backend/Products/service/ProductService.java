package lxiyas.example.backend.Products.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;
import lxiyas.example.backend.Products.Utils.ProductExceptions;
import lxiyas.example.backend.Products.models.InventoryView;
import lxiyas.example.backend.Products.models.ProductRequestView;
import lxiyas.example.backend.Products.models.ProductView;
import lxiyas.example.backend.Products.repository.InventoryRepository;
import lxiyas.example.backend.Products.repository.ProductRepository;

@Service
@Log4j2
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private InventoryRepository inventoryRepository;

    public Object createProduct(ProductRequestView productRequestView) throws Exception {
        if (null == productRequestView)
            throw new Exception(ProductExceptions.INVALID_INPUT.name());
        ProductView productView = new ProductView();
        productView.setName(productRequestView.getName());
        productView.setShortDescription(productRequestView.getShortDescription());
        productView.setImages(productRequestView.getImages());
        productView.setLandingPageUrl(this.UrlFormatter(productRequestView.getName()));
        productView.setActive(productRequestView.isActive());
        productView.setOnSale(productRequestView.isOnSale());
        productView.setCreatedDate(new Date());
        productView.setUpdatedDate(new Date());
        log.info("f409b643-c6e2-49d9-af41-181501ad50b4", "saving product details {}", productView);
        productView = productRepository.save(productView);

        InventoryView inventoryView = new InventoryView();
        inventoryView.setProductId(productView.getId());
        inventoryView.setVarients(productRequestView.getVarients());
        inventoryView.setInfinite(productRequestView.isInfinite());
        inventoryView.setActive(productRequestView.isActive());
        inventoryView.setDefaultPrice(productRequestView.getDefaultPrice());
        inventoryView.setCreatedDate(new Date());
        inventoryView.setUpdatedDate(new Date());
        log.info("95ad9061-d2f9-4129-acf2-e26a4074e4ac", "saving inventory details {}", inventoryView);
        inventoryView = inventoryRepository.save(inventoryView);
        return Arrays.asList(productView, inventoryView);

    }

    private String UrlFormatter(String url) {
        return "/" + url.replace(" ", "-");
    }

    public Object getProductByUrl(String product) throws Exception {
        if (null == product)
            throw new Exception(ProductExceptions.INVALID_INPUT.name());
        ProductView productView = productRepository.findByLandingPageUrlAndActive(this.UrlFormatter(product), true);
        if (null == productView)
            throw new Exception(ProductExceptions.NO_ACTIVE_PRODUCTS_FOUND.name());
        InventoryView inventoryView = inventoryRepository.findByProductIdAndActive(productView.getId(), true);
        if (null == inventoryView)
            throw new Exception(ProductExceptions.NO_ACTIVE_INVENTORY_FOUND.name());
        log.info("45557866-955a-4c3a-831c-cfadeaa68ffa", "fetched product details {} and inventory details {}",
                productView, inventoryView);
        return Arrays.asList(productView, inventoryView);
    }

    public List<Object> getProductsDetaildByIds(List<String> productId) throws Exception {
        if (null == productId)
            throw new Exception(ProductExceptions.INVALID_INPUT.name());
        List<Object> productDetails = productId.stream().map(item -> {
            ProductView productView = null;
            InventoryView inventoryView = null;
            try {
                productView = productRepository.findByIdAndActive(item, true).get();
                inventoryView = inventoryRepository.findByProductIdAndActive(item, true);
            } catch (Exception e) {
                log.info("fad46c52-bf76-4dcb-93df-0e2081182057", "cannot find product {}", item);
            }
            return Arrays.asList(productView, inventoryView);
        }).collect(Collectors.toList());
        return productDetails;
    }

    public InventoryView getProductInventoryById(String id) throws Exception {
        if (null == id) {
            throw new Exception(ProductExceptions.INVALID_INPUT.name());
        }
        InventoryView inventoryView = inventoryRepository.findByProductIdAndActive(id, true);
        log.info("be0b2756-151a-49d8-a937-4f6727e30c16", "fetched inventory {} of product: {}", inventoryView, id);
        if (null == inventoryView) {
            throw new Exception(ProductExceptions.NO_ACTIVE_INVENTORY_FOUND.name());
        }
        return inventoryView;
    }
}
