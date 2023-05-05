package com.example.mediamarkbe.controller;
import com.example.mediamarkbe.service.paypal.PayPalClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping(value = "/paypal")
public class PayPalController {

    private final PayPalClient payPalClient;
    @Autowired
    PayPalController(PayPalClient payPalClient){
        this.payPalClient = payPalClient;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value = "/make/payment")
    public Map<String, Object> makePayment(@RequestParam("sum") String sum, @RequestParam("idOrder") Long idOrder){
        System.out.println("có một request");
        return payPalClient.createPayment(sum,idOrder);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value = "/complete/payment")
    public Object completePayment(HttpServletRequest request, @RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId, @RequestParam("idOrder") Long idOrder){
        return payPalClient.completePayment(request);
    }


}
