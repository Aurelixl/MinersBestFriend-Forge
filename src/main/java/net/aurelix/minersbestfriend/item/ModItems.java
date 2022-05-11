package net.aurelix.minersbestfriend.item;

import net.aurelix.minersbestfriend.MinersBestFriend;
import net.aurelix.minersbestfriend.item.custom.EmeraldPickAxeItem;
import net.aurelix.minersbestfriend.item.custom.ValuableBlockFinderItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static net.minecraftforge.registries.ForgeRegistries.Keys.ITEMS;


public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MinersBestFriend.MOD_ID);

    public static final RegistryObject<Item> EMERALD_PICKAXE = ITEMS.register("emerald_pickaxe", () -> new EmeraldPickAxeItem(ModTiers.EMERALD, 2, 3f, new Item.Properties().tab(MinersBestFriendCreativeModeTab.MINERS_BEST_FRIEND_TAB)));
    public static final RegistryObject<Item> VALUABLE_BLOCK_FINDER = ITEMS.register("valuable_block_finder", () -> new ValuableBlockFinderItem(new Item.Properties().tab(MinersBestFriendCreativeModeTab.MINERS_BEST_FRIEND_TAB).durability(64)));


    public static void register(IEventBus eventBus) {

        ITEMS.register(eventBus);
    }
}
