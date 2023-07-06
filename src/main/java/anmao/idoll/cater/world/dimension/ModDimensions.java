package anmao.idoll.cater.world.dimension;

import anmao.idoll.cater.Cater;
import anmao.idoll.cater.ToApi;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;

public class ModDimensions {
    public static final ResourceKey<Level> AMDIM_ZERO_KEY =
            ResourceKey.create(Registry.DIMENSION_REGISTRY,new ResourceLocation(Cater.MOD_ID,"amdim_zero"));
    public static final ResourceKey<DimensionType> AMDIM_ZERO_TYPE =
            ResourceKey.create(Registry.DIMENSION_TYPE_REGISTRY, AMDIM_ZERO_KEY.location());
    public static final ResourceKey<Level> AMDIM_CENTER_KEY =
            ResourceKey.create(Registry.DIMENSION_REGISTRY,new ResourceLocation(Cater.MOD_ID,"amdim_center"));
    public static final ResourceKey<DimensionType> AMDIM_CENTER_TYPE =
            ResourceKey.create(Registry.DIMENSION_TYPE_REGISTRY, AMDIM_CENTER_KEY.location());
    public static void register(){
        ToApi.echo("reg moddim for" + Cater.MOD_ID);
    }
}
