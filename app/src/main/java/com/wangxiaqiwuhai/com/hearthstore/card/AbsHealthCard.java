package com.wangxiaqiwuhai.com.hearthstore.card;

import com.wangxiaqiwuhai.com.hearthstore.manager.GameManager;

/**
 *有血量的卡
 */
public abstract class AbsHealthCard extends AbsCard{



    /**
     * 最大攻击力
     */
    protected int mMaxAttack;

    /**
     * 最大血量
     */
    protected int mMaxHealth;

    public AbsHealthCard(Quality quality, Race race, CardHeroClass cardHeroClass, Type type, CharSequence cardName, CharSequence description, int cost, Sets sets, Hero hero, GameManager gameManager,int maxAttack,int maxHealth) {
        super(quality, race, cardHeroClass, type, cardName, description, cost, sets, hero, gameManager);
        this.mMaxAttack=maxAttack;
        this.mMaxHealth=maxHealth;

    }

    /**
     * 是否被冻结
     */
    protected boolean isFreeze;

    protected boolean toBeDestroy;

    /**
     * 当前攻击力
     */
    protected int currentAttack;

    /**
     * 当前血量
     */
    protected int currentHealth;


    public void takeDamage(int damage){

    }

    public boolean isFreeze() {
        return isFreeze;
    }

    public void setFreeze(boolean freeze) {
        this.isFreeze = freeze;
    }

    public void setToBeDestroy(boolean destroy) {
        this.toBeDestroy = destroy;
    }

    public int getCurrentAttack() {
        return currentAttack;
    }

    public void setCurrentAttack(int currentAttack) {
        this.currentAttack = currentAttack;
    }
}
