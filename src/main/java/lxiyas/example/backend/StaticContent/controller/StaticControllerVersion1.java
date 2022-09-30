package lxiyas.example.backend.StaticContent.controller;

import lxiyas.example.backend.MainUtils.models.Response;
import lxiyas.example.backend.StaticContent.models.StaticcontentView;
import lxiyas.example.backend.StaticContent.services.StaticServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("static/api/v1")
@CrossOrigin(origins = "*")
public class StaticControllerVersion1 {
    @Autowired
    private StaticServices staticServices;

    @GetMapping("/page/{type}")
    public ResponseEntity<Response> getPageContentByPageType(@PathVariable String type) throws Exception {
        return new ResponseEntity<>(
                new Response(true, staticServices.getPageContentByPageType(type), "page details fetched successfully"),
                HttpStatus.OK);
    }

    @PostMapping("/page")
    public ResponseEntity<Response> createPageContentByPageType(
            @RequestBody StaticcontentView StaticContent) throws Exception {
        return new ResponseEntity<>(
                new Response(true, staticServices.createPageContentByPageType(StaticContent),
                        "page updated successfully"),
                HttpStatus.OK);
    }
}
