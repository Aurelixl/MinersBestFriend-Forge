package net.aurelix.minersbestfriend.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class MinersBestFriendCreativeModeTab {
    public static final CreativeModeTab MINERS_BEST_FRIEND_TAB = new CreativeModeTab("minersbestfriendtab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.EMERALD_PICKAXE.get());
        }
    };
}
