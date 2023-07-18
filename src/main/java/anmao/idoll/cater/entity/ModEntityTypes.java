package anmao.idoll.cater.entity;

import anmao.idoll.cater.Cater;
import anmao.idoll.cater.entity.custom.EntityD;
import anmao.idoll.cater.entity.custom.EntityEQ;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

public class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Cater.MOD_ID);

    public static final RegistryObject<EntityType<EntityEQ>> EQ =
            ENTITY_TYPES.register("eq",()->EntityType.Builder.of(EntityEQ::new, MobCategory.MONSTER).sized(1.0f,1.0f).build(new ResourceLocation(Cater.MOD_ID,"eq").toString()));
    public static final RegistryObject<EntityType<EntityD>> D =
            ENTITY_TYPES.register("d",()->EntityType.Builder.of(EntityD::new, MobCategory.MONSTER).sized(1.0f,1.0f).build(new ResourceLocation(Cater.MOD_ID,"d").toString()));
    public static void register (IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }
}
