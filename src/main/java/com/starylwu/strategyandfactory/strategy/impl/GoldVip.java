package com.starylwu.strategyandfactory.strategy.impl;

import com.starylwu.strategyandfactory.annotation.PriceRegion;
import com.starylwu.strategyandfactory.strategy.CalPriceStrategy;
import org.springframework.stereotype.Component;

/**
 * 金牌会员
 */
@PriceRegion(min = 30000, max = 40000)
@Component
public class GoldVip implements CalPriceStrategy {

    /**
     * 金牌会员7折
     * @param orgincPrice
     * @return
     */
    @Override
    public Double calPrice(Double orgincPrice) {
        return orgincPrice * 0.7;
    }
}
