package com.starylwu.strategyandfactory.strategy.impl;

import com.starylwu.strategyandfactory.annotation.PriceRegion;
import com.starylwu.strategyandfactory.strategy.CalPriceStrategy;
import org.springframework.stereotype.Component;

/**
 * 超级会员
 */
@PriceRegion(min = 20000, max = 30000)
@Component
public class SuperVip implements CalPriceStrategy {

    /**
     * 超级会员8折
     * @param orgincPrice
     * @return
     */
    @Override
    public Double calPrice(Double orgincPrice) {
        return orgincPrice * 0.8;
    }
}
