package com.eostt.memaker.init;

import com.eostt.memaker.items.FoodBase;
import com.eostt.memaker.tabs.EosttTab;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class ModFoods {
    public static final List<ItemFood> FOODS=new ArrayList<>();
    public static final FoodBase EXPLOSION_APPLE=new FoodBase(5,2.5f,"explosion_apple", EosttTab.EOSTT_TABS){
        protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player ) {
            super.onFoodEaten(stack, worldIn, player);
            if(!worldIn.isRemote){
                Explosion ex=new Explosion(worldIn,player,player.posX,player.posY,player.posZ, ((player.experienceLevel)*2),true,true);
                ex.doExplosionA();
                ex.doExplosionB(true);
                player.addExperienceLevel(-5) ;
                player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 400, 1));
                player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 6000, 0));
                player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 6000, 0));
                player.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 2400, 3));
            }
        }
    };

//
}
