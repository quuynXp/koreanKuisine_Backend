package com.connectJPA.demo.enums;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public enum PromoCode {

    koreanKuisine("koreanKuisine", 10),
    vip("vip", 20);

    private final String promoCode;
    private final int discount;

    PromoCode(String promoCode, int discount) {
        this.promoCode = promoCode;
        this.discount = discount;
    }

    public static int findDiscount(String promoCode) {
        for (PromoCode code : PromoCode.values()) {
            if (code.getPromoCode().equalsIgnoreCase(promoCode)) {
                return code.getDiscount();
            }
        }
        return 0;
    }
}

