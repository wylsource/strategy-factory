package com.starylwu.strategyandfactory.controller;

import com.starylwu.strategyandfactory.factory.CalPriceAnnoFactory;
import com.starylwu.strategyandfactory.strategy.CalPriceStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Wuyulong
 * @Date: 2018/7/1 11:15
 * @Description:
 */
@RestController
public class PlayerController {

    @GetMapping("/play")
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
