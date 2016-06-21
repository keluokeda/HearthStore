package com.wangxiaqiwuhai.com.hearthstore.interfaces;

import com.wangxiaqiwuhai.com.hearthstore.card.Card;
import com.wangxiaqiwuhai.com.hearthstore.card.MinionCard;

import java.util.List;
import java.util.Objects;

/**
 * 卡牌抽象回调
 */
public interface IAction {
    void onTurnStart();

    void onTurnEnd();

    /**
     * 使用一张卡牌之前，对应英文whenever
     * @return true表示拦截
     */
    boolean beforePlayCard(Card card, List<Card> cardList);

    /**
     * 使用一张卡牌之后 对应英文 after
     * @return
     */
    boolean afterPlayCard(Card card, List<Card> cardList);

    /**
     * 召唤一个随从之前
     */
    void beforeSummonMinion(MinionCard minionCard);

    /**
     * 召唤一个随从之后
     */
    void afterSummonMinion(MinionCard minionCard);

    void onCardGroupInsert(ICardGroupManager cardGroupManager, Card targetCard);

    void onUseCard(Card card, List<Card> cardList);

    void afterUseCard(Card card, Objects target);

    void beforeAttack(Objects source, Objects target);

    void onAttack(Objects source, Objects target);

    void afterAttack(Objects source, Objects target);


    void takeDamage(int damage, Card.TargetType targetType);

}
