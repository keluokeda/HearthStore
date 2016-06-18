package com.wangxiaqiwuhai.com.hearthstore.manager;


import com.wangxiaqiwuhai.com.hearthstore.card.Card;
import com.wangxiaqiwuhai.com.hearthstore.card.HeroCard;
import com.wangxiaqiwuhai.com.hearthstore.card.WeaponCard;
import com.wangxiaqiwuhai.com.hearthstore.interfaces.IAction;
import com.wangxiaqiwuhai.com.hearthstore.interfaces.IBattleFieldManager;
import com.wangxiaqiwuhai.com.hearthstore.interfaces.ICemeteryManager;
import com.wangxiaqiwuhai.com.hearthstore.interfaces.ICrystalManager;
import com.wangxiaqiwuhai.com.hearthstore.interfaces.IDeckCardManager;
import com.wangxiaqiwuhai.com.hearthstore.interfaces.IHandCardManager;
import com.wangxiaqiwuhai.com.hearthstore.interfaces.IHeroManager;
import com.wangxiaqiwuhai.com.hearthstore.interfaces.ISecretAreaManager;

import java.util.Objects;

/**
 *英雄管理类
 */
public abstract class HeroManager implements IHeroManager{
    boolean isUser;

    HeroCard mHeroCard;
    WeaponCard mWeaponCard;//武器区域
    ISecretAreaManager mISecretAreaManager;//奥秘区域


    IDeckCardManager mIDeckCardManager;//牌库
    ICemeteryManager mICemeteryManager;//坟场
    IHandCardManager mIHandCardManager;//手牌

    ICrystalManager mICrystalManager;//水晶

    IBattleFieldManager mIBattleFieldManager;//战场

    void heroDamage(int damage){

    }

    public HeroCard getHeroCard() {
        return mHeroCard;
    }

    public void setHeroCard(HeroCard heroCard) {
        mHeroCard = heroCard;
    }

    public WeaponCard getWeaponCard() {
        return mWeaponCard;
    }

    public ISecretAreaManager getISecretAreaManager() {
        return mISecretAreaManager;
    }

    public IDeckCardManager getIDeckCardManager() {
        return mIDeckCardManager;
    }

    public ICemeteryManager getICemeteryManager() {
        return mICemeteryManager;
    }

    public IHandCardManager getIHandCardManager() {
        return mIHandCardManager;
    }

    public ICrystalManager getICrystalManager() {
        return mICrystalManager;
    }

    public IBattleFieldManager getIBattleFieldManager() {
        return mIBattleFieldManager;
    }

    @Override
    public void onTurnStart() {
        mIBattleFieldManager.onTurnStart();
        mICrystalManager.onTurnStart();
        drawCardFromDeck();
    }

    /**
     * 让当前英雄从牌库中抽取一张卡
     */
    public  void drawCardFromDeck(){
        Card card=mIDeckCardManager.remove(0);
        drawCardFromDeck(card);
    }

    public void drawCardFromDeck(Card card){
        if(card==null){
            heroDamage(mIDeckCardManager.getTiredDamage());
        }else {
            //成功从牌库抽到卡牌
            //先把卡牌插入到手牌或者墓地
            if(!mIHandCardManager.insertCard(card)) {
                mICemeteryManager.insertCard(card);
            }
            //在执行卡牌的异能
            card.onDrawCardFromDeck(card);

        }
    }

    /**
     * 向手牌中插入一张牌
     * @param card 要插入的牌
     */
    public void insertCardToHandCard(Card card){
        if(card==null){
            return;
        }

    }

    @Override
    public void onTurnEnd() {
        mIBattleFieldManager.onTurnEnd();
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
    public void takeDamage(int damage,Card.TargetType targetType) {

    }
}
