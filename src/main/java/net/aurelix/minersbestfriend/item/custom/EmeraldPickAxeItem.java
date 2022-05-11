package net.aurelix.minersbestfriend.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolActions;

public class EmeraldPickAxeItem extends DiggerItem {
    public EmeraldPickAxeItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Item.Properties pProperties) {
        super((float)pAttackDamageModifier, pAttackSpeedModifier, pTier, BlockTags.MINEABLE_WITH_PICKAXE, pProperties);
    }
    @Override
    public boolean canPerformAction(ItemStack stack, net.minecraftforge.common.ToolAction toolAction) {
        System.out.println("perform action");
        return ToolActions.DEFAULT_PICKAXE_ACTIONS.contains(toolAction);
    }
    @Override
    public InteractionResult useOn(UseOnContext pContext){
        /*
        System.out.println("useOn");
        System.out.println("pContext : " + pContext);

        BlockPos positionClicked;
        positionClicked = pContext.getClickedPos();
        Player player = pContext.getPlayer();
        Level level = pContext.getLevel();

        Block block = pContext.getLevel().getBlockState(positionClicked).getBlock();
        BlockState blockState = pContext.getLevel().getBlockState(positionClicked);

        level.destroyBlock(positionClicked, true);

        System.out.println("block : " + block);
        System.out.println("blockState : " + blockState);
        */
        return super.useOn(pContext);
    }
    @Override
    public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity pMiningEntity) {
        /*
        System.out.println("mineBlock");
        System.out.println("pStack : " + pStack);
        System.out.println("pLevel : " + pLevel);
        System.out.println("pState : " + pState);
        System.out.println("pPos : " + pPos);
        System.out.println("pMiningEntity : " + pMiningEntity);
        */

        if (!pLevel.isClientSide) {
            /**
             * neues Objekt der Blockposition mit Delta-Werten zu dem gegebenen Parameter Blockwert
             */
            BlockPos blockPos = pPos.offset(0, -1, 0);
            System.out.println("blockPos : " + blockPos);

            java.util.UUID uuid = pMiningEntity.getUUID();
            Player playerByUUID = pLevel.getPlayerByUUID(uuid);

            //pStack.can

            if (pState.canHarvestBlock(pLevel, blockPos, playerByUUID)) {
                System.out.println("playerByUUID : " + playerByUUID);
                /** Anschließend den Block unter dem Abgebauten mit abbauen, also die Y-pPos -1 */
                pLevel.destroyBlock(blockPos, true);

                /** Durability des  Werkzeuges welches genutzt wurde um 1 verringern. dabei wird auch die Hand ermittelt,in der die Aktion Ausgeführt wurde*/
                pMiningEntity.getItemInHand(pMiningEntity.swingingArm).hurtAndBreak(1, pMiningEntity, (player) -> player.broadcastBreakEvent(player.getUsedItemHand()));
            }

        }
        return false;
    }
}
