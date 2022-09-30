package lxiyas.example.backend.StaticContent;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("static/api/v1")
public class ControllerVersion1 {

    @GetMapping("/page")
  public String getPageContentByPageType() {
    return "HomePage";
  }
}
