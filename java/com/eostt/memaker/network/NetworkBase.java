package com.eostt.memaker.network;

import com.eostt.memaker.Main;
import com.eostt.memaker.util.IHasModel;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLEventChannel;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class NetworkBase implements IHasModel {
    @Override
    public void registerModels() {


    }
    public static class ThirstyValue{
        private static final String NAME="THIRSTYVALUE";
        private static final FMLEventChannel CHANNEL= NetworkRegistry.INSTANCE.newEventDrivenChannel(NAME);
        @SubscribeEvent
        @SideOnly(Side.CLIENT)
        public static void onClientCustomPacket(FMLNetworkEvent.ClientCustomPacketEvent event){

        }
    }
}
