package com.wangxiaqiwuhai.com.hearthstore.cardLibrary.Minion;

import android.support.annotation.NonNull;

import com.wangxiaqiwuhai.com.hearthstore.card.AbsHealthCard;
import com.wangxiaqiwuhai.com.hearthstore.card.Card;
import com.wangxiaqiwuhai.com.hearthstore.card.CardWrapper;
import com.wangxiaqiwuhai.com.hearthstore.card.MinionCard;
import com.wangxiaqiwuhai.com.hearthstore.manager.GameManager;

import java.util.List;
import java.util.Random;

/**
 * 被腐化的死亡之翼
 */
public class Deathwing_Dragonlord extends MinionCard {


    public Deathwing_Dragonlord(Hero hero, GameManager gameManager) {
        super(Quality.Legendary, Race.Dragon, CardHeroClass.Neutral, "死亡之翼", "忘语：召唤手中", 10, Sets.OLD_GODS, hero, gameManager, 12, 12);
        addEvent(EventType.MAKE_FUNCTION, new AbsEvent(this) {
            @Override
            public void run(Card source, @NonNull CardWrapper target) {
                //战吼
                if(source==getAttachCard()){
                    mGameManager.toast(Deathwing_Dragonlord.this.toString()+"发动战吼");
                }
            }
        });
    }

    @Override
    public TargetType getTargetType() {
        return TargetType.Enemy;
    }
}
