package com.wangxiaqiwuhai.com.hearthstore.interfaces;

import com.wangxiaqiwuhai.com.hearthstore.card.Card;
import com.wangxiaqiwuhai.com.hearthstore.card.MinionCard;

import java.util.List;


/**
 * 卡牌容器抽象管理父类
 */
public interface ICardManager extends IAction{
    /**
     * 向卡牌容器中插入卡牌
     * @param card 要插入的卡
     * @return true表示成功插入 false表示失败
     */
    boolean insertCard(Card card);

    /**
     * 向卡牌容器插入一张卡
     * @param card 要插入的卡
     * @param position 要插入的位置
     * @return true表示成功插入 false表示失败
     */
    boolean insertCard(Card card,int position);

    /**
     * 向卡牌容器插入一堆卡
     * @param cardList 要插入的卡的集合
     * @return true表示成功插入 false表示失败
     */
    boolean insertCardList(List<? extends Card> cardList);


    /**
     * 移除容器中的一张卡
     * @param card 要删除的卡
     * @return true表示成功插入 false表示失败
     */
    boolean deleteCard(Card card);

    /**
     * 移除容器中的一堆卡
     * @param cards 要删除的卡的集合
     * @return true表示成功插入 false表示失败
     */
    boolean deleteCard(List<? extends Card> cards);

    /**
     * 移除容器中指定位置的卡
     */
    Card remove(int position);

    /**
     * 移除指定的卡
     * @return
     */
    Card remove(Card card);

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
    int getCount(Class<? extends Card> cardClazz);

    /**
     * 是否包含指定类的卡牌
     */
    boolean isContainsTargetClassCard(Class<? extends Card> clazz);

    /**
     * 获得容器中指定类型的卡牌集合
     * @param tClass 卡牌的类型
     * @return 卡牌集合
     */
    <T extends Card> List<T> getCardList(Class<T> tClass);

    /**
     * 移除容器中指定类型的卡牌集合
     * @param tClass 卡牌的类型
     * @return 卡牌集合
     */
    <T extends Card> List<T> removeCardList(Class<T> tClass);

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



}
