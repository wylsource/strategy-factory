package com.starylwu.strategyandfactory;

import com.starylwu.strategyandfactory.factory.CalPriceAnnoFactory;
import com.starylwu.strategyandfactory.factory.CalPriceFactory;
import com.starylwu.strategyandfactory.strategy.CalPriceStrategy;
import com.starylwu.strategyandfactory.strategy.impl.GoldVip;
import com.starylwu.strategyandfactory.strategy.impl.Orgnic;
import com.starylwu.strategyandfactory.strategy.impl.SuperVip;
import com.starylwu.strategyandfactory.strategy.impl.Vip;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
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

    @Test
    public void player(){
        Player player = new Player();
        player.buy(5000D);
        System.out.printf("玩家需要付钱：%s\n", player.calLastAmount());
        player.buy(10000D);
        System.out.printf("玩家需要付钱：%s\n", player.calLastAmount());
        player.buy(10000D);
        System.out.printf("玩家需要付钱：%s\n", player.calLastAmount());
        player.buy(10000D);
        System.out.printf("玩家需要付钱：%s\n", player.calLastAmount());
    }
}
