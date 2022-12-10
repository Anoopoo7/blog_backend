package lxiyas.example.backend.Orders.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;
import lxiyas.example.backend.Orders.models.ItemRequestView;
import lxiyas.example.backend.Orders.models.LineItem;
import lxiyas.example.backend.Orders.models.Order;
import lxiyas.example.backend.Orders.repository.OrderRepository;
import lxiyas.example.backend.Orders.utils.OrderExceptions;
import lxiyas.example.backend.Orders.utils.OrderStatus;
import lxiyas.example.backend.PricingEngine.models.PricingInfo;
import lxiyas.example.backend.PricingEngine.service.PricingService;
import lxiyas.example.backend.Products.models.InventoryView;
import lxiyas.example.backend.Products.models.ProductView;
import lxiyas.example.backend.Products.models.VarientView;
import lxiyas.example.backend.Products.service.ProductService;
import lxiyas.example.backend.Users.models.User;
import lxiyas.example.backend.Users.service.UserService;

@Service
@Log4j2
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private PricingService pricingService;

    private Order validateAndAddingProduct(Order order, String productId, int quantity, String varient)
            throws Exception {
        ProductView product = productService.getProductById(productId);
        InventoryView inventory = productService.getProductInventoryById(productId);
        if (product.isActive() && product.isOnSale()) {
            VarientView varients = inventory.getVarients().get(varient);
            if (null == varients) {
                throw new Exception(OrderExceptions.INVALID_VARITENT.name());
            }
            int stock = varients.getQuantity();
            if (stock < quantity) {
                throw new Exception(OrderExceptions.INSUFFICIENT_QUANTITY.name());
            }
            int price = quantity * varients.getSalePrice();
            List<LineItem> lineItems = new ArrayList<LineItem>();
            if (null != order.getLineItems()) {
                System.out.println(varient);
                lineItems = order.getLineItems().stream()
                        .filter(item -> (!item.getProductId().equals(productId) || !item.getVarient().equals(varient)))
                        .collect(Collectors.toList());
            }
            LineItem lineItem = new LineItem();
            lineItem.setName(product.getName());
            lineItem.setPrice(price);
            lineItem.setProductId(product.getId());
            lineItem.setLandingPageUrl(product.getLandingPageUrl());
            lineItem.setQuantity(quantity);
            lineItem.setVarient(varient);
            lineItems.add(lineItem);
            order.setLineItems(lineItems);
            log.info("74e7e508-d4bb-4f54-b4c9-837f547bb279", "saving product {} to order {}", lineItem, order);
            return orderRepository.save(order);
        }
        throw new Exception(OrderExceptions.INVALID_PRODUCT.name());

    }

    public Order addItemToOrder(ItemRequestView itemRequestView) throws Exception {
        log.info("7e4b7df1-39c4-4faa-b95e-f8af3b828696", "recieved a request {}", itemRequestView);
        if (null == itemRequestView.getUserId()) {
            throw new Exception(OrderExceptions.INVALID_INPUT.name());
        }
        User user = userService.getUserById(itemRequestView.getUserId());
        if (null == user) {
            log.debug("7e4b7df1-39c4-4faa-b95e-f8af3b828696", "user not found with id", itemRequestView.getUserId());
            throw new Exception(OrderExceptions.USER_DO_NOT_EXISTS.name());
        }
        Order order = new Order();
        Order currentOrder = orderRepository.findByUserIdAndStatus(user.getId(), OrderStatus.INCOMPLETED.name());
        if (null != currentOrder) {
            log.info("8d064e55-7a64-4d41-aa75-b979095cfc68", "fetched user older order {}", order);
            order = currentOrder;
            order.setModifiedDate(new Date());
        } else {
            order.setUserId(user.getId());
            order.setStatus(OrderStatus.INCOMPLETED);
            order.setActive(true);
            order.setCreatedDate(new Date());
            order.setModifiedDate(new Date());
        }
        Order newOrder = validateAndAddingProduct(order, itemRequestView.getProductId(), itemRequestView.getQuantity(),
                itemRequestView.getVarient());
        PricingInfo pricingInfo = pricingService.calculateBulkProductPrice(newOrder.getLineItems());
        newOrder.setPricingInfo(pricingInfo);
        return newOrder;
    }

    public Object getOrder(String userId) throws Exception {
        if (null == userId) {
            throw new Exception(OrderExceptions.INVALID_INPUT.name());
        }
        Order currentOrder = orderRepository.findByUserIdAndStatus(userId, OrderStatus.INCOMPLETED.name());
        log.info("de2c035c-d99c-44b9-a03e-54848d3ae94e", "fetched user older order {}", currentOrder);
        if (null != currentOrder) {
            PricingInfo pricingInfo = pricingService.calculateBulkProductPrice(currentOrder.getLineItems());
            currentOrder.setPricingInfo(pricingInfo);
            return currentOrder;
        }
        return null;
    }
}
