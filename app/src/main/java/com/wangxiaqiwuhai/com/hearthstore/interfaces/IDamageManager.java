package com.wangxiaqiwuhai.com.hearthstore.interfaces;

import com.wangxiaqiwuhai.com.hearthstore.card.Card;

import java.util.List;

/**
 * 计算伤害的抽象接口
 */
public abstract class IDamageManager {
    /**
     *
     * @param damage 伤害值
     * @param cardList 受害人群体
     * @param source 当事人
     * @param damageExecuter 伤害执行者
     */
    public abstract void computeDamage(int damage,List<Card> cardList,Card source,IDamageExecuter damageExecuter);
}
