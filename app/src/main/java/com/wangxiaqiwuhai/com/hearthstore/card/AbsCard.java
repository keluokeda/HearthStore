package com.wangxiaqiwuhai.com.hearthstore.card;

import com.wangxiaqiwuhai.com.hearthstore.manager.GameManager;

/**
 * Created by Administrator on 2016/6/15.
 */
public abstract class AbsCard extends Card{

    public AbsCard(Quality quality, Race race, CardHeroClass cardHeroClass, Type type, CharSequence cardName, CharSequence description, int cost, Sets sets, Hero hero, GameManager gameManager) {
        super(quality, race, cardHeroClass, type, cardName, description, cost, sets, hero, gameManager);
    }
}
