package com.wangxiaqiwuhai.com.hearthstore.manager;

import com.wangxiaqiwuhai.com.hearthstore.card.Card;
import com.wangxiaqiwuhai.com.hearthstore.card.MinionCard;
import com.wangxiaqiwuhai.com.hearthstore.card.SpellCard;
import com.wangxiaqiwuhai.com.hearthstore.card.WeaponCard;
import com.wangxiaqiwuhai.com.hearthstore.interfaces.IDamageExecuter;
import com.wangxiaqiwuhai.com.hearthstore.interfaces.IDamageManager;

import java.util.List;

/**
 * 游戏管理工具类
 */
@SuppressWarnings("unused")
public abstract class GameManager implements IDamageExecuter {

    public static GameManager getInstance() {
        return null;
    }

    HeroManager userHeroManager;
    HeroManager enemyManager;

    public void takeDamage(int damage, Card.TargetType targetType) {
        userHeroManager.takeDamage(damage, targetType);
        enemyManager.takeDamage(damage, targetType);
    }

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
    public abstract void computeDeath();

    /**
     * 使用一张卡牌
     */
    public void useCard(Card card, List<Card> targetList) {
        HeroManager heroManager = getManager(card.isUserHero());

        //从法力槽中扣除卡牌的水晶
        heroManager.getICrystalManager().takeCost(card);

        //从当前卡牌区域中移除此卡
        card.getICardManager().remove(card);


        if (card instanceof SpellCard) {
            //使用一张法术卡



            if (!beforeUseCard(card, targetList)) {
                //现场都不拦截
                card.onBattlecry(targetList);

                afterUseCard(card, targetList);
            }

            //不管是否使用成功，把这张卡加入墓地
            card.setICardManager(heroManager.getICemeteryManager());

        } else if (card instanceof MinionCard) {
            //从手牌中打出一个随从
            if (!beforeUseCard(card, targetList)) {
                //现场都不拦截
                card.onBattlecry(targetList);

                afterUseCard(card, targetList);
            }

            //把此卡加入到战场
            card.setICardManager(heroManager.getIBattleFieldManager());

        }else if(card instanceof WeaponCard){
            //装备武器

        }


    }

    /**
     * 从手牌中拿出一张卡来使用
     *
     * @param card 要使用的卡
     * @return 不会null表示可以使用, null表示不能使用
     */
    public List<Card> useCardFromHand(Card card) {
        HeroManager heroManager = getManager(card.isUserHero());
        //判断是否有足够的水晶
        if (!heroManager.getICrystalManager().canUseCard(card)) {
            return null;
        }
        //判断当前卡牌能不能插入目标区域
        if (!canInsertCardToTargetArea(card, heroManager)) {
            return null;
        }

        //判断用户是否指定了目标 没有指定目标就放弃使用
        return getTargetCardList(card.getTargetType());

    }

    public abstract boolean canInsertCardToTargetArea(Card card, HeroManager heroManager);

    public abstract void afterUseCard(Card card, List<Card> cardList);

    /**
     * 询问在场的所有卡牌是否有问题
     *
     * @param card     将要使用的卡牌
     * @param cardList 卡牌的作用对象
     * @return true拦截使用 false表示可以使用
     */
    public abstract boolean beforeUseCard(Card card, List<Card> cardList);

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
