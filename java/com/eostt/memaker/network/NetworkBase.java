package com.eostt.memaker.network;

import com.eostt.memaker.capability.ThirstyValue;
import com.eostt.memaker.util.IHasModel;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLEventChannel;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class NetworkBase implements IHasModel {


    @Override
    public void registerModels() {
        NetWork.CHANNEL.register(NetWork.class);
    }



        public static class NetWork {
            private static final String NAME = "THIRSTYVALUE";
            private static final FMLEventChannel CHANNEL = NetworkRegistry.INSTANCE.newEventDrivenChannel(NAME);

            @SubscribeEvent
            @SideOnly(Side.CLIENT)
            public static void onClientCustomPacket(FMLNetworkEvent.ClientCustomPacketEvent event) {
                //todo send message to validate the capability in the players
                ByteBuf buf=event.getPacket().payload();
                float thirstyValue=buf.readFloat();
                Minecraft mc=Minecraft.getMinecraft();
                mc.addScheduledTask(()->{
                    EntityPlayer player=mc.player;
                    ThirstyValue value=player.getCapability(ThirstyValue.THIRSTY_VALUE,null);
                    value.setThirstyValue(thirstyValue);
                });
            }
            public static void sendClientCustomPacket(EntityPlayer player){
                PacketBuffer buf=new PacketBuffer(Unpooled.buffer());
                ThirstyValue value=player.getCapability(ThirstyValue.THIRSTY_VALUE,null);
                if (value != null) {
                    buf.writeFloat(value.getThirstyValue());
                }
                CHANNEL.sendTo(new FMLProxyPacket(buf,NAME), (EntityPlayerMP) player);
            }
        }
    }

