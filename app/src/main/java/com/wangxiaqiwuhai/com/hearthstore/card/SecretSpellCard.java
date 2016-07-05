package com.wangxiaqiwuhai.com.hearthstore.card;

import com.wangxiaqiwuhai.com.hearthstore.manager.GameManager;

/**
 * 奥秘卡牌 特殊的 可以存在于战场中的魔法卡
 */
public abstract class SecretSpellCard extends SpellCard {
    public SecretSpellCard(Quality quality, Race race, CardHeroClass cardHeroClass, Type type, CharSequence cardName, CharSequence description, int cost, Sets sets, Hero hero, GameManager gameManager) {
        super(quality, race, cardHeroClass, type, cardName, description, cost, sets, hero, gameManager);
    }
}
