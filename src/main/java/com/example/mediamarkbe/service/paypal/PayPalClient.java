package com.example.mediamarkbe.service.paypal;

import com.example.mediamarkbe.model.Orders;
import com.example.mediamarkbe.respository.OrderRepository;
import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class PayPalClient {
    @Autowired
    private OrderRepository orderRepository;

    private String clientId = "AYNPom_WbRcp9PsMzOEOmCqvdhGf2P9sL0q3askXLa7gH6KSWxBO1XYvVBtijbwn0EV0HcrcVPWXbLTk";
    private String clientSecret = "EI-kHMtmPWuh4iZDIG8_gfCQL56dhYzGcidIRErgNJSCq3b77rja1SPej-z3cOPOLuhFizNCECIFt9Do";

    public Map<String, Object> createPayment(String sum,Long idOrder){
        Map<String, Object> response = new HashMap<String, Object>();
        Amount amount = new Amount();
        amount.setCurrency("USD");
        amount.setTotal(sum);
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        List<Transaction> transactions = new ArrayList<Transaction>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        Payment payment = new Payment();
        payment.setIntent("sale");
        payment.setPayer(payer);
        payment.setTransactions(transactions);

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("http://localhost:3000/");
        redirectUrls.setReturnUrl("http://localhost:3000/confirmation/?idOrder="+idOrder);
        payment.setRedirectUrls(redirectUrls);
        Payment createdPayment;
        try {
            String redirectUrl = "";
            APIContext context = new APIContext(clientId, clientSecret, "sandbox");
            createdPayment = payment.create(context);
            if(createdPayment!=null){
                List<Links> links = createdPayment.getLinks();
                for (Links link:links) {
                    if(link.getRel().equals("approval_url")){
                        redirectUrl = link.getHref();
                        break;
                    }
                }
                response.put("status", "success");
                response.put("redirect_url", redirectUrl);
            }
        } catch (PayPalRESTException e) {
            System.out.println("Error happened during payment creation!");
        }
        return response;
    }


    public Map<String, Object> completePayment(HttpServletRequest req){
        Map<String, Object> response = new HashMap();
        Payment payment = new Payment();
        payment.setId(req.getParameter("paymentId"));

        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(req.getParameter("PayerID"));

        Long idOrder = Long.valueOf(req.getParameter("idOrder"));
        try {
            APIContext context = new APIContext(clientId, clientSecret, "sandbox");
            Payment createdPayment = payment.execute(context, paymentExecution);
            if(createdPayment!=null){
                response.put("status", "success");
                System.out.println(createdPayment);

                Orders order = orderRepository.findById(idOrder).get();
                order.setStatus(true);
                orderRepository.save(order);
            }
        } catch (PayPalRESTException e) {
            System.err.println(e.getDetails());
        }
        return response;
    }



}
