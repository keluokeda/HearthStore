package com.wangxiaqiwuhai.com.hearthstore.card;

import java.util.Objects;

/**
 *英雄
 */
public abstract class HeroCard extends AbsCard{
    int heroImageResource;
    public HeroCard(int health,CardHeroClass cardHeroClass,Quality quality,Race race,CharSequence cardName,CharSequence description){
        this.mHealth=health;
        this.mCost=0;
        this.mAttack=0;
        this.mCardHeroClass=cardHeroClass;
        this.mQuality=quality;
        this.mRace=race;
        this.mType=Type.Hero;
        this.mCardName=cardName;
        this.mDescription=description;

    }

    @Override
    public void onTurnStart() {

    }

    @Override
    public void onTurnEnd() {

    }



    @Override
    public void beforeUseCard(Card card, Objects target) {

    }

    @Override
    public void onUseCard(Card card, Objects target) {

    }

    @Override
    public void afterUseCard(Card card, Objects target) {

    }

    @Override
    public void beforeAttack(Objects source, Objects target) {

    }

    @Override
    public void onAttack(Objects source, Objects target) {

    }

    @Override
    public void afterAttack(Objects source, Objects target) {

    }

    @Override
    public void takeDamage(int damage, TargetType targetType) {

    }
}
