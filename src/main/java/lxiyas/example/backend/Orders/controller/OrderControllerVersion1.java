package lxiyas.example.backend.Orders.controller;

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
import lxiyas.example.backend.Orders.models.ItemRequestView;
import lxiyas.example.backend.Orders.service.OrderService;

@RestController
@RequestMapping("/api/v1/order")
@CrossOrigin(origins = "*")
public class OrderControllerVersion1 {
    @Autowired
    private OrderService orderService;

    @PostMapping("/item")
    public ResponseEntity<Response> addItemToOrder(@RequestBody ItemRequestView ItemRequestView) throws Exception {
        return new ResponseEntity<>(
                new Response(true, orderService.addItemToOrder(ItemRequestView), "added item to order Successfully"),
                HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Response> getOrder(@PathVariable String userId) throws Exception {
        return new ResponseEntity<>(
                new Response(true, orderService.getOrder(userId), "fetched user order successfully"),
                HttpStatus.OK);
    }

}
