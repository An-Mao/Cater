package anmao.idoll.cater.item;

import anmao.idoll.cater.Cater;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    //
    public static final DeferredRegister<Item> Items = DeferredRegister.create(ForgeRegistries.ITEMS, Cater.MOD_ID);

    public static final RegistryObject<Item> INGOT_ZERO = Items.register("ingot_zero",() -> new Item(new Item.Properties().tab(ModCreativeModeTab.CATER_TAB)));
    public static void register(IEventBus eventBus){
        Items.register(eventBus);
    }
}
