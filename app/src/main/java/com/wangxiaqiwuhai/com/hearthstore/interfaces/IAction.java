package com.wangxiaqiwuhai.com.hearthstore.interfaces;

import com.wangxiaqiwuhai.com.hearthstore.card.Card;

import java.util.List;
import java.util.Objects;

/**
 * Created by Administrator on 2016/6/14.
 */
public interface IAction {
    void onTurnStart();

    void onTurnEnd();



    void beforeUseCard(Card card,List<Card> cardList);

    void onUseCard(Card card,List<Card> cardList);

    void afterUseCard(Card card,Objects target);

    void beforeAttack(Objects source,Objects target);

    void onAttack(Objects source,Objects target);

    void afterAttack(Objects source,Objects target);



    void takeDamage(int damage,Card.TargetType targetType);

}
