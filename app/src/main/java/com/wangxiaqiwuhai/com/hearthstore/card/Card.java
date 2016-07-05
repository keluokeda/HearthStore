package com.wangxiaqiwuhai.com.hearthstore.card;

import android.graphics.Color;
import android.support.annotation.NonNull;

import com.wangxiaqiwuhai.com.hearthstore.R;
import com.wangxiaqiwuhai.com.hearthstore.interfaces.ICardGroupManager;
import com.wangxiaqiwuhai.com.hearthstore.manager.GameManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 卡片抽象父类
 */
public abstract class Card {

    //常量

    //最高优先级
    public static final long MAX_PRIORITY = Long.MAX_VALUE - 1000;

    //最低优先级
    public static final long MIN_PRIORITY = Long.MIN_VALUE + 1000;


    //卡牌必须有的属性
    /**
     * 卡牌质量
     */
    protected Quality mQuality;

    /**
     * 随从种族
     */
    protected Race mRace;

    /**
     * 所有英雄
     */
    protected CardHeroClass mCardHeroClass;

    /**
     * 卡牌分类
     */
    protected Type mType;

    /**
     * 卡牌名称
     */
    protected CharSequence mCardName;

    /**
     * 卡牌描述
     */
    protected CharSequence mDescription;

    /**
     * 费用
     */
    protected int mCost;

    /**
     * 分类
     */
    protected Sets mSets;

    /**
     * 主人是 自己英雄 还是 敌方英雄
     */
    protected Hero mHero;


    /**
     * 游戏管理类
     */
    protected GameManager mGameManager;


    public Card(Quality quality, Race race, CardHeroClass cardHeroClass, Type type, CharSequence cardName, CharSequence description, int cost, Sets sets, Hero hero, GameManager gameManager) {
        mQuality = quality;
        mRace = race;
        mCardHeroClass = cardHeroClass;
        mType = type;
        mCardName = cardName;
        mDescription = description;
        mCost = cost;
        mSets = sets;
        mHero = hero;
        mGameManager = gameManager;
    }

    /**
     * 优先级别
     */
    protected int priority;


    /**
     * 特效和buff执行类
     */
    protected ActionExecuter mActionExecuter = new ActionExecuter();

    /**
     * 判断当前卡牌所处的容器
     * <p>{@link com.wangxiaqiwuhai.com.hearthstore.manager.HandCardManager}</p>
     * <p>{@link ICardGroupManager.IBattleFieldManager}</p>
     */
    protected ICardGroupManager mICardGroupManager;



    public int getGroupPosition() {
        return mICardGroupManager.getCardPosition(this);
    }



    public GameManager getGameManager() {
        return mGameManager;
    }

    public void setGameManager(GameManager gameManager) {
        mGameManager = gameManager;
    }

    public CharSequence getCardName() {
        return mCardName;
    }

    public Race getRace() {
        return mRace;
    }


    public Hero getHero() {
        return mHero;
    }

    public void setHero(Hero hero) {
        mHero = hero;
    }


    public TargetType getTargetType() {
        return null;
    }


    public ICardGroupManager getICardGroupManager() {
        return mICardGroupManager;
    }

    public void setICardGroupManager(ICardGroupManager ICardGroupManager) {
        mICardGroupManager = ICardGroupManager;
    }




    public List<IEvent> getEventList(EventType eventType) {
        return mActionExecuter.getEventList(eventType);
    }

    /**
     * 给随从添加一个响应事件
     *
     * @param eventType 响应事件类型
     * @param event     响应事件执行方法
     */
    public void addEvent(EventType eventType, IEvent event) {
        mActionExecuter.addEvent(eventType, event);
    }

    /**
     * 负责执行卡牌功能和维持卡牌buff
     */
    public static class ActionExecuter {

        final Map<EventType, List<IEvent>> mEventTypeListHashMap = new HashMap<>();

        /**
         * 添加一个事件
         *
         * @param eventType 事件类型
         * @param iEvent    事件具体处理方法
         */
        public void addEvent(EventType eventType, IEvent iEvent) {
            List<IEvent> eventList = mEventTypeListHashMap.get(eventType);
            if (eventList == null) {
                eventList = new ArrayList<>();
                mEventTypeListHashMap.put(eventType, eventList);
            }
            eventList.add(iEvent);
        }

        /**
         * 获取一个事件的响应列表
         */
        public List<IEvent> getEventList(EventType eventType) {
            List<IEvent> eventList = mEventTypeListHashMap.get(eventType);
            if (eventList == null) {
                eventList = new ArrayList<>();
                mEventTypeListHashMap.put(eventType, eventList);
            }
            return eventList;
        }
    }

    /**
     * 响应事件 一个事件必须依附在一个卡牌之上
     */
    public interface IEvent extends Comparable<IEvent> {
        /**
         * 遇到事件会调用此方法
         *
         * @param source 当事卡
         * @param target 目标
         */
        void run(Card source, @NonNull CardWrapper target);

        /**
         * 获取该事件的处理优先级
         */
        long getPriority();

        /**
         * 获取依附的卡牌
         */
        Card getAttachCard();
    }

    public static abstract class AbsEvent implements IEvent {
        private long priority;
        private final Card attachCard;

        public AbsEvent(long priority, Card attachCard) {
            this.priority = priority;
            this.attachCard = attachCard;
        }

        public AbsEvent(Card attachCard) {
            this(System.currentTimeMillis(), attachCard);
        }

