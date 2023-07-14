package anmao.idoll.cater.networking;

import anmao.idoll.cater.Cater;
import anmao.idoll.cater.networking.packet.ExampleC2SPacket;
import anmao.idoll.cater.networking.packet.SanC2SPacket;
import anmao.idoll.cater.networking.packet.SanDataSyncS2CPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModMessages {
    private static SimpleChannel INSTANCE;
    private static int packetId = 0;
    private static int id(){
        return packetId++;
    }
    public static void register(){
        SimpleChannel net = NetworkRegistry.ChannelBuilder.named(new ResourceLocation(Cater.MOD_ID,"messages")).networkProtocolVersion(()->"1.0").serverAcceptedVersions(s -> true).clientAcceptedVersions(s -> true).simpleChannel();
        INSTANCE = net;
        net.messageBuilder(ExampleC2SPacket.class,id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(ExampleC2SPacket::new)
                .encoder(ExampleC2SPacket::toBytes)
                .consumerMainThread(ExampleC2SPacket::handle).add();
        net.messageBuilder(SanC2SPacket.class,id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(SanC2SPacket::new)
                .encoder(SanC2SPacket::toBytes)
                .consumerMainThread(SanC2SPacket::handle).add();
        net.messageBuilder(SanDataSyncS2CPacket.class,id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(SanDataSyncS2CPacket::new)
                .encoder(SanDataSyncS2CPacket::toBytes)
                .consumerMainThread(SanDataSyncS2CPacket::handle).add();
    }
    public static <MSG> void sendToServer(MSG msg){
        INSTANCE.sendToServer(msg);
    }
    public static <MSG> void sendToPlayer(MSG msg, ServerPlayer serverPlayer){
        INSTANCE.send(PacketDistributor.PLAYER.with(()->serverPlayer), msg);
    }
}
