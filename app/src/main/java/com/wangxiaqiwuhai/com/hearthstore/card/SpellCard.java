package com.wangxiaqiwuhai.com.hearthstore.card;

/**
 *法术牌
 */
public abstract class SpellCard extends AbsCard {
    public SpellCard(int cost,CardHeroClass cardHeroClass,Quality quality,CharSequence cardName,CharSequence description){
        this.mCost=cost;
        this.mCardHeroClass=cardHeroClass;
        this.mQuality=quality;
        this.mRace=Race.None;//法术牌没有种类
        this.mType=Type.Spell;
        this.mCardName=cardName;
        this.mDescription=description;
    }


}
