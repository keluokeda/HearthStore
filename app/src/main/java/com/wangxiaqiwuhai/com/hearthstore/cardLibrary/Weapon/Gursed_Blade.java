package com.wangxiaqiwuhai.com.hearthstore.cardLibrary.Weapon;


import com.wangxiaqiwuhai.com.hearthstore.card.Card;
import com.wangxiaqiwuhai.com.hearthstore.card.MinionCard;
import com.wangxiaqiwuhai.com.hearthstore.card.WeaponCard;
import com.wangxiaqiwuhai.com.hearthstore.interfaces.ICardGroupManager;
import com.wangxiaqiwuhai.com.hearthstore.manager.GameManager;

import java.util.List;
import java.util.Objects;

public class Gursed_Blade extends WeaponCard {

    public Gursed_Blade(Quality quality, Race race, CardHeroClass cardHeroClass, Type type, CharSequence cardName, CharSequence description, int cost, Sets sets, Hero hero, GameManager gameManager, int maxAttack, int maxHealth) {
        super(quality, race, cardHeroClass, type, cardName, description, cost, sets, hero, gameManager, maxAttack, maxHealth);
    }


}
