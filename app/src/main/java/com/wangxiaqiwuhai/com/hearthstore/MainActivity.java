package com.wangxiaqiwuhai.com.hearthstore;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.umeng.analytics.MobclickAgent;
import com.wangxiaqiwuhai.com.hearthstore.manager.BattleFieldManager;

public class MainActivity extends Activity {
    private LinearLayout heroHand;
    private LinearLayout battleField;
    private LinearLayout mLinearLayout;
    private BattleFieldManager battleFieldManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);
//        heroHand = (LinearLayout) findViewById(R.id.herohand);
//        HandCardManager handCardManager = new HandCardManager(heroHand);
//        handCardManager.insert(new LeperGnome(true));
//        handCardManager.insert(new CounterSpell());
//
//        battleField = (LinearLayout) findViewById(R.id.battlefield);
//        mLinearLayout = (LinearLayout) findViewById(R.id.enemyhand);
//        battleFieldManager = new BattleFieldManager(battleField, mLinearLayout);
//        battleFieldManager.joinBattleField(new LeperGnome(true));
//        battleFieldManager.joinBattleField(new SorcererApprentice(false));
////        BattleFieldManager battleFieldManager=new BattleFieldManager(battleField);
////        battleFieldManager.insert(new CounterSpell());
////        battleFieldManager.insert(new SorcererApprentice());
//    }
//
//    public void attack(View view) {
//        battleFieldManager.minionAttack(0,0);
   }
    public void attack(View view) {
//        battleFieldManager.minionAttack(0,0);
        MobclickAgent.onEvent(this,"100");
    }
    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
