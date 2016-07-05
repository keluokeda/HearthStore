package com.wangxiaqiwuhai.com.hearthstore.manager;


import android.view.View;

import com.wangxiaqiwuhai.com.hearthstore.R;
import com.wangxiaqiwuhai.com.hearthstore.card.Card;
import com.wangxiaqiwuhai.com.hearthstore.card.HeroCard;
import com.wangxiaqiwuhai.com.hearthstore.card.WeaponCard;
import com.wangxiaqiwuhai.com.hearthstore.cardLibrary.hero.JiHero;
import com.wangxiaqiwuhai.com.hearthstore.interfaces.ICardGroupManager;
import com.wangxiaqiwuhai.com.hearthstore.interfaces.ICrystalManager;

import java.util.Objects;

/**
 * 英雄管理类
 */
public  class HeroManager {
    Card.Hero mHero;

    HeroCard mHeroCard;
    WeaponCard mWeaponCard;//武器区域
    ICardGroupManager.ISecretAreaManager mISecretAreaManager;//奥秘区域


    ICardGroupManager.IDeckCardManager mIDeckCardManager;//牌库
    ICardGroupManager.ICemeteryManager mICemeteryManager;//坟场
    HandCardManager mIHandCardManager;//手牌
    BattleFieldManager mIBattleFieldManager;//战场
    ICrystalManager mICrystalManager;//水晶

    public HeroManager(View baseView,Card.Hero hero,   HandCardManager IHandCardManager, BattleFieldManager IBattleFieldManager) {
        mHero = hero;
        mISecretAreaManager = (ICardGroupManager.ISecretAreaManager) baseView.findViewById(R.id.secret_area);
        mIDeckCardManager = (ICardGroupManager.IDeckCardManager) baseView.findViewById(R.id.deck_card);
        mICemeteryManager = (ICardGroupManager.ICemeteryManager) baseView.findViewById(R.id.cemetery);
        mICrystalManager = (com.wangxiaqiwuhai.com.hearthstore.interfaces.ICrystalManager) baseView.findViewById(R.id.crystal);
        mIHandCardManager = IHandCardManager;
        mIBattleFieldManager = IBattleFieldManager;


    }

    public void heroDamage(int damage) {

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

    public ICardGroupManager.ISecretAreaManager getISecretAreaManager() {
        return mISecretAreaManager;
    }

    public ICardGroupManager.IDeckCardManager getIDeckCardManager() {
        return mIDeckCardManager;
    }

    public ICardGroupManager.ICemeteryManager getICemeteryManager() {
        return mICemeteryManager;
    }

    public HandCardManager getIHandCardManager() {
        return mIHandCardManager;
    }

    public ICrystalManager getICrystalManager() {
        return mICrystalManager;
    }

    public BattleFieldManager getIBattleFieldManager() {
        return mIBattleFieldManager;
    }







}