        @Override
        public Card getAttachCard() {
            return attachCard;
        }

        @Override
        public long getPriority() {
            return priority;
        }

        @Override
        public int compareTo(IEvent another) {
            return another == null ? 0 : (int) (this.getPriority() - another.getPriority());
        }
    }

    /**
     * 卡牌稀有程度
     */
    public enum Quality {
        /**
         * 基础卡
         */
        Free(R.color.transparent),
        /**
         * 白卡
         */
        Common(R.color.wheat),
        /**
         * 蓝卡
         */
        Rare(R.color.app_color),
        /**
         * 紫卡
         */
        Epic(R.color.darkviolet),
        /**
         * 橙卡
         */
        Legendary(R.color.gold);
        private int mColorResource;

        Quality(int colorResource) {
            this.mColorResource = colorResource;
        }

        public int getColorResource() {
            return mColorResource;
        }
    }

    /**
     * 卡牌版本
     */
    public enum Sets {
        /**
         * 标准
         */
        STANDARD(0),
        OLD_GODS(0),
        LEAGUE_OF_EXPLORERS(0),
        GRAND_TOURNAMENT(0),
        BLACKROCK_MOUNTAIN(0),
        CLASSIC(0),
        BASIC(0),
        WILD(1),
        GOBKINES_VS_GNOMES(1),
        MAXXRAMAS(1),
        REWARD(1);

        private int code;

        Sets(int code) {
            this.code = code;
        }

        public static Sets getMode(Sets sets) {
            if (sets.code == 0) {
                return STANDARD;
            } else {
                return WILD;
            }
        }
    }

    /**
     * 事件类型
     */
    public enum EventType {
        BEFORE_PLAY_CARD,//使用一张卡之前
        MAKE_FUNCTION,//使用一张卡时
        AFTER_PLAY_CARD,//使用一张卡之后
        BEFORE_SUMMON_MINION,//召唤一个随从钱
        AFTER_SUMMON_MINION,//召唤一个随从后
        MINION_DIE,//随从死亡
        BEFORE_TAKE_DAMAGE,//造成伤害前
        TAKES_DAMAGE,//随从受伤
        AFTER_TAKE_DAMAGE,//造成伤害后
        BEFORE_ATTACK,//攻击前事件
        ON_ATTACK,//攻击事件
        AFTER_ATTACK,//攻击后事件
        START_OF_TURN,//回合开始
        END_OF_TURN,//回合结束
        DRAW_CARD,//抽牌

    }

    /**
     * 卡牌种类
     */
    public enum Race {
        None(""),//无
        /**
         * 野兽
         */
        Beast("野兽"),
        /**
         * 恶魔
         */
        Demon("恶魔"),
        /**
         * 龙
         */
        Dragon("龙"),
        /**
         * 机械
         */
        Mech("机械"),
        /**
         * 鱼人
         */
        Murloc("鱼人"),
        /**
         * 海盗
         */
        Pirate("海盗"),
        /**
         * 图腾
         */
        Totem("图腾");//图腾
        private String values;

        Race(String values) {
            this.values = values;
        }

        @Override
        public String toString() {
            return this.values;
        }
    }

    public enum Hero {
        enemy,//敌人
        your,//自己
        none;//无

        public Hero getSideHero() {
            if (this == enemy) {
                return your;
            } else if (this == your) {
                return enemy;
            }
            return none;

        }
    }

    /**
     * 所属英雄
     */
    public enum CardHeroClass {
        /**
         * 中立
         */
        Neutral(Color.rgb(114, 94, 81), "中立"),
        /**
         * 德鲁伊
         */
        Druid(Color.rgb(100, 60, 33), "德鲁伊"),
        /**
         * 猎人
         */
        Hunter(Color.rgb(40, 92, 32), "猎人"),
        /**
         * 法师
         */
        Mage(Color.rgb(88, 102, 149), "法师"),
        /**
         * 骑士
         */
        Paladin(Color.rgb(148, 109, 51), "骑士"),
        /**
         * 牧师
         */
        Priest(Color.rgb(162, 170, 175), "牧师"),
        /**
         * 盗贼
         */
        Rogue(Color.rgb(62, 62, 66), "盗贼"),
        /**
         * 萨满
         */
        Shaman(Color.rgb(44, 50, 90), "萨满"),
        /**
         * 术士
         */
        Warlock(Color.rgb(81, 54, 90), "术士"),
        /**
         * 战士
         */
        Warrior(Color.rgb(142, 42, 31), "战士");
        private int color;
        private String name;

        CardHeroClass(int color, String name) {
            this.color = color;
            this.name = name;
        }

        public int getColor() {
            return color;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    /**
     * 卡牌属性
     */
    public enum Type {
        Minion,//随从
        Spell,//法术
        Weapon,//武器
        Hero//英雄
    }


    /**
     * 目标类型
     */
    public enum TargetType {
        Null,//无目标
        Other_Character,//其他目标
        Any_Character,//任何目标
        All_Character,//所有目标
        Random,//随机
        Adjacent,//相邻
        Opponent,//对手
        Player,//玩家
        Each_Player,//所有玩家
        /**
         * 敌方英雄和敌方随从
         */
        Enemy,//敌方
        Enemy_Hero,//敌方英雄
        Enemy_Minion,//敌方仆从
        Frisendly,//友方
        Frisendly_Minion,//友方仆从
        Your,//你
        BattleField//战场上的
    }


}
