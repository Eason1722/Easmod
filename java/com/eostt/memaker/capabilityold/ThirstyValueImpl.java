package com.eostt.memaker.capabilityold;

import com.eostt.memaker.network.NetworkBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.Objects;

@Mod.EventBusSubscriber
public class ThirstyValueImpl {
    @SubscribeEvent
    public static void onPlayerRunning(TickEvent.PlayerTickEvent event){
        EntityPlayer player=event.player;
            ThirstyValue value = player.getCapability(ThirstyValue.THIRSTY_VALUE, null);
//            value.setThirstyValue(value.getThirstyValue()-0.001f);
            NetworkBase.NetWork.sendClientCustomPacket(player);
            if(player.isSprinting()){
                if (value != null && value.getThirstyValue() >= 48000.0f) {
                    player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 1, (int) value.getThirstyValue() / 24000));
                    player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 1, (int) value.getThirstyValue() / 24000));
                    value.setThirstyValue(value.getThirstyValue() - 10);
                }

            } else if (value != null && value.getThirstyValue() <= 24000) {
                player.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS,1,(int)value.getThirstyValue()/12000));
            } else if (value != null && value.getThirstyValue() == 0) {
                player.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 20, 1));
                player.addPotionEffect(new PotionEffect(MobEffects.POISON, 20, 1));
            }
    }
    @SubscribeEvent
    public static void onPlayerDestroying(PlayerDestroyItemEvent event){
        event.getEntityPlayer().addPotionEffect(new PotionEffect(MobEffects.HASTE,30,(int)(Objects.requireNonNull(event.getEntityPlayer().getCapability(ThirstyValue.THIRSTY_VALUE, null)).getThirstyValue()/24000)));
    }
    private static int times=0;
    @SubscribeEvent
    public static void onPlayerJoin(EntityJoinWorldEvent event){
        if(event.getEntity() instanceof EntityPlayer){
            EntityPlayer player= (EntityPlayer) event.getEntity();
            times++;
            if(times==1&&player.getCapability(ThirstyValue.THIRSTY_VALUE,null)!=null){
                Objects.requireNonNull(player.getCapability(ThirstyValue.THIRSTY_VALUE, null)).setThirstyValue(72000.0f);
            }
        }
    }


}

