package com.wangxiaqiwuhai.com.hearthstore.manager;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.wangxiaqiwuhai.com.hearthstore.card.Card;
import com.wangxiaqiwuhai.com.hearthstore.card.MinionCard;
import com.wangxiaqiwuhai.com.hearthstore.card.SecretSpellCard;
import com.wangxiaqiwuhai.com.hearthstore.interfaces.ICardGroupManager;

import java.util.ArrayList;
import java.util.List;

/**
 * 奥秘区域
 */
public class SecretAreaManager extends TextView implements ICardGroupManager.ISecretAreaManager{
    public SecretAreaManager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SecretAreaManager(Context context) {
        super(context);
    }

    public SecretAreaManager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean insertCard(SecretSpellCard card) {
        return false;
    }

    @Override
    public boolean insertCard(SecretSpellCard card, int position) {
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
    public SecretSpellCard remove(int position) {
        return null;
    }

    @Override
    public SecretSpellCard remove(SecretSpellCard card) {
        return null;
    }

    @Override
    public int getCardPosition(SecretSpellCard card) {
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
    public int getCount(Class<? extends SecretSpellCard> cardClazz) {
        return 0;
    }

    @Override
    public boolean isContainsTargetClassCard(Class<? extends SecretSpellCard> clazz) {
        return false;
    }

    @Override
    public <T extends SecretSpellCard> List<T> getCardList(Class<T> tClass) {
        return new ArrayList<>();
    }

    @Override
    public <T extends SecretSpellCard> List<T> removeCardList(Class<T> tClass) {
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
