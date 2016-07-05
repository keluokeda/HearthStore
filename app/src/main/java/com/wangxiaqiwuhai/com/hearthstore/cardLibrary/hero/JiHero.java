package com.wangxiaqiwuhai.com.hearthstore.cardLibrary.hero;

import com.wangxiaqiwuhai.com.hearthstore.card.Card;
import com.wangxiaqiwuhai.com.hearthstore.card.HeroCard;
import com.wangxiaqiwuhai.com.hearthstore.manager.GameManager;

/**
 * Created by Administrator on 2016/7/4.
 */
public class JiHero extends HeroCard {
    public JiHero(Hero hero, GameManager gameManager) {
        super(Quality.Free, Race.None, CardHeroClass.Mage, Type.Hero, "吉安娜", "机械", 0, Sets.BASIC, hero, gameManager, 0, 30);
    }

    @Override
    public TargetType getTargetType() {
        return null;
    }
}
