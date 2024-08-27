package com.unseen.nb.handler;

import com.unseen.nb.init.ModBlocks;
import com.unseen.nb.init.ModItems;
import com.unseen.nb.util.ModReference;
import com.unseen.nb.util.mapper.AdvancedStateMap;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber
public class RegistryHandler {

        private static IForgeRegistry<Item> itemRegistry;



        @SubscribeEvent
        public static void onItemRegister(RegistryEvent.Register<Item> event) {
            itemRegistry = event.getRegistry();
            event.getRegistry().registerAll(ModItems.ITEMS.toArray(new Item[0]));
            // Items.ALTAR = registerItem(new ItemBlock(Items.ALTAR_BLOCK), "altar");
        }


        @SubscribeEvent
        public static void onBlockRegister(RegistryEvent.Register<Block> event) {
            event.getRegistry().registerAll(ModBlocks.BLOCKS.toArray(new Block[0]));
            // blockRegistry = event.getRegistry();
            // Items.ALTAR_BLOCK = new BlockAltar();
            // Items.ALTAR_BLOCK.setCreativeTab(ModCreativeTabs.ITEMS);
            //  registerGeckoBlock(Items.ALTAR_BLOCK, "altar");

        }




        public static <T extends Item> T registerItem(T item, String name) {
            registerItem(item, new ResourceLocation(ModReference.MOD_ID, name));
            return item;
        }

        public static <T extends Item> T registerItem(T item, ResourceLocation name) {
            itemRegistry.register(item.setRegistryName(name).  setTranslationKey(name.toString().replace(":", ".")));
            return item;
        }

        @SubscribeEvent
        @SideOnly(Side.CLIENT)
        public static void onModelRegister(ModelRegistryEvent event) {


            for (Item item : ModItems.ITEMS) {
                if (item instanceof IHasModel) {
                    ((IHasModel) item).registerModels();
                }
            }
            for (Block block : ModBlocks.BLOCKS) {
                if (block instanceof IStateMappedBlock) {
                    AdvancedStateMap.Builder builder = new AdvancedStateMap.Builder();
                    ((IStateMappedBlock) block).setStateMapper(builder);
                    ModelLoader.setCustomStateMapper(block, builder.build());
                }
                if (block instanceof IHasModel) {
                    ((IHasModel) block).registerModels();
                }
            }


        }

    public interface IStateMappedBlock {
        /**
         * Sets the statemap
         *
         * @param builder
         */
        @SideOnly(Side.CLIENT)
        void setStateMapper(AdvancedStateMap.Builder builder);
    }


}
