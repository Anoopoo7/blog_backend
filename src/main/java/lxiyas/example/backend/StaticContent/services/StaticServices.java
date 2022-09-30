package lxiyas.example.backend.StaticContent.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;
import lxiyas.example.backend.StaticContent.models.StaticcontentView;
import lxiyas.example.backend.StaticContent.repository.StaicContentRepository;
import lxiyas.example.backend.StaticContent.utils.StaticException;

@Service
@Log4j2
public class StaticServices {
    @Autowired
    private StaicContentRepository staicContentRepository;

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

}
