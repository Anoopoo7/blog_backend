package lxiyas.example.backend.Products.controller;

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

import lxiyas.example.backend.MainUtils.models.Response;
import lxiyas.example.backend.Products.models.ProductRequestView;
import lxiyas.example.backend.Products.service.ProductService;

@RestController
@RequestMapping("/product/api/v1")
@CrossOrigin(origins = "*")
public class ProdutControllerVersion1 {
    @Autowired
    private ProductService productService;

    @PostMapping("/product")
    public ResponseEntity<Response> createProduct(@RequestBody ProductRequestView productRequestView) throws Exception {
        return new ResponseEntity<>(
                new Response(true, productService.createProduct(productRequestView), "Product Saved Successfully"),
                HttpStatus.OK);
    }

    @GetMapping("/url/{product}")
    public ResponseEntity<Response> getProductByUrl(@PathVariable String product) throws Exception {
        return new ResponseEntity<>(
                new Response(true, productService.getProductByUrl(product), "Product fetched Successfully"),
                HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Response> getProductInventoryById(@PathVariable String id) throws Exception {
        return new ResponseEntity<>(
                new Response(true, productService.getProductInventoryById(id), "Product inventory fetched Successfully"),
                HttpStatus.OK);
    }
}
