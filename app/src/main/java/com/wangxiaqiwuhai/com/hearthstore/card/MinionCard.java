package com.wangxiaqiwuhai.com.hearthstore.card;


import com.wangxiaqiwuhai.com.hearthstore.manager.GameManager;

/**
 * 随从卡
 */
public abstract class MinionCard extends AbsHealthCard {


    public MinionCard(Quality quality, Race race, CardHeroClass cardHeroClass, CharSequence cardName, CharSequence description, int cost, Sets sets, Hero hero, GameManager gameManager, int maxAttack, int maxHealth) {
        super(quality, race, cardHeroClass, Type.Minion, cardName, description, cost, sets, hero, gameManager, maxAttack, maxHealth);
    }

    public boolean getDeathStatus() {
        return toBeDestroy || (mMaxHealth <= 0);
    }

    public void setMinionMaxHealth(int maxHealth) {
        this.mMaxHealth = maxHealth;
    }

    public int getMinionMaxHealth() {
        return this.mMaxHealth;
    }



}
