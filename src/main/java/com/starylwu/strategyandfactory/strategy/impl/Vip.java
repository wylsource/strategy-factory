package com.starylwu.strategyandfactory.strategy.impl;

import com.starylwu.strategyandfactory.annotation.PriceRegion;
import com.starylwu.strategyandfactory.strategy.CalPriceStrategy;
import org.springframework.stereotype.Component;

/**
 * 普通会员
 */
@PriceRegion(min = 10000, max = 20000)
@Component
public class Vip implements CalPriceStrategy {

    /**
     * 普通会员九折
     */
    @Override
    public Double calPrice(Double orgincPrice) {
        return orgincPrice * 0.9;
    }
}
