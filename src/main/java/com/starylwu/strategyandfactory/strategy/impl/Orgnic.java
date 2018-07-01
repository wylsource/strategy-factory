package com.starylwu.strategyandfactory.strategy.impl;

import com.starylwu.strategyandfactory.annotation.PriceRegion;
import com.starylwu.strategyandfactory.strategy.CalPriceStrategy;
import org.springframework.stereotype.Component;

/**
 * 普通玩家
 */
@PriceRegion(max = 10000)
@Component
public class Orgnic implements CalPriceStrategy {

    //原价返回
    @Override
    public Double calPrice(Double orgincPrice) {
        return orgincPrice;
    }
}
