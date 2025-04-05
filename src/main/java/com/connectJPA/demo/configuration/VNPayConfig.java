package com.connectJPA.demo.configuration;

import org.springframework.beans.factory.annotation.Value;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class VNPayConfig {
    @Value("${vnpay.tmnCode}")
    private String vnp_TmnCode;

    @Value("${vnpay.hashSecret}")
    private String vnp_HashSecret;

    @Value("${vnpay.payUrl}")
    private String vnp_PayUrl;

    @Value("${vnpay.returnUrl}")
    private String vnp_ReturnUrl;

    public static String generateVNPayUrl(long amount, String orderInfo) {
        try {
            Map<String, String> vnp_Params = new HashMap<>();
            vnp_Params.put("vnp_Version", "2.1.0");
            vnp_Params.put("vnp_Command", "pay");
            vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
            vnp_Params.put("vnp_Amount", String.valueOf(amount * 100));
            vnp_Params.put("vnp_CurrCode", "VND");
            vnp_Params.put("vnp_TxnRef", String.valueOf(System.currentTimeMillis()));
            vnp_Params.put("vnp_OrderInfo", orderInfo);
            vnp_Params.put("vnp_Locale", "vn");
            vnp_Params.put("vnp_ReturnUrl", vnp_ReturnUrl);
            vnp_Params.put("vnp_CreateDate", new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));

            List<String> fieldNames = new ArrayList<>(vnp_Params.keySet());
            Collections.sort(fieldNames);

            StringBuilder query = new StringBuilder();
            for (String fieldName : fieldNames) {
                query.append(URLEncoder.encode(fieldName, StandardCharsets.UTF_8))
                        .append("=")
                        .append(URLEncoder.encode(vnp_Params.get(fieldName), StandardCharsets.UTF_8))
                        .append("&");
            }
            query.deleteCharAt(query.length() - 1);

            return vnp_PayUrl + "?" + query.toString();
        } catch (Exception e) {
            throw new RuntimeException("Lỗi tạo URL thanh toán", e);
        }


    }

}

