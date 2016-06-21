package com.wangxiaqiwuhai.com.hearthstore.cardLibrary.Spell;

import com.wangxiaqiwuhai.com.hearthstore.card.Card;
import com.wangxiaqiwuhai.com.hearthstore.card.MinionCard;
import com.wangxiaqiwuhai.com.hearthstore.card.SecretSpellCard;
import com.wangxiaqiwuhai.com.hearthstore.interfaces.ICardGroupManager;

import java.util.List;
import java.util.Objects;

/**
 * 法术反制
 */
public class CounterSpell extends SecretSpellCard {
    public CounterSpell() {
        super(3, CardHeroClass.Mage, Quality.Rare, "法术反制", "反制敌方法术");
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
        //火球术 打在敌方连上
        mGameManager.takeDamage(this,cardList,6,null);
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
    public void takeDamage(int damage,TargetType targetType) {

    }



    @Override
    public void onDeathrattle() {

    }



    @Override
    public void onTakeDamage(int damage, Card source, Card target) {

    }

    @Override
    public void afterTakeDamage(int damage, Card source, Card target) {

    }
}
