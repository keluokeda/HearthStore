package com.wangxiaqiwuhai.com.hearthstore.card;

/**
 * 随从卡
 */
public abstract class MinionCard extends AbsCard{

    public MinionCard(int cost,int health,int attack,CardHeroClass cardHeroClass,Race race,Quality quality,CharSequence cardName,CharSequence description,boolean ownerIsHero){
        this.mHealth=health;
        this.mCost=cost;
        this.mAttack=attack;
        this.mCardHeroClass=cardHeroClass;
        this.mQuality=quality;
        this.mRace=race;
        this.mType=Type.Minion;
        this.mCardName=cardName;
        this.mDescription=description;
        this.ownerIsHero = ownerIsHero;
    }

    /**
     * 随从相互攻击
     */
    public static void minionAttack(MinionCard source,MinionCard target){
        source.mHealth=source.mHealth-target.mAttack;
        target.mHealth=target.mHealth-source.mAttack;
    }


}
