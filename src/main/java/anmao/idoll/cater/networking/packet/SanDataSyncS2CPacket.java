package anmao.idoll.cater.networking.packet;

import anmao.idoll.cater.client.ClientSanData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SanDataSyncS2CPacket {
    private final int san;
    public SanDataSyncS2CPacket(int san){
        this.san = san;
    }
    public SanDataSyncS2CPacket(FriendlyByteBuf buf){
        this.san = buf.readInt();
    }
    public void toBytes(FriendlyByteBuf buf){
        buf.writeInt(san);
    }
    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(()->{
            ClientSanData.set(san);
        });
        return true;
    }
}
