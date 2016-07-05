package com.wangxiaqiwuhai.com.hearthstore.manager;


import android.content.Context;
import android.util.AttributeSet;

import com.wangxiaqiwuhai.com.hearthstore.card.Card;
import com.wangxiaqiwuhai.com.hearthstore.card.MinionCard;

import java.util.List;

/**
 * 手牌类
 */
public class HandCardManager extends AbsCardViewGroupManager<Card> {


    public HandCardManager(Context context) {
        super(context);
    }

    public HandCardManager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HandCardManager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public int getMaxSize() {
        return 10;
    }







    @Override
    public int getCount(Class<? extends Card> cardClazz) {
        return 0;
    }

    @Override
    public boolean isContainsTargetClassCard(Class<? extends Card> clazz) {
        return false;
    }



    @Override
    public <T extends Card> List<T> removeCardList(Class<T> tClass) {
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
