package com.wangxiaqiwuhai.com.hearthstore.interfaces;

import com.wangxiaqiwuhai.com.hearthstore.card.Card;

import java.util.List;

/**
 * 游戏管理类
 */
@SuppressWarnings("unused")
public interface IGameManager {
    IHeroManager getActiveHero();

    void changedActiveHero();

    List<Card> getTargetCardList(Card.TargetType targetType);

    void takeDamage(List<Card> cardList,int damage,Card.TargetType targetType,Card source);

    void heroDrawCardFromDeckToHand(boolean isOwner);

    boolean insertCardToHero(Card card,boolean isOwner);




}
