package com.wangxiaqiwuhai.com.hearthstore.card;

import com.wangxiaqiwuhai.com.hearthstore.manager.GameManager;

import java.util.Objects;

/**
 *英雄
 */
public abstract class HeroCard extends AbsHealthCard{
    public HeroCard(Quality quality, Race race, CardHeroClass cardHeroClass, Type type, CharSequence cardName, CharSequence description, int cost, Sets sets, Hero hero, GameManager gameManager, int maxAttack, int maxHealth) {
        super(quality, race, cardHeroClass, type, cardName, description, cost, sets, hero, gameManager, maxAttack, maxHealth);
    }
}
