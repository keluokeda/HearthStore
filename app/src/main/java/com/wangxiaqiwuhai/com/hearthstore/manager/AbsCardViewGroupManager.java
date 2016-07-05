package com.wangxiaqiwuhai.com.hearthstore.manager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;

import com.wangxiaqiwuhai.com.hearthstore.card.Card;
import com.wangxiaqiwuhai.com.hearthstore.card.CardView;
import com.wangxiaqiwuhai.com.hearthstore.interfaces.ICardGroupManager;

import java.util.ArrayList;
import java.util.List;

/**
 * 容器抽象管理类
 */
public abstract class AbsCardViewGroupManager<T extends Card> extends LinearLayout implements ICardGroupManager<T> {
    protected List<T> mCardList;

    public AbsCardViewGroupManager(Context context) {
        super(context);
        init();
    }

    public AbsCardViewGroupManager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AbsCardViewGroupManager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOrientation(HORIZONTAL);//水平方向
        setGravity(Gravity.CENTER);//重心在中间

        mCardList = new ArrayList<>();
    }

    @Override
    public boolean insertCard(T card) {
        return insertCard(card,mCardList.size());
    }

    @Override
    public boolean insertCard(T card, int position) {
        if (isFull()) {
            return false;
        }
        mCardList.add(position, card);
        CardView cardView=new CardView(getContext());
        cardView.initData(card);
        addView(cardView,position);
        card.setICardGroupManager(this);
        return true;
    }

    @Override
    public void refresh() {
        removeAllViews();
        for (int i = 0; i < mCardList.size(); i++) {
            CardView cardView = new CardView(getContext());
            cardView.initData(mCardList.get(i));
            addView(cardView);
        }

    }

    @Override
    public boolean isEmpty() {
        return mCardList.isEmpty();
    }

    @Override
    public boolean isFull() {
        return mCardList.size() >= getMaxSize();
    }

    @Override
    public <T1 extends T> List<T1> getCardList(Class<T1> t1Class) {
        List<T1> t1List = new ArrayList<>();
        for(int i=0;i<mCardList.size();i++){
            T card=mCardList.get(i);
            t1Class.isInstance(card);
            t1List.add((T1) card);
        }
        return t1List;
    }

    @Override
    public T remove(T card) {
        if(!mCardList.contains(card)){
            return null;
        }

        return remove(mCardList.indexOf(card));
    }

    @Override
    public T remove(int position) {
        removeViewAt(position);
        return mCardList.remove(position);
    }

    @Override
    public int getCardPosition(T card) {
        return mCardList.indexOf(card);
    }
}
