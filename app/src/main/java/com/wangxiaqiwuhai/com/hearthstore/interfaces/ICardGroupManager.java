package com.wangxiaqiwuhai.com.hearthstore.interfaces;

import com.wangxiaqiwuhai.com.hearthstore.card.Card;
import com.wangxiaqiwuhai.com.hearthstore.card.MinionCard;
import com.wangxiaqiwuhai.com.hearthstore.card.SecretSpellCard;

import java.util.List;


/**
 * 卡牌容器抽象管理父类
 */
public interface ICardGroupManager<C extends Card>  {
    /**
     * 向卡牌容器中插入卡牌
     * @param card 要插入的卡
     * @return true表示成功插入 false表示失败
     */
    boolean insertCard(C card);

    /**
     * 向卡牌容器插入一张卡
     * @param card 要插入的卡
     * @param position 要插入的位置
     * @return true表示成功插入 false表示失败
     */
    boolean insertCard(C card,int position);


    /**
     * 获取容器的最大容量
     */
    int getMaxSize();

    /**
     * 刷新当前容器
     */
    void refresh();

    /**
     * 移除容器中指定位置的卡
     */
    C remove(int position);

    /**
     * 移除指定的卡
     */
    C remove(C card);

    /**
     * 获取卡牌在容器中的位置
     */
    int getCardPosition(C card);

    /**
     * 容器是否是空的
     */
    boolean isEmpty();

    /**
     * 容器是否满了
     */
    boolean isFull();

    /**
     * 获取卡牌容器中指定类型卡牌的总数
     * @param cardClazz
     * @return
     */
    int getCount(Class<? extends C> cardClazz);

    /**
     * 是否包含指定类的卡牌
     */
    boolean isContainsTargetClassCard(Class<? extends C> clazz);

    /**
     * 获得容器中指定类型的卡牌集合
     * @param tClass 卡牌的类型
     * @return 卡牌集合
     */
    <T extends C> List<T> getCardList(Class<T> tClass);

    /**
     * 移除容器中指定类型的卡牌集合
     * @param tClass 卡牌的类型
     * @return 卡牌集合
     */
    <T extends C> List<T> removeCardList(Class<T> tClass);

    /**
     * 卡牌容器中是否包含指定种类的随从卡
     */
    boolean isContainsTargetTypeMinion(Card.Race targetRace);

    /**
     * 获取容器中指定种类的随从卡
     */
    List<MinionCard> getTargetTypeMinion(Card.Race race);

    /**
     * 获取容器中指定种类的随从卡
     */
    List<MinionCard> removeTargetTypeMinion(Card.Race race);



    /**
     *墓地
     */
    interface ICemeteryManager extends ICardGroupManager<Card> {

    }

    /**
     * 牌库类抽象接口
     */
    interface IDeckCardManager extends ICardGroupManager<Card> {


    }



    /**
     * 被移除的卡牌 变形 爆牌
     */
    interface IOutSideCardManager extends ICardGroupManager<Card> {
    }

    /**
     * 奥秘区域管理类
     */
    interface ISecretAreaManager extends ICardGroupManager<SecretSpellCard> {


    }

    /**
     * 战场管理类
     */
    interface IBattleFieldManager extends ICardGroupManager<MinionCard> {

    }

    /**
     * 无卡区
     */
    interface INoneCardAreaManager extends ICardGroupManager<Card>{

    }
}
