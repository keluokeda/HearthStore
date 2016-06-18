package com.wangxiaqiwuhai.com.hearthstore.cardLibrary.Minion;

import com.wangxiaqiwuhai.com.hearthstore.card.Card;
import com.wangxiaqiwuhai.com.hearthstore.card.HeroCard;
import com.wangxiaqiwuhai.com.hearthstore.card.MinionCard;
import com.wangxiaqiwuhai.com.hearthstore.interfaces.IBattleFieldManager;
import com.wangxiaqiwuhai.com.hearthstore.interfaces.IHandCardManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 被腐化的死亡之翼
 */
public class Deathwing_Dragonlord extends MinionCard{
    public Deathwing_Dragonlord(boolean isHero) {
        super(1, 1, 2, CardHeroClass.Neutral, Race.Dragon, Quality.Legendary, "死亡之翼", "亡语:将手牌中的所有龙类置于战场",isHero);
    }
    @Override
    public void onTurnStart() {

    }

    @Override
    public void onTurnEnd() {

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
    public void onDrawCardFromDeck(Card card) {
        if(card==this) {
            //从牌库抽到卡牌的时候 对所有角色造成2点伤害
            mGameManager.getEnemyManager().getIBattleFieldManager().takeDamage(2, null);
            mGameManager.getEnemyManager().getHeroCard().takeDamage(2, null);

            mGameManager.getUserHeroManager().getIBattleFieldManager().takeDamage(2, null);
            mGameManager.getUserHeroManager().getHeroCard().takeDamage(2, null);
        }

        //克洛玛古斯 抽牌复制效果
        IHandCardManager handCardManager=ownerIsHero?mGameManager.getUserHeroManager().getIHandCardManager():mGameManager.getEnemyManager().getIHandCardManager();
        handCardManager.insertCard(card);



    }

    @Override
    public void onBattlecry() {

    }


    @Override
    public void onDeathrattle() {
        //将英雄手牌中的所有龙类加入战场
        IHandCardManager handCardManager=ownerIsHero?mGameManager.getUserHeroManager().getIHandCardManager():mGameManager.getEnemyManager().getIHandCardManager();
        IBattleFieldManager battleFieldManager=ownerIsHero?mGameManager.getUserHeroManager().getIBattleFieldManager():mGameManager.getEnemyManager().getIBattleFieldManager();
        List<MinionCard> list = handCardManager.removeTargetTypeMinion(Race.Dragon);
        if(list!=null){
            battleFieldManager.insertCardList(list);
        }

        //对敌方英雄造成两点伤害
        HeroCard heroCard= mGameManager.getManager(ownerIsHero).getHeroCard();
        List<Card> cardList=new ArrayList<>();
        cardList.add(heroCard);
        mGameManager.takeDamage(this,cardList,2,null);

        //对所有角色造成两点伤害
        List<Card> cards=new ArrayList<>();
        cards.add(mGameManager.getUserHeroManager().getHeroCard());
        cards.addAll(mGameManager.getUserHeroManager().getIBattleFieldManager().getCardList(MinionCard.class));
        cards.add(mGameManager.getEnemyManager().getHeroCard());
        cards.addAll(mGameManager.getEnemyManager().getIBattleFieldManager().getCardList(MinionCard.class));
        mGameManager.takeDamage(this,cards,2,null);

    }

    @Override
    public int beforeTakeDamage(int damage, Card source, Card target) {
        //你的英雄每次只会收到一点伤害
        //如果受到伤害的英雄是你的英雄 那么就把伤害值变为1
        if(target==mGameManager.getManager(ownerIsHero).getHeroCard()&&damage>0){
            return 1;
        }
        return damage;

    }

    @Override
    public void onTakeDamage(int damage, Card source, Card target) {

    }

    @Override
    public void afterTakeDamage(int damage, Card source, Card target) {

    }
}