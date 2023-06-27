package anmao.idoll.cater.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeModeTab CATER_TAB = new CreativeModeTab("cater") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.INGOT_ZERO.get());
        }
    };
}
