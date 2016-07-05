package com.wangxiaqiwuhai.com.hearthstore.cardLibrary.Minion;

import android.support.annotation.NonNull;

import com.wangxiaqiwuhai.com.hearthstore.card.Card;
import com.wangxiaqiwuhai.com.hearthstore.card.CardWrapper;
import com.wangxiaqiwuhai.com.hearthstore.card.MinionCard;
import com.wangxiaqiwuhai.com.hearthstore.manager.GameManager;

/**
 * 麻风侏儒
 */
public class Mayun extends MinionCard {
    public Mayun(Hero hero, GameManager gameManager) {
        super(Quality.Common, Race.None, CardHeroClass.Neutral, "麻风侏儒", "亡语：对对面英雄造成两点伤害", 1, Sets.BASIC, hero, gameManager, 2, 1);
        addEvent(EventType.BEFORE_PLAY_CARD, new AbsEvent(this) {
            @Override
            public void run(Card source, @NonNull CardWrapper target) {
                //每当你打出一张牌 toast一下
                mGameManager.toast(Mayun.this.toString() + "响应了 使用卡牌事件");
            }
        });

        addEvent(EventType.AFTER_SUMMON_MINION, new AbsEvent(this) {
            @Override
            public void run(Card source, @NonNull CardWrapper target) {
                //召唤后事件
                if(source instanceof MinionCard &&source.getRace()==Race.Dragon){
                    mGameManager.toast("响应召唤后事件");
                }
            }
        });
    }


}
