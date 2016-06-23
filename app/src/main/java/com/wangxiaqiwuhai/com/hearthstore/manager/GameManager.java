package com.wangxiaqiwuhai.com.hearthstore.manager;

import com.wangxiaqiwuhai.com.hearthstore.card.Card;
import com.wangxiaqiwuhai.com.hearthstore.card.MinionCard;
import com.wangxiaqiwuhai.com.hearthstore.card.SecretSpellCard;
import com.wangxiaqiwuhai.com.hearthstore.card.SpellCard;
import com.wangxiaqiwuhai.com.hearthstore.interfaces.IAction;
import com.wangxiaqiwuhai.com.hearthstore.interfaces.ICardGroupManager;
import com.wangxiaqiwuhai.com.hearthstore.interfaces.IDamageExecuter;
import com.wangxiaqiwuhai.com.hearthstore.interfaces.IDamageManager;

import java.util.ArrayList;
import java.util.List;

/**
 * 游戏管理工具类
 */
public abstract class GameManager implements IDamageExecuter, IAction {

    public static GameManager getInstance() {
        return null;
    }

    HeroManager userHeroManager;
    HeroManager enemyManager;


    public HeroManager getManager(boolean isHero) {
        return isHero ? userHeroManager : enemyManager;
    }

    public HeroManager getUserHeroManager() {
        return userHeroManager;
    }

    public HeroManager getEnemyManager() {
        return enemyManager;
    }

    public abstract List<Card> chooseTargetCard(Card.TargetType targetType, int count);

    public abstract HeroManager getActiveHero();

    public abstract void changeActiveHero();

    /**
     * 选取目标卡牌
     *
     * @param targetType 目标类型
     * @return 不选返回空，不需要目标返回空集合，否则就是 战吼目标或者法术目标集合
     */
    public abstract List<Card> getTargetCardList(Card.TargetType targetType);

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
            onMinionDie(deathMinionList.get(i));
        }

    }

    @Override
    public void onMinionDie(MinionCard deathMinion) {
        List<Card> cardList = getObserves(deathMinion);
        for (int i = 0; i < cardList.size(); i++) {
            cardList.get(i).onMinionDie(deathMinion);
        }

    }

    @Override
    public void onTurnStart() {
        List<MinionCard> cardList = getActiveHero().getIBattleFieldManager().getCardList(MinionCard.class);
        for (int i = 0; i < cardList.size(); i++) {
            cardList.get(i).onTurnStart();
        }
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
    protected abstract List<MinionCard> getDeathMinionList();


    /**
     * 判断是否可以使用这张卡牌
     *
     * @return true表示可以 false表示不可以
     */
    private boolean canUseHandCard(Card card) {
        HeroManager heroManager = getManager(card.isUserHero());
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

        //获取卡牌目标  法术目标或者 随从战吼目标
        List<Card> cardList = getTargetCardList(card.getTargetType());
        if (cardList == null) {
            //没有选择任何目标就放弃使用
            return;
        }

        HeroManager heroManager = getManager(card.isUserHero());
        //扣除水晶
        heroManager.getICrystalManager().takeCost(card);
        //从手牌中移除该卡
        heroManager.getIHandCardManager().remove(card);

        //改变卡牌位置
        moveHandCardToTargetArea(card);

        //使用卡牌阶段
        if (beforePlayCard(card, cardList)) {
            return;
        }

        if (card instanceof MinionCard) {
            playMinionCard(card, cardList);
        } else if (card instanceof SpellCard) {
            playSpellCard(card, cardList);
        }


    }

    /**
     * 把要使用的手牌移动到他应该去的位置
     */
    protected void moveHandCardToTargetArea(Card card) {

    }

    /**
     * 使用一张随从卡
     */
    protected void playMinionCard(Card card, List<Card> cardList) {
        //召唤随从
        beforeSummonMinion((MinionCard) card);

        //战吼
        card.onBattlecry(cardList);
        computeDeath();

        //使用之后
        afterPlayCard(card, cardList);


        //召唤完成
        afterSummonMinion((MinionCard) card);
    }

    @Override
    public void beforeSummonMinion(MinionCard minionCard) {
        List<Card> cardList = getObserves(minionCard);
        for (int i = 0; i < cardList.size(); i++) {
            cardList.get(i).beforeSummonMinion(minionCard);
        }
        computeDeath();
    }


    /**
     * 使用一张法术卡
     */
    protected void playSpellCard(Card card, List<Card> cardList) {
        card.onBattlecry(cardList);
        computeDeath();
        afterPlayCard(card, cardList);
    }

    /**
     * 卡牌使用之前询问各个观察者
     *
     * @param card 被使用的手牌
     * @return true表示拦截该卡牌使用，后面阶段都不执行
     */
    @Override
    public boolean beforePlayCard(Card card, List<Card> targetList) {
        List<Card> cardList = getObserves(card);
        for (int i = 0; i < cardList.size(); i++) {
            if (cardList.get(i).beforePlayCard(card, targetList)) {
                computeDeath();
                return true;
            }
        }
        computeDeath();
        return false;
    }

    @Override
    public boolean afterPlayCard(Card card, List<Card> targetList) {
        List<Card> cardList = getObserves(card);
        for (int i = 0; i < cardList.size(); i++) {
            if (cardList.get(i).afterPlayCard(card, targetList)) {
                computeDeath();
                return true;
            }
        }
        computeDeath();
        return false;
    }


    /**
     * 召唤一个随从 在该过程中不进行死亡结算，有调用此方法的对象申请结算
     *
     * @return
     */
    public boolean summonMinion(MinionCard card) {
        HeroManager heroManager = getManager(card.isUserHero());
        if (heroManager.getIBattleFieldManager().isFull()) {
            return false;
        }
        heroManager.getIBattleFieldManager().insertCard(card);

        beforeSummonMinion(card);

        afterSummonMinion(card);


        return true;
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
                cardList.get(i).onCardGroupInsert(cardManager, card);
            }
        }
        return true;
    }


    /**
     * 获取观察者
     *
     * @param card
     * @return
     */
    protected final List<Card> getObserves(Card card) {
        List<Card> cardList = new ArrayList<>();//集合中的顺序是 友方随从 敌方随从 敌方奥秘
        cardList.addAll(getManager(card.isUserHero()).getIBattleFieldManager().getCardList(MinionCard.class));
        cardList.addAll(getManager(!card.isUserHero()).getIBattleFieldManager().getCardList(MinionCard.class));
        cardList.addAll(getManager(!card.isUserHero()).getISecretAreaManager().getCardList(SecretSpellCard.class));
        return cardList;
    }

    public abstract boolean canInsertCardToTargetArea(Card card, HeroManager heroManager);


    /**
     * 对指定集合的角色造成伤害
     *
     * @param source     伤害来源
     * @param targetList 受害群体
     * @param damage     伤害值
     */
    public void takeDamage(Card source, List<Card> targetList, int damage, IDamageManager damageManager) {
        damageManager.computeDamage(damage, targetList, source, this);
    }

    @Override
    public void executeDamage(Card source, Card target, int damage) {

    }

    public abstract void heroDrawCardFromDeckToHand(boolean isOwner);

    public abstract boolean insertCardToHero(Card card, boolean isOwner);


}
