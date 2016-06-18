package com.wangxiaqiwuhai.com.hearthstore.cardLibrary.Weapon;


import com.wangxiaqiwuhai.com.hearthstore.card.Card;
import com.wangxiaqiwuhai.com.hearthstore.card.WeaponCard;
import com.wangxiaqiwuhai.com.hearthstore.interfaces.ICardManager;

import java.util.Objects;

public class Gursed_Blade extends WeaponCard{

    public Gursed_Blade(){
        super(4, 2, 4, CardHeroClass.Warrior, Quality.Rare, "真银圣剑", "每当你的英雄攻击，回复两点生命");
    }

    @Override
    public void onTurnStart() {

    }

    @Override
    public void onTurnEnd() {

    }

    @Override
    public void onDrawCardFromDeck(Card card) {

    }

    @Override
    public void beforeUseCard(Card card, Objects target) {

    }

    @Override
    public void onUseCard(Card card, Objects target) {

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
    public void onBattlecry() {

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
