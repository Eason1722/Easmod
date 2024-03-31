package com.eostt.memaker.capability;

import com.eostt.memaker.network.NetworkBase;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.player.AdvancementEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod.EventBusSubscriber
public class ThirstyValueImpl {
    @SubscribeEvent
    public static void onPlayerRunning(AdvancementEvent event){
            EntityPlayer player=event.getEntityPlayer();
            ThirstyValue value=player.getCapability(ThirstyValue.THIRSTY_VALUE,null);
//            value.setThirstyValue(value.getThirstyValue()-0.001f);
        NetworkBase.NetWork.sendClientCustomPacket(player);
    }


}

