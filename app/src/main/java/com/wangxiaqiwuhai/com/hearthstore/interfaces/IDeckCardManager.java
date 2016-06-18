package com.wangxiaqiwuhai.com.hearthstore.interfaces;


/**
 * 牌库类抽象接口
 */
public interface IDeckCardManager extends ICardManager{

    /**
     * 获取疲劳值
     */
    int getTiredDamage();
}
