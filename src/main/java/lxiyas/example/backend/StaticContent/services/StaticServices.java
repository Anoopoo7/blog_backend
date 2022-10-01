package lxiyas.example.backend.StaticContent.services;

import java.lang.reflect.Array;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;
import lxiyas.example.backend.Products.service.ProductService;
import lxiyas.example.backend.StaticContent.models.FeaturedItems;
import lxiyas.example.backend.StaticContent.models.StaticcontentView;
import lxiyas.example.backend.StaticContent.repository.StaicContentRepository;
import lxiyas.example.backend.StaticContent.utils.StaticConstants;
import lxiyas.example.backend.StaticContent.utils.StaticException;

@Service
@Log4j2
public class StaticServices {
    @Autowired
    private StaicContentRepository staicContentRepository;
    @Autowired
    private ProductService productService;

    public StaticcontentView createPageContentByPageType(StaticcontentView staticContent) throws Exception {
        if (null == staticContent) {
            throw new Exception(StaticException.INVALID_INPUT.name());
        }
        log.info("6222bb82-75da-4f49-9f40-9163e6d7a3ac", "saving static content for the page {}",
                staticContent.getPageType(), staticContent);
        staticContent.setCreatedDate(new Date());
        staticContent.setUpdatedDate(new Date());
        staticContent.setActive(true);
        staticContent = staicContentRepository.save(staticContent);
        return staticContent;
    }

    public StaticcontentView getPageContentByPageType(String type) throws Exception {
        if (null == type) {
            throw new Exception(StaticException.INVALID_INPUT.name());
        }
        StaticcontentView staticContent = staicContentRepository.findByPageTypeAndActive(type, true);
        if (null == staticContent) {
            log.debug("e121c223-619f-4474-b371-782b8669156c", "no page configuration find for {}",
                    type);
            throw new Exception(StaticException.NO_PAGE_CONFIGURATIONS_FOUND.name());
        }
        return staticContent;
    }

    public Object setFeaturedItems(List<String> itemList) throws Exception {
        if (null == itemList) {
            throw new Exception(StaticException.INVALID_INPUT.name());
        }
        StaticcontentView staticcontentView = staicContentRepository
                .findByPageTypeAndActive(StaticConstants.FEATURED_ITEMS, true);
        if (null == staticcontentView) {
            staticcontentView = new StaticcontentView();
            staticcontentView.setCreatedDate(new Date());
            staticcontentView.setActive(true);
            staticcontentView.setPageType(StaticConstants.FEATURED_ITEMS);
        }
        Map<Object, Object> content = new HashMap<Object, Object>();
        content.put(StaticConstants.FEATURED_PRODUCTS, new FeaturedItems(itemList));
        staticcontentView.setContent(content);
        staticcontentView.setUpdatedDate(new Date());
        log.debug("b7d1ef1b-5132-49ad-9390-ba82ac92f9d4", "saving static content view {}",
                staticcontentView);
        staicContentRepository.save(staticcontentView);
        return staticcontentView;
    }

    public Object getFeaturedItems() throws Exception {
        StaticcontentView staticcontentView = staicContentRepository
                .findByPageTypeAndActive(StaticConstants.FEATURED_ITEMS, true);
        if (null == staticcontentView) {
            throw new Exception(StaticException.NO_FEATURED_ITEMS_FOUND.name());
        }
        FeaturedItems content = (FeaturedItems) staticcontentView.getContent().get(StaticConstants.FEATURED_PRODUCTS);
        return productService.getProductsDetaildByIds(content.getItems());
    }

}
