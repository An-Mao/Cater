package anmao.idoll.cater;

import anmao.idoll.cater.block.ModBlocks;
import anmao.idoll.cater.enchantment.ModEnchantments;
import anmao.idoll.cater.entity.ModEntityTypes;
import anmao.idoll.cater.item.ModItems;
import anmao.idoll.cater.networking.ModMessages;
import anmao.idoll.cater.world.dimension.ModDimensions;
import anmao.idoll.cater.world.feature.ModConfiguredFeatures;
import anmao.idoll.cater.world.feature.ModPlacedFeatures;
import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import software.bernie.geckolib3.GeckoLib;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Cater.MOD_ID)
public class Cater
{
    public static final String MOD_ID = "cater";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final TagKey<Item> SanAddOne = ItemTags.create(new ResourceLocation(Cater.MOD_ID,"sanaddone"));
    public static final TagKey<Item> SanAddTwo = ItemTags.create(new ResourceLocation(Cater.MOD_ID,"sanaddtwo"));
    public static final TagKey<Item> SanAddThree = ItemTags.create(new ResourceLocation(Cater.MOD_ID,"sanaddthree"));
    public static final TagKey<Item> SanSubOne = ItemTags.create(new ResourceLocation(Cater.MOD_ID,"sansubone"));
    public static final TagKey<Item> SanSubTwo = ItemTags.create(new ResourceLocation(Cater.MOD_ID,"sansubtwo"));
    //test s
    public Cater()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        ModEnchantments.register(modEventBus);
        ModConfiguredFeatures.register(modEventBus);
        ModPlacedFeatures.register(modEventBus);
        ModDimensions.register();

        GeckoLib.initialize();
        ModEntityTypes.register(modEventBus);

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // Some common setup code
        //LOGGER.info("HELLO FROM COMMON SETUP");
        //LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));
        ModMessages.register();
    }
}
