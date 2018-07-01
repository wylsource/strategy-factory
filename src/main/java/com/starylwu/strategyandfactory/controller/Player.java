package com.starylwu.strategyandfactory.controller;

import com.starylwu.strategyandfactory.factory.CalPriceAnnoFactory;
import com.starylwu.strategyandfactory.strategy.CalPriceStrategy;

public class Player {

    /**
     * 客户在鹅厂消费的总额
     */
    private Double totalAmount = 0D;

    /**
     * 客户单次消费额
     */
    private Double amount = 0D;

    /**
     * 计算应交金额
     */
    private CalPriceStrategy calPriceStrategy = null;

    public void buy(Double amount){
        this.amount = amount;
        this.totalAmount += amount;
        calPriceStrategy = CalPriceAnnoFactory.instance().createCalPrice(this.totalAmount);
    }

    public Double calLastAmount(){
        return calPriceStrategy.calPrice(amount);
    }

}
