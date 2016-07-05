package com.wangxiaqiwuhai.com.hearthstore.card;

/**
 * 卡片封装类
 */
public class CardWrapper {
    protected AbsHealthCard originalCard;
    protected AbsHealthCard finalCard;


    private int damage;

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public AbsHealthCard getOriginalCard() {
        return originalCard;
    }

    public void setOriginalCard(AbsHealthCard originalCard) {
        this.originalCard = originalCard;
    }

    public AbsHealthCard getFinalCard() {
        return finalCard;
    }

    public void setFinalCard(AbsHealthCard finalCard) {
        this.finalCard = finalCard;
    }
}
