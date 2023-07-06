package anmao.idoll.cater.block;

import anmao.idoll.cater.Cater;
import anmao.idoll.cater.item.ModCreativeModeTab;
import anmao.idoll.cater.item.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Cater.MOD_ID);

    public static final RegistryObject<Block> BLOCK_ZERO = registerBlock("block_zero",()->new Block(BlockBehaviour.Properties.of(Material.STONE).strength(10f).requiresCorrectToolForDrops()), ModCreativeModeTab.CATER_TAB);
    public static final RegistryObject<Block> ORE_ZERO = registerBlock("ore_zero",()->new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).strength(10f).requiresCorrectToolForDrops(),UniformInt.of(3,7)),ModCreativeModeTab.CATER_TAB);
    public static final RegistryObject<Block> DEEP_ORE_ZERO = registerBlock("deep_ore_zero",()->new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).strength(10f).requiresCorrectToolForDrops(),UniformInt.of(3,7)),ModCreativeModeTab.CATER_TAB);
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab){
        RegistryObject<T> toReturn = BLOCKS.register(name,block);
        registerBlockItem(name,toReturn,tab);
        return toReturn;
    };
    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,CreativeModeTab tab){
        return ModItems.Items.register(name,()->new BlockItem(block.get(),new Item.Properties().tab(tab)));
    }
    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    };
}
