package com.wangxiaqiwuhai.com.hearthstore;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.umeng.analytics.MobclickAgent;
import com.wangxiaqiwuhai.com.hearthstore.card.Card;
import com.wangxiaqiwuhai.com.hearthstore.cardLibrary.Minion.Deathwing_Dragonlord;
import com.wangxiaqiwuhai.com.hearthstore.cardLibrary.Minion.Mayun;
import com.wangxiaqiwuhai.com.hearthstore.cardLibrary.Weapon.Gursed_Blade;
import com.wangxiaqiwuhai.com.hearthstore.cardLibrary.hero.JiHero;
import com.wangxiaqiwuhai.com.hearthstore.manager.BattleFieldManager;
import com.wangxiaqiwuhai.com.hearthstore.manager.GameManager;
import com.wangxiaqiwuhai.com.hearthstore.manager.HandCardManager;
import com.wangxiaqiwuhai.com.hearthstore.manager.HeroManager;

public class MainActivity extends Activity {
    private BattleFieldManager mEnemyBattle;
    private BattleFieldManager mYourBattle;
    private HandCardManager mEnemyHandCard;
    private HandCardManager mYourHandCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEnemyBattle= (BattleFieldManager) findViewById(R.id.enemy_battle);
        mEnemyHandCard= (HandCardManager) findViewById(R.id.enemy_hand_card);
        mYourBattle= (BattleFieldManager) findViewById(R.id.your_battle);
        mYourHandCard= (HandCardManager) findViewById(R.id.your_hand_card);


        HeroManager enemyHeroManager=new HeroManager(findViewById(R.id.enemy_area), Card.Hero.enemy,mEnemyHandCard,mEnemyBattle);
        HeroManager yourHeroManager=new HeroManager(findViewById(R.id.your_area), Card.Hero.your,mYourHandCard,mYourBattle);
        GameManager gameManager=new GameManager(yourHeroManager,enemyHeroManager,this);
        enemyHeroManager.setHeroCard(new JiHero(Card.Hero.enemy,gameManager));
        yourHeroManager.setHeroCard(new JiHero(Card.Hero.your,gameManager));

        Deathwing_Dragonlord deathwing_dragonlord=new Deathwing_Dragonlord(Card.Hero.your,gameManager);
        Deathwing_Dragonlord deathwing_dragonlord1=new Deathwing_Dragonlord(Card.Hero.enemy,gameManager);
        Mayun mayun=new Mayun(Card.Hero.your,gameManager);
        mYourBattle.insertCard(mayun);
        deathwing_dragonlord.setGameManager(gameManager);

        mEnemyBattle.insertCard(deathwing_dragonlord1);

        mYourHandCard.insertCard(deathwing_dragonlord);

    }


}
