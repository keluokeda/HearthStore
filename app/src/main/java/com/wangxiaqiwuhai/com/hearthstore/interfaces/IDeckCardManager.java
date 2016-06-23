package com.wangxiaqiwuhai.com.hearthstore.interfaces;


import com.wangxiaqiwuhai.com.hearthstore.card.Card;

/**
 * 牌库类抽象接口
 */
public interface IDeckCardManager extends ICardGroupManager<Card> {

    /**
     * 获取疲劳值
     */
    int getTiredDamage();
}
