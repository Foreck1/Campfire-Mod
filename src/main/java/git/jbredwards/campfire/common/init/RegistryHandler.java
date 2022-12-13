package git.jbredwards.campfire.common.init;

import git.jbredwards.campfire.client.renderer.model.ModelCampfireInvWrapper;
import git.jbredwards.campfire.client.renderer.model.ModelCampfireLogs;
import git.jbredwards.campfire.common.tileentity.TileEntityBrazier;
import git.jbredwards.campfire.common.tileentity.TileEntityCampfire;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nonnull;

/**
 *
 * @author jbred
 *
 */
@Mod.EventBusSubscriber(modid = "campfire")
final class RegistryHandler
{
    @SubscribeEvent
    static void registerBlock(@Nonnull RegistryEvent.Register<Block> event) {
        event.getRegistry().register(CampfireBlocks.BRAZIER.setRegistryName("brazier").setTranslationKey("campfire:brazier"));
        TileEntity.register("campfire:brazier", TileEntityBrazier.class);

        event.getRegistry().register(CampfireBlocks.CAMPFIRE.setRegistryName("campfire").setTranslationKey("campfire:campfire"));
        TileEntity.register("campfire:campfire", TileEntityCampfire.class);
    }

    @SubscribeEvent
    static void registerItem(@Nonnull RegistryEvent.Register<Item> event) {
        event.getRegistry().register(CampfireItems.BRAZIER.setRegistryName("brazier"));
        OreDictionary.registerOre("campfire", CampfireItems.BRAZIER);

        event.getRegistry().register(CampfireItems.CAMPFIRE.setRegistryName("campfire"));
        OreDictionary.registerOre("campfire", CampfireItems.CAMPFIRE);
    }

    @SubscribeEvent
    static void registerSounds(@Nonnull RegistryEvent.Register<SoundEvent> event) {
        event.getRegistry().register(CampfireSounds.CRACKLE.setRegistryName("blocks.campfire.crackle"));
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    static void registerModels(@Nonnull ModelRegistryEvent event) {
        ModelLoaderRegistry.registerLoader(ModelCampfireInvWrapper.Loader.INSTANCE);
        ModelLoaderRegistry.registerLoader(ModelCampfireLogs.Loader.INSTANCE);

        ModelLoader.setCustomModelResourceLocation(CampfireItems.BRAZIER, 0,
                new ModelResourceLocation(String.valueOf(CampfireItems.BRAZIER.getRegistryName()), "inventory"));

        ModelLoader.setCustomModelResourceLocation(CampfireItems.CAMPFIRE, 0,
                new ModelResourceLocation(String.valueOf(CampfireItems.CAMPFIRE.getRegistryName()), "inventory"));
    }
}
