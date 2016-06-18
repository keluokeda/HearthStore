package com.wangxiaqiwuhai.com.hearthstore.card;

/**
 * Created by Administrator on 2016/6/15.
 */
public abstract class AbsCard extends Card{
    @Override
    public int beforeTakeDamage(int damage, Card source, Card target) {
        return damage;
    }
}
