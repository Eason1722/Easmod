package com.eostt.memaker.items;

import com.eostt.memaker.init.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class tabs extends CreativeTabs {
    public tabs() {
        super("eostt_tabs");
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(ModItems.ICON_EOSTT);
    }
}
