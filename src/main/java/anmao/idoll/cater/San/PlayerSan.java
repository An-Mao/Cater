package anmao.idoll.cater.San;

import net.minecraft.nbt.CompoundTag;

public class PlayerSan {
    private final int SAN_MIN = 0;
    private final int SAN_MAX = 100;
    private int san = SAN_MAX;

    public int getSan() {
        return san;
    }

    public void addSan(int add) {
        this.san = Math.min(san+add,SAN_MAX);
    }

    public void subSan(int sub) {
        this.san = Math.max(san-sub,SAN_MIN);
    }
    public void copyFrom(PlayerSan source){
        this.san = source.san;
    }
    public void saveNBTData(CompoundTag nbt)
    {
        nbt.putInt("SAN",san);
    }
    public void loadNBTData(CompoundTag nbt)
    {
        san = nbt.getInt("SAN");
    }
}
