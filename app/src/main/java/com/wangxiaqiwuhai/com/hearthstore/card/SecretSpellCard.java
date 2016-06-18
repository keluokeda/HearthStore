package com.wangxiaqiwuhai.com.hearthstore.card;

/**
 * 奥秘卡牌 特殊的 可以存在于战场中的魔法卡
 */
public abstract class SecretSpellCard extends SpellCard {
    public SecretSpellCard(int cost, CardHeroClass cardHeroClass, Quality quality, CharSequence cardName, CharSequence description) {
        super(cost, cardHeroClass, quality, cardName, description);
    }
}
