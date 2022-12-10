package lxiyas.example.backend.PricingEngine.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;
import lxiyas.example.backend.Orders.models.LineItem;
import lxiyas.example.backend.PricingEngine.models.PricingInfo;
import lxiyas.example.backend.PricingEngine.models.PricingTableView;
import lxiyas.example.backend.PricingEngine.repository.PricingTableRepository;
import lxiyas.example.backend.PricingEngine.utils.PricingConstants;

@Service
@Log4j2
public class PricingService {
    @Autowired
    private PricingTableRepository pricingTableRepository;

    public PricingInfo calculateBulkProductPrice(List<LineItem> lineItems) {
        log.info("3930653f-c789-4e4e-9f29-a453cbc73d51", "recieved product ids {}", lineItems);
        PricingInfo pricingInfo = new PricingInfo();
        PricingTableView pricingTableView = pricingTableRepository.findByCode(PricingConstants.pricing);
        int itemPrice = 0;
        for (LineItem lineItem : lineItems) {
            itemPrice += lineItem.getPrice() + (pricingTableView.getProductTax() * lineItem.getPrice() / 100);
        }
        pricingInfo.setTotalPrice(itemPrice);
        pricingInfo.setTax(pricingTableView.getProductTax() + pricingTableView.getOrderTax());
        int orderPrice = pricingInfo.getTotalPrice()
                + (pricingTableView.getOrderTax() * pricingInfo.getTotalPrice() / 100);
        pricingInfo.setTotalPrice(orderPrice);
        pricingInfo.setDeliveryCharge(
                orderPrice <= pricingTableView.getFreeDeliveryQualification() ? pricingTableView.getDeliveryCharge()
                        : 0);
        pricingInfo.setDiscount(
                orderPrice >= pricingTableView.getFreeDeliveryQualification() ? pricingTableView.getDeliveryCharge()
                        : 0);
        pricingInfo.setDeliveryCharge(pricingTableView.getDeliveryCharge());
        pricingInfo.setNetPrice(orderPrice + pricingInfo.getDeliveryCharge() - pricingInfo.getDiscount());
        log.info("84b0c111-0f29-47d7-8325-85cc5bc3f77f", "pricing info calculated for the order", pricingInfo);
        return pricingInfo;
    }
}
