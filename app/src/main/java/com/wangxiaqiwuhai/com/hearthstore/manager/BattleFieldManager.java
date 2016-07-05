package com.wangxiaqiwuhai.com.hearthstore.manager;


import android.content.Context;
import android.util.AttributeSet;

import com.wangxiaqiwuhai.com.hearthstore.card.Card;
import com.wangxiaqiwuhai.com.hearthstore.card.MinionCard;

import java.util.List;

/**
 * 战场管理类
 */
public class BattleFieldManager extends AbsCardViewGroupManager<MinionCard> {




    public BattleFieldManager(Context context) {
        super(context);

    }

    public BattleFieldManager(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public BattleFieldManager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }


    @Override
    public int getMaxSize() {
        return 7;
    }








    @Override
    public int getCount(Class<? extends MinionCard> cardClazz) {
        return 0;
    }

    @Override
    public boolean isContainsTargetClassCard(Class<? extends MinionCard> clazz) {
        return false;
    }



    @Override
    public <T extends MinionCard> List<T> removeCardList(Class<T> tClass) {
        return null;
    }

    @Override
    public boolean isContainsTargetTypeMinion(Card.Race targetRace) {
        return false;
    }

    @Override
    public List<MinionCard> getTargetTypeMinion(Card.Race race) {
        return null;
    }

    @Override
    public List<MinionCard> removeTargetTypeMinion(Card.Race race) {
        return null;
    }
}
