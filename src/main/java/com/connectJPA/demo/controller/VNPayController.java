package com.connectJPA.demo.controller;

import com.connectJPA.demo.configuration.VNPayConfig;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/vnpay")
public class VNPayController {

    @GetMapping("/pay")
    public Map<String, String> createPayment(@RequestParam long amount, @RequestParam String orderInfo) {
        String paymentUrl = VNPayConfig.generateVNPayUrl(amount, orderInfo);
        Map<String, String> response = new HashMap<>();
        response.put("paymentUrl", paymentUrl);
        return response;
    }

    @GetMapping("/return")
    public Map<String, String> paymentReturn(@RequestParam Map<String, String> queryParams) {
        return queryParams;
    }
}

