package com.wangxiaqiwuhai.com.hearthstore.manager;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.wangxiaqiwuhai.com.hearthstore.card.Card;
import com.wangxiaqiwuhai.com.hearthstore.card.MinionCard;
import com.wangxiaqiwuhai.com.hearthstore.interfaces.ICardGroupManager;

import java.util.List;

/**
 * 牌库管理类
 */
public class DeckCardManager extends TextView implements ICardGroupManager.IDeckCardManager {

    public DeckCardManager(Context context) {
        super(context);
    }

    public DeckCardManager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DeckCardManager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean insertCard(Card card) {
        return false;
    }

    @Override
    public boolean insertCard(Card card, int position) {
        return false;
    }

    @Override
    public int getMaxSize() {
        return 0;
    }

    @Override
    public void refresh() {

    }

    @Override
    public Card remove(int position) {
        return null;
    }

    @Override
    public Card remove(Card card) {
        return null;
    }

    @Override
    public int getCardPosition(Card card) {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean isFull() {
        return false;
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
    public <T extends Card> List<T> getCardList(Class<T> tClass) {
        return null;
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
