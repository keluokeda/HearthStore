package com.wangxiaqiwuhai.com.hearthstore.card;

import com.wangxiaqiwuhai.com.hearthstore.manager.GameManager;

/**
 * 武器卡
 */
public abstract class WeaponCard extends AbsHealthCard {
    public WeaponCard(Quality quality, Race race, CardHeroClass cardHeroClass, Type type, CharSequence cardName, CharSequence description, int cost, Sets sets, Hero hero, GameManager gameManager, int maxAttack, int maxHealth) {
        super(quality, race, cardHeroClass, type, cardName, description, cost, sets, hero, gameManager, maxAttack, maxHealth);
    }
}
