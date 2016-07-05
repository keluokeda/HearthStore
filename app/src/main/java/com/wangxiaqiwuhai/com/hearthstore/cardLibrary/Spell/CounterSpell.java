package com.wangxiaqiwuhai.com.hearthstore.cardLibrary.Spell;

import android.support.annotation.NonNull;

import com.wangxiaqiwuhai.com.hearthstore.card.Card;
import com.wangxiaqiwuhai.com.hearthstore.card.CardWrapper;
import com.wangxiaqiwuhai.com.hearthstore.card.MinionCard;
import com.wangxiaqiwuhai.com.hearthstore.card.SecretSpellCard;
import com.wangxiaqiwuhai.com.hearthstore.card.SpellCard;
import com.wangxiaqiwuhai.com.hearthstore.interfaces.ICardGroupManager;
import com.wangxiaqiwuhai.com.hearthstore.manager.GameManager;

import java.util.ArrayList;
import java.util.List;

/**
 * 法术反制
 */
public abstract class CounterSpell extends SecretSpellCard {
    public CounterSpell(Quality quality, Race race, CardHeroClass cardHeroClass, Type type, CharSequence cardName, CharSequence description, int cost, Sets sets, Hero hero, GameManager gameManager) {
        super(quality, race, cardHeroClass, type, cardName, description, cost, sets, hero, gameManager);


        addEvent(EventType.MAKE_FUNCTION, new AbsEvent(this) {

            @Override
            public void run(Card source, @NonNull CardWrapper target) {
                if (
                        (CounterSpell.this.getHero() != mGameManager.getCurrentActiveHero()
                        ) && (
                                (source instanceof SpellCard) && (target.getOriginalCard() != null && target.getOriginalCard() instanceof MinionCard)
                        )
                        ) {
                    MinionCard minionCard = null;
                    mGameManager.summonMinion(minionCard);
                    target.setOriginalCard(minionCard);
                }

                target.setDamage(6);
                mGameManager.takeDamage(source, target);
            }
        });

        //如果一个法术分为多个步骤 可以认为是多次战吼 因为每个步骤的结算队列不同
        //暴风雪 先造成2点伤害 在 冻结所有随从
        addEvent(EventType.MAKE_FUNCTION, new AbsEvent(this) {
            @Override
            public void run(Card source, @NonNull CardWrapper target) {
                if (CounterSpell.this == source) {
                    //获取当前卡牌所属英雄 对面英雄的 所有战场随从
                    List<MinionCard> enemyMinionList = mGameManager.getHeroManager(source.getHero().getSideHero()).getIBattleFieldManager().getCardList(MinionCard.class);
                    //锁定队列
                    enemyMinionList = new ArrayList<>(enemyMinionList);
                    for (int i = 0; i < enemyMinionList.size(); i++) {
                        MinionCard card = enemyMinionList.get(i);
                        CardWrapper cardWrapper = new CardWrapper();
                        cardWrapper.setOriginalCard(card);
                        cardWrapper.setDamage(2);
                        mGameManager.takeDamage(source, cardWrapper);
                    }
                }
            }
        });
        addEvent(EventType.MAKE_FUNCTION, new AbsEvent(this) {
            @Override
            public void run(Card source, @NonNull CardWrapper target) {
                if (CounterSpell.this == source) {
                    //获取当前卡牌所属英雄 对面英雄的 所有战场随从
                    List<MinionCard> enemyMinionList = mGameManager.getHeroManager(source.getHero().getSideHero()).getIBattleFieldManager().getCardList(MinionCard.class);
                    //锁定队列
                    enemyMinionList = new ArrayList<>(enemyMinionList);
                    for (int i = 0; i < enemyMinionList.size(); i++) {

                    }
                }
            }

        });


        //智慧祝福
        addEvent(EventType.MAKE_FUNCTION, new AbsEvent(this) {
            @Override
            public void run(Card source, @NonNull final CardWrapper target) {
                if (source == getAttachCard()) {
                    //发起卡就是当前卡

                    if (target.getOriginalCard() != null && target.getOriginalCard().getICardGroupManager() instanceof ICardGroupManager.IBattleFieldManager) {
                        //目标卡在场面上

                        //为目标添加一个攻击前的响应事件

                        target.getOriginalCard().addEvent(EventType.BEFORE_ATTACK, new AbsEvent(target.getOriginalCard()) {
                            @Override
                            public void run(Card source, @NonNull CardWrapper target) {
                                if (source == this.getAttachCard()) {
                                    mGameManager.heroDrawCardFromDeckToHand(getHero());
                                }
                            }
                        });
                    }
                }

            }
        });


    }
}