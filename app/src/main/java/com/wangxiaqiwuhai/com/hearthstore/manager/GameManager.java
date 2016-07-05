package com.wangxiaqiwuhai.com.hearthstore.manager;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.wangxiaqiwuhai.com.hearthstore.TestFunction;
import com.wangxiaqiwuhai.com.hearthstore.card.AbsHealthCard;
import com.wangxiaqiwuhai.com.hearthstore.card.Card;
import com.wangxiaqiwuhai.com.hearthstore.card.CardWrapper;
import com.wangxiaqiwuhai.com.hearthstore.card.HeroCard;
import com.wangxiaqiwuhai.com.hearthstore.card.MinionCard;
import com.wangxiaqiwuhai.com.hearthstore.card.SecretSpellCard;
import com.wangxiaqiwuhai.com.hearthstore.card.SpellCard;
import com.wangxiaqiwuhai.com.hearthstore.interfaces.ICardGroupManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 游戏管理工具类
 */
public class GameManager {


    HeroManager userHeroManager;
    HeroManager enemyManager;
    HeroManager mCurrentHero;
    Context mContext;

    public GameManager(HeroManager userHeroManager, HeroManager enemyManager, Context context) {
        this.userHeroManager = userHeroManager;
        this.enemyManager = enemyManager;

        mContext = context;
    }

    public HeroManager getHeroManager(Card.Hero hero) {
        switch (hero) {
            case your:
                return userHeroManager;
            case enemy:
                return enemyManager;
            default:
                return null;
        }
    }

    /**
     * 获取英雄卡牌
     *
     * @param hero
     * @return
     */
    public HeroCard getHeroCard(Card.Hero hero) {
        return getHeroManager(hero).getHeroCard();
    }

    public boolean isActiveHeroIsUserHero() {
        return mCurrentHero == userHeroManager;
    }


    public HeroManager getActiveHero() {
        return enemyManager;
    }


    /**
     * 死亡结算
     */
    private void computeDeath() {
        //确认场上是否有待移除的随从 如果没有就返回
        //把卡牌从战场上移除
        //执行亡语
        //所有待死亡角色忘语执行完成之后，检查是否有新的随从死亡，如果有，继续执行此方法，直到不会有新的死亡发生
        if (!anyMinionDie()) {
            return;
        }
        List<MinionCard> deathMinionList = getDeathMinionList();
        removeDeathMinion();//把标记死亡的从战场上移除
        for (int i = 0; i < deathMinionList.size(); i++) {
            //onMinionDie(deathMinionList.get(i));
        }
        computeDeath();
    }

    public final void toast(CharSequence content){
        Toast.makeText(mContext,content,Toast.LENGTH_SHORT).show();
    }


    public List<Card> getGameAssertList() {
        return null;
    }


    /**
     * 是否有随从死亡
     *
     * @return 有随从死亡就返回true
     */
    private boolean anyMinionDie() {
        return false;
    }

    //移除死亡的随从
    private void removeDeathMinion() {

    }

    //获取在此阶段死亡的随从
    protected List<MinionCard> getDeathMinionList() {
        return null;
    }


    /**
     * 判断是否可以使用这张卡牌
     *
     * @return true表示可以 false表示不可以
     */
    private boolean canUseHandCard(Card card) {
        HeroManager heroManager = getHeroManager(card.getHero());
        //判断是否有足够的水晶
        if (!heroManager.getICrystalManager().canUseCard(card)) {
            return false;
        }
        //判断当前卡牌能不能插入目标区域
        if (!canInsertCardToTargetArea(card, heroManager)) {
            return false;
        }
        return true;
    }

    /**
     * 从手牌中拿出一张卡来使用
     *
     * @param card 要使用的卡
     */
    public void useCardFromHand(Card card) {
        if (!canUseHandCard(card)) {
            return;
        }

        if (card.getTargetType() != null && card.getTargetType() != Card.TargetType.Null) {
            //如果卡牌需要 战吼 目标
            //获取卡牌目标  法术目标或者 随从战吼目标
            getTargetCardList(card, card.getTargetType());
            return;
        } else {
            choiceCardPosition(card, null);
        }


    }

