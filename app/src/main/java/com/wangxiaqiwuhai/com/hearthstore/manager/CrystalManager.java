package com.wangxiaqiwuhai.com.hearthstore.manager;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.wangxiaqiwuhai.com.hearthstore.card.Card;
import com.wangxiaqiwuhai.com.hearthstore.interfaces.ICrystalManager;

/**
 * 水晶管理类
 */
public class CrystalManager extends TextView implements ICrystalManager {

    public CrystalManager(Context context) {
        super(context);
    }

    public CrystalManager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CrystalManager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public int getCurrentTurnMaxCrystal() {
        return 0;
    }

    @Override
    public void setCurrentTurnMaxCrystal(int currentTurnMaxCrystal) {

    }

    @Override
    public boolean setCrystalTopLimit(int topLimit) {
        return false;
    }

    @Override
    public int getCrystalTopLimit() {
        return 0;
    }

    @Override
    public void setUseableCrystal(int useableCrystal) {

    }

    @Override
    public int getUseableCrystal() {
        return 0;
    }

    @Override
    public void setOverloadCrystal(int overloadCrystal) {

    }

    @Override
    public int getOverloadCrystal() {
        return 0;
    }

    @Override
    public void clearAllOverload() {

    }

    @Override
    public boolean canUseCard(Card card) {
        return true;
    }

    @Override
    public void takeCost(Card card) {

    }
}
