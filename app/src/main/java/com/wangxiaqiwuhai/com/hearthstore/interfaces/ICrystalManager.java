package com.wangxiaqiwuhai.com.hearthstore.interfaces;

import com.wangxiaqiwuhai.com.hearthstore.card.Card;

/**
 *
 */
public interface ICrystalManager  {


    /**
     * 获取当前回合最大水晶数
     */
    int getCurrentTurnMaxCrystal();

    /**
     * 设置当前回合最大水晶数
     */
    void setCurrentTurnMaxCrystal(int currentTurnMaxCrystal);

    /**
     * 设置水晶限制
     * @param topLimit
     */
    boolean setCrystalTopLimit(int topLimit);

    /**
     * 获取水晶限制值
     */
    int getCrystalTopLimit();


    /**
     * 设置可用水晶数量
     */
    void setUseableCrystal(int useableCrystal);

    /**
     * 获取可用水晶数量
     */
    int getUseableCrystal();

    /**
     * 设置过载水晶数量
     */
    void setOverloadCrystal(int overloadCrystal);

    /**
     * 获取过载水晶数量
     */
    int getOverloadCrystal();

    /**
     * 清除所有过载水晶
     */
    void clearAllOverload();

    /**
     * 是否有足够的水晶来使用这张牌
     */
    boolean canUseCard(Card card);

    /**
     * 消费一张卡
     */
    void takeCost(Card card);


}
