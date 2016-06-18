package com.wangxiaqiwuhai.com.hearthstore.interfaces;

import com.wangxiaqiwuhai.com.hearthstore.card.Card;

/**
 * Created by Administrator on 2016/6/15.
 */
public interface IDamageExecuter {
    void executeDamage(Card source,Card target,int damage);
}
