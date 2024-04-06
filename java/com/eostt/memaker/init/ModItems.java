package com.eostt.memaker.init;

import com.eostt.memaker.items.BottleBase;
import com.eostt.memaker.items.ItemBase;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

import static com.eostt.memaker.tabs.EosttTab.EOSTT_TABS;

public class ModItems {
    //    public static final Item RED_POWER=new ItemBase("red_batter",EOSTT_TABS);
    public static final List<Item> ITEMS = new ArrayList<Item>();
    public static final Item WATER_DRINK_BOTTLE=new BottleBase("water_drink_bottle",EOSTT_TABS,0);

   public static final Item ICON_EOSTT=new ItemBase("water_bottle",null);


}
