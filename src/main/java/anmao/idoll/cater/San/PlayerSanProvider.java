package anmao.idoll.cater.San;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerSanProvider implements ICapabilityProvider, INBTSerializable<CompoundTag>
{
    public static Capability<PlayerSan> PLAYER_SAN = CapabilityManager.get(new CapabilityToken<PlayerSan>() {
    });
    private PlayerSan san = null;
    private final LazyOptional<PlayerSan> optional = LazyOptional.of(this::createPlayerSan);

    private PlayerSan createPlayerSan() {
        if (this.san == null)
        {
            this.san = new PlayerSan();
        }
        return this.san;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == PLAYER_SAN)
        {
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createPlayerSan().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createPlayerSan().loadNBTData(nbt);
    }
}
