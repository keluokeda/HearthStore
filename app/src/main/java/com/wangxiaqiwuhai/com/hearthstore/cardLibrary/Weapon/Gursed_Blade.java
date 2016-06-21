package com.wangxiaqiwuhai.com.hearthstore.cardLibrary.Weapon;


import com.wangxiaqiwuhai.com.hearthstore.card.Card;
import com.wangxiaqiwuhai.com.hearthstore.card.MinionCard;
import com.wangxiaqiwuhai.com.hearthstore.card.WeaponCard;
import com.wangxiaqiwuhai.com.hearthstore.interfaces.ICardGroupManager;

import java.util.List;
import java.util.Objects;

public class Gursed_Blade extends WeaponCard {

    public Gursed_Blade() {
        super(4, 2, 4, CardHeroClass.Warrior, Quality.Rare, "真银圣剑", "每当你的英雄攻击，回复两点生命");
    }

    @Override
    public void onTurnStart() {

    }

    @Override
    public void onTurnEnd() {

    }

    @Override
    public boolean beforePlayCard(Card card, List<Card> cardList) {
        return false;
    }

    @Override
    public boolean afterPlayCard(Card card, List<Card> cardList) {
        return false;
    }

    @Override
    public void beforeSummonMinion(MinionCard minionCard) {

    }

    @Override
    public void afterSummonMinion(MinionCard minionCard) {

    }

    @Override
    public void onCardGroupInsert(ICardGroupManager cardGroupManager, Card targetCard) {

    }

    @Override
    public void onUseCard(Card card, List<Card> cardList) {

    }

    @Override
    public void onDrawCardFromDeck(Card card) {

    }

    @Override
    public void onBattlecry(List<Card> cardList) {

    }


    @Override
    public void afterUseCard(Card card, Objects target) {

    }

    @Override
    public void beforeAttack(Objects source, Objects target) {

    }

    @Override
    public void onAttack(Objects source, Objects target) {

    }

    @Override
    public void afterAttack(Objects source, Objects target) {

    }

    @Override
    public void takeDamage(int damage, TargetType targetType) {

    }


    @Override
    public void onDeathrattle() {

    }

    @Override
    public int beforeTakeDamage(int damage, Card source, Card target) {
        return 0;
    }

    @Override
    public void onTakeDamage(int damage, Card source, Card target) {

    }

    @Override
    public void afterTakeDamage(int damage, Card source, Card target) {

    }
}
