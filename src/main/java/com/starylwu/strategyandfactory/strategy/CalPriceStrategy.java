package com.starylwu.strategyandfactory.strategy;

/**
 * 计算价格的策略接口
 */
public interface CalPriceStrategy {

    /**
     * 根据原价计算一个成交价并返回
     */
    Double calPrice(Double orgincPrice);
}
