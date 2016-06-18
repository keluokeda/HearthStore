package com.wangxiaqiwuhai.com.hearthstore.action;

import com.wangxiaqiwuhai.com.hearthstore.card.Card;

/**
 * 卡牌行为抽象接口
 */
@SuppressWarnings("unused")
public interface CardAction {
    /**
     * 在卡牌使用之前
     *
     * @param card 要使用的卡牌
     */
    void beforeCardUse(Card card);

    /**
     * 使用卡牌
     *
     * @param card 正在使用卡牌
     */
    void onCardUse(Card card);

    /**
     * 卡牌使用之后
     *
     * @param card
     */
    void afterCardUse(Card card);

    interface MinionCardAction extends CardAction {
        void beforeAttack(Card card);

        void onAttack(Card card);

        void afterAttack(Card card);

    }
}
