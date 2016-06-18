package com.wangxiaqiwuhai.com.hearthstore.interfaces;


import com.wangxiaqiwuhai.com.hearthstore.card.Card;

import java.util.List;

/**
 * 卡牌抽象行为
 */
@SuppressWarnings("unused")
public interface ICardAction extends IAction{

    void onDrawCardFromDeck(Card card);

    /**
     * 触发卡牌战吼
     */
    void onBattlecry(List<Card> cardList);


    /**
     * 亡语
     */
    void onDeathrattle();

    int beforeTakeDamage(int damage,Card source,Card target);

    void onTakeDamage(int damage,Card source,Card target);

    void afterTakeDamage(int damage,Card source,Card target);

}
