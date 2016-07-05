package com.wangxiaqiwuhai.com.hearthstore.card;

import com.wangxiaqiwuhai.com.hearthstore.manager.GameManager;

/**
 *法术牌
 */
public abstract class SpellCard extends AbsCard {
    public SpellCard(Quality quality, Race race, CardHeroClass cardHeroClass, Type type, CharSequence cardName, CharSequence description, int cost, Sets sets, Hero hero, GameManager gameManager) {
        super(quality, race, cardHeroClass, type, cardName, description, cost, sets, hero, gameManager);
    }
}
