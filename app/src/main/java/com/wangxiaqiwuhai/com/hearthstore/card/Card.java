package com.wangxiaqiwuhai.com.hearthstore.card;

import android.graphics.Color;

import com.wangxiaqiwuhai.com.hearthstore.R;
import com.wangxiaqiwuhai.com.hearthstore.interfaces.ICardAction;
import com.wangxiaqiwuhai.com.hearthstore.interfaces.ICardGroupManager;
import com.wangxiaqiwuhai.com.hearthstore.manager.GameManager;

/**
 * 卡片抽象父类
 */
public abstract class Card implements ICardAction{
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
     * 攻击力
     */
    protected int mAttack;

    /**
     * 血量
     */
    protected int mHealth;

    /**
     * 游戏管理类
     */
    protected GameManager mGameManager;

    protected boolean ownerIsHero;//主人是 自己英雄 还是 敌方英雄


    public boolean isUserHero() {
        return ownerIsHero;
    }

    public void setUserHero(boolean ownerIsHero) {
        this.ownerIsHero = ownerIsHero;
    }

    /**
     * 目标类型
     */
    protected TargetType mTargetType;

    /**
     * 判断当前卡牌所处的位置
     * <p>{@link com.wangxiaqiwuhai.com.hearthstore.interfaces.IHandCardManager}</p>
     * <p>{@link com.wangxiaqiwuhai.com.hearthstore.interfaces.IBattleFieldManager}</p>
     */
    protected ICardGroupManager mICardGroupManager;

    public TargetType getTargetType() {
        return mTargetType;
    }

    public void setTargetType(TargetType targetType) {
        mTargetType = targetType;
    }

    public ICardGroupManager getICardGroupManager() {
        return mICardGroupManager;
    }

    public void setICardGroupManager(ICardGroupManager ICardGroupManager) {
        mICardGroupManager = ICardGroupManager;

    }

    /**
     * 卡牌稀有程度
     */
    public enum Quality{
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
         Quality(int colorResource){
            this.mColorResource=colorResource;
        }

        public int getColorResource() {
            return mColorResource;
        }
    }

    /**
     * 卡牌种类
     */
    public enum Race{
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
        Race(String values){
            this.values=values;
        }

        @Override
        public String toString() {
            return this.values;
        }
    }

    /**
     * 所属英雄
     */
    public enum CardHeroClass{
        /**
         * 中立
         */
        Neutral(Color.rgb(114,94,81),"中立"),
        /**
         * 德鲁伊
         */
        Druid(Color.rgb(100,60,33),"德鲁伊"),
        /**
         * 猎人
         */
        Hunter(Color.rgb(40,92,32),"猎人"),
        /**
         * 法师
         */
        Mage(Color.rgb(88,102,149),"法师"),
        /**
         * 骑士
         */
        Paladin(Color.rgb(148,109,51),"骑士"),
        /**
         * 牧师
         */
        Priest(Color.rgb(162,170,175),"牧师"),
        /**
         * 盗贼
         */
        Rogue(Color.rgb(62,62,66),"盗贼"),
        /**
         * 萨满
         */
        Shaman(Color.rgb(44,50,90),"萨满"),
        /**
         * 术士
         */
        Warlock(Color.rgb(81,54,90),"术士"),
        /**
         * 战士
         */
        Warrior(Color.rgb(142,42,31),"战士");
        private int color;
        private String name;
        CardHeroClass(int color,String name){
            this.color=color;
            this.name=name;
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
    public enum Type{
        Minion,//随从
        Spell,//法术
        Weapon,//武器
        Hero//英雄
    }

    /**
     * 目标类型
     */
    public enum TargetType{
        Null,//无目标
        Other_Character,//其他目标
        Any_Character,//任何目标
        All_Character,//所有目标
        Random,//随机
        Adjacent,//相邻
        Opponent,//对手
        Player,//玩家
        Each_Player,//所有玩家
        Enemy,//敌方
        Enemy_Hero,//敌方英雄
        Enemy_Minion,//敌方仆从
        Frisendly,//友方
        Frisendly_Minion,//友方仆从
        Your,//你
        BattleField//战场上的
    }



}