    /**
     * 选择打出的随从在战场的位置
     */
    private void choiceCardPosition(final Card card, final AbsHealthCard target) {
        if (card instanceof MinionCard) {
            HeroManager heroManager = getHeroManager(card.getHero());
            List<MinionCard> minionCards = heroManager.getIBattleFieldManager().getCardList(MinionCard.class);
            if (minionCards.isEmpty()) {
                useCardFromHand(card, target, 0);
                return;
            }
            CharSequence[] items = new CharSequence[minionCards.size() + 1];
            for (int i = 0; i < items.length; i++) {
                items[i] = String.valueOf(i);
            }
            new AlertDialog.Builder(mContext).setTitle("选择随从的位置").setItems(items, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    useCardFromHand(card, target, which);
                }

            }).show();

        } else {
            useCardFromHand(card, target, -1);
        }
    }

    /**
     * 选取目标卡牌
     *
     * @param targetType 目标类型
     * @return 不选返回空，不需要目标返回空集合，否则就是 战吼目标或者法术目标集合
     */
    public void getTargetCardList(final Card card, Card.TargetType targetType) {
        final List<AbsHealthCard> cardList = new ArrayList<>();
        HeroManager yourHeroManager = getHeroManager(card.getHero());
        HeroManager enemyHeroManager = getHeroManager(card.getHero().getSideHero());
        switch (targetType) {
            case Enemy:
                cardList.add(enemyHeroManager.getHeroCard());
                cardList.addAll(enemyHeroManager.getIBattleFieldManager().getCardList(MinionCard.class));

                break;
            default:
                break;
        }
        CharSequence[] charSequences = new CharSequence[cardList.size()];
        for (int i = 0; i < cardList.size(); i++) {
            charSequences[i] = cardList.get(i).getCardName();
        }
        new AlertDialog.Builder(mContext).setTitle("选择目标").setItems(charSequences, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                choiceCardPosition(card, cardList.get(which));
            }
        }).setCancelable(true).show();

    }

    /**
     * 从手牌中使用一张卡牌
     *
     * @param card       要使用的卡牌
     * @param targetCard 使用卡牌的作用目标
     * @param position   如果是随从，就指随从要插入战场的位置，其他情况 -1
     */
    private void useCardFromHand(Card card, AbsHealthCard targetCard, int position) {
        HeroManager heroManager = getHeroManager(card.getHero());
        //扣除水晶
        heroManager.getICrystalManager().takeCost(card);
        //从手牌中移除该卡
        heroManager.getIHandCardManager().remove(card);

        //改变卡牌位置
        moveHandCardToTargetArea(card, position);

        //使用前阶段
        onEvent(Card.EventType.BEFORE_PLAY_CARD, card, targetCard);
        computeDeath();


        if (card instanceof MinionCard) {
            playMinionCard((MinionCard) card, targetCard);
        } else if (card instanceof SpellCard) {
            playSpellCard(card, targetCard);
        }
    }


    /**
     * 把要使用的手牌移动到他应该去的位置
     */
    protected void moveHandCardToTargetArea(Card card, int position) {
        if (card instanceof MinionCard) {
            getHeroManager(card.getHero()).getIBattleFieldManager().insertCard((MinionCard) card, position);
        }
    }

    /**
     * 使用一张随从卡
     */
    protected void playMinionCard(MinionCard card, AbsHealthCard targetCard) {
        onEvent(Card.EventType.BEFORE_SUMMON_MINION, card, targetCard);

        //战吼
        onEvent(Card.EventType.MAKE_FUNCTION, card, targetCard);
        computeDeath();


        onEvent(Card.EventType.AFTER_PLAY_CARD, card, targetCard);
        onEvent(Card.EventType.AFTER_SUMMON_MINION, card, targetCard);
        computeDeath();
    }


    /**
     * 使用一张法术卡
     */
    protected void playSpellCard(Card source, AbsHealthCard target) {
        onEvent(Card.EventType.MAKE_FUNCTION, source, target);
        computeDeath();
        onEvent(Card.EventType.AFTER_PLAY_CARD, source, target);
        computeDeath();
    }


    /**
     * 召唤一个随从 在该过程中不进行死亡结算
     */
    public boolean summonMinion(MinionCard card) {
        HeroManager heroManager = getHeroManager(card.getHero());
        if (heroManager.getIBattleFieldManager().isFull()) {
            return false;
        }
        heroManager.getIBattleFieldManager().insertCard(card);

        onEvent(Card.EventType.BEFORE_SUMMON_MINION, card, null);

        onEvent(Card.EventType.AFTER_SUMMON_MINION, card, null);

        return true;
    }

    protected void onEvent(Card.EventType event, Card source, final AbsHealthCard target) {
        CardWrapper cardWrapper = new CardWrapper();
        cardWrapper.setOriginalCard(target);
        onEvent(event, cardWrapper, source);


    }

    protected void onEvent(Card.EventType eventType, @NonNull CardWrapper cardWrapper, Card source) {
        List<Card> cardList = getObserves(source);
        if (cardWrapper.getOriginalCard() != null && !cardList.contains(cardWrapper.getOriginalCard())) {
            cardList.add(cardWrapper.getOriginalCard());
        }

        List<Card.IEvent> eventList = new ArrayList<>();
        for (int i = 0; i < cardList.size(); i++) {
            eventList.addAll(cardList.get(i).getEventList(eventType));
        }
        //从小到大
        Collections.sort(eventList);
        for (int i = eventList.size() - 1; i >= 0; i--) {
            eventList.get(i).run(source, cardWrapper);
        }

    }

    /**
     * 向指定区域内插入一张卡牌
     *
     * @param card
     * @param cardManager
     * @return
     */
    public boolean insertCardToTargetArea(Card card, ICardGroupManager cardManager) {
        if (cardManager.insertCard(card)) {
            //成功插入就询问所有观察者
            List<Card> cardList = getObserves(card);
            for (int i = 0; i < cardList.size(); i++) {

            }
        }
        return true;
    }


    @TestFunction
    public void takeDeath() {
        List<MinionCard> deathMinionList = getDeathMinionList();
        for (int i = 0; i < deathMinionList.size(); i++) {
            takeDeath(deathMinionList.get(i));
        }
    }


    /**
     * 获得并且移除死亡的随从
     */
    @TestFunction
    private List<MinionCard> getDieMinionList() {
        return null;
    }

    @TestFunction
    public void takeDeath(final MinionCard minionCard) {
        List<Card> cardList = getObserves(minionCard);
        cardList.add(minionCard);

        for (int i = 0; i < cardList.size(); i++) {
            List<Card.IEvent> events = cardList.get(i).getEventList(Card.EventType.MINION_DIE);
            if (events == null) {
                continue;
            }
            for (int j = 0; j < events.size(); j++) {
                events.get(j).run(minionCard, null);
            }
        }

    }


    /**
     * 获取观察者 队列锁定
     *
     * @param card
     * @return
     */
    protected final List<Card> getObserves(Card card) {
        List<Card> cardList = new ArrayList<>();//集合中的顺序是 友方随从 敌方随从 敌方奥秘
        cardList.addAll(getHeroManager(card.getHero()).getIBattleFieldManager().getCardList(MinionCard.class));
        cardList.addAll(getHeroManager(card.getHero().getSideHero()).getIBattleFieldManager().getCardList(MinionCard.class));
        cardList.addAll(getHeroManager(card.getHero().getSideHero()).getISecretAreaManager().getCardList(SecretSpellCard.class));
        return cardList;
    }

    public boolean canInsertCardToTargetArea(Card card, HeroManager heroManager) {
        return true;
    }


    /**
     * 对一个随从造成伤害
     *
     * @param source      伤害来源
     * @param cardWrapper 受害人
     */
    public void takeDamage(Card source, CardWrapper cardWrapper) {
        onEvent(Card.EventType.BEFORE_TAKE_DAMAGE, cardWrapper, source);
        int damage = cardWrapper.getDamage();
        cardWrapper.getOriginalCard().takeDamage(damage);
        onEvent(Card.EventType.TAKES_DAMAGE, cardWrapper, source);
    }

    /**
     * 有血量的随从相互攻击
     *
     * @param source 发起攻击的卡
     * @param target 受到攻击的卡
     */
    public void AbsHealthCardAttackEach(final AbsHealthCard source, AbsHealthCard target) {
        //先进行攻击前事件
        CardWrapper cardWrapper = new CardWrapper();
        cardWrapper.setFinalCard(target);
        do {
            cardWrapper.setOriginalCard(cardWrapper.getFinalCard());
            onEvent(Card.EventType.BEFORE_ATTACK, cardWrapper, source);
            //当防御者不在改变的时候，进行攻击事件阶段
        } while (cardWrapper.getOriginalCard() != cardWrapper.getFinalCard());
        onEvent(Card.EventType.ON_ATTACK, cardWrapper, source);

        //判断攻击者和被攻击者是否都在战场 如果有一方不在场就中断
        if (source.getICardGroupManager() instanceof ICardGroupManager.IBattleFieldManager && cardWrapper.getFinalCard().getICardGroupManager() instanceof ICardGroupManager.IBattleFieldManager) {
            //攻击的时候 防御方受到伤害先结算  结算完成后在结算进攻方
            AbsHealthCard defenseCard = (AbsHealthCard) cardWrapper.getOriginalCard();
            CardWrapper defenseCardWrapper = new CardWrapper();
            defenseCardWrapper.setOriginalCard(defenseCard);
            defenseCardWrapper.setDamage(source.getCurrentAttack());
            takeDamage(source, defenseCardWrapper);

            CardWrapper attackCardWrapper = new CardWrapper();
            attackCardWrapper.setOriginalCard(source);
            attackCardWrapper.setDamage(defenseCard.getCurrentAttack());
            takeDamage(defenseCard, attackCardWrapper);

            //伤害结算完成后 执行攻击后事件
            onEvent(Card.EventType.AFTER_ATTACK, cardWrapper, source);


        }

    }


    public void heroDrawCardFromDeckToHand(Card.Hero hero) {

    }


    public Card.Hero getCurrentActiveHero() {
        return null;
    }
}
