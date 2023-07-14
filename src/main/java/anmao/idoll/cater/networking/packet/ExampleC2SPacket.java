package anmao.idoll.cater.networking.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ExampleC2SPacket {
    public ExampleC2SPacket(){}
    public ExampleC2SPacket(FriendlyByteBuf buf){}
    public void toBytes(FriendlyByteBuf buf){}
    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(()->{
            ServerPlayer serverPlayer = context.getSender();
            ServerLevel serverLevel = serverPlayer.getLevel();
            EntityType.COW.spawn(serverLevel,null,null,serverPlayer.blockPosition(), MobSpawnType.COMMAND,true,false);
        });
        return true;
    }
}
