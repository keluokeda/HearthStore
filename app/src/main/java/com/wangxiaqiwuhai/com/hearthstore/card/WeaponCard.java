package com.wangxiaqiwuhai.com.hearthstore.card;

/**
 * 武器卡
 */
public abstract class WeaponCard extends AbsCard {
    public WeaponCard(int cost,int health,int attack, CardHeroClass cardHeroClass, Quality quality, CharSequence cardName, CharSequence description){
        this.mCost=cost;
        this.mAttack=attack;
        this.mHealth=health;
        this.mCardHeroClass=cardHeroClass;
        this.mQuality=quality;
        this.mRace=Race.None;//武器牌没有种类
        this.mType=Type.Weapon;
        this.mCardName=cardName;
        this.mDescription=description;
    }
}
