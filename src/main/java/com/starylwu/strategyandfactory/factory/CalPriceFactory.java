package com.starylwu.strategyandfactory.factory;

import com.starylwu.strategyandfactory.strategy.CalPriceStrategy;
import com.starylwu.strategyandfactory.strategy.impl.GoldVip;
import com.starylwu.strategyandfactory.strategy.impl.Orgnic;
import com.starylwu.strategyandfactory.strategy.impl.SuperVip;
import com.starylwu.strategyandfactory.strategy.impl.Vip;

/**
 * 计算对象工厂
 */
public class CalPriceFactory {


    private CalPriceFactory(){}



    /**
     * 获取价格计算类
     * @param totalAmount
     * @return
     */
    public static CalPriceStrategy calPrice(Double totalAmount){
        CalPriceStrategy calPriceStrategy;
        if (totalAmount > 30000){
            //金牌会员
            calPriceStrategy = new GoldVip();
        }else if (totalAmount > 20000){
            //超级会员
            calPriceStrategy = new SuperVip();
        }else if (totalAmount > 10000){
            //普通会员
            calPriceStrategy = new Vip();
        }else {
            //普通玩家
            calPriceStrategy = new Orgnic();
        }
        return calPriceStrategy;
    }

}
