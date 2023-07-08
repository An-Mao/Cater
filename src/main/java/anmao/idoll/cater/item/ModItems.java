package anmao.idoll.cater.item;

import anmao.idoll.cater.Cater;
import anmao.idoll.cater.entity.ModEntityTypes;
import anmao.idoll.cater.item.custom.ItemCoreChaos;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    //
    public static final DeferredRegister<Item> Items = DeferredRegister.create(ForgeRegistries.ITEMS, Cater.MOD_ID);

    public static final RegistryObject<Item> INGOT_ZERO = Items.register("ingot_zero",() -> new Item(new Item.Properties().tab(ModCreativeModeTab.CATER_TAB)));
    public static final RegistryObject<Item> CORE_CHAOS = Items.register("core_chaos",() -> new ItemCoreChaos(new Item.Properties().tab(ModCreativeModeTab.CATER_TAB)));
    public static final RegistryObject<Item> SPAWN_EGG_EQ = Items.register("spawn_egg_eq",() -> new ForgeSpawnEggItem(ModEntityTypes.EQ,0x000000,0xffffff,new Item.Properties().tab(ModCreativeModeTab.CATER_TAB)));
    public static void register(IEventBus eventBus){
        Items.register(eventBus);
    }
}
