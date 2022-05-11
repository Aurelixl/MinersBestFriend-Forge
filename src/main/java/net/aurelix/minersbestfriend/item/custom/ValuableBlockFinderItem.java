package net.aurelix.minersbestfriend.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class ValuableBlockFinderItem extends Item{
    public ValuableBlockFinderItem(Item.Properties pProperties) {

        super(pProperties);
    }
    @Override
    public InteractionResult useOn(UseOnContext pContext) {

        BlockPos positionClicked;
        if (pContext.getLevel().isClientSide()) {
            positionClicked = pContext.getClickedPos();
            Player player = pContext.getPlayer();
            boolean foundBlock = false;

            //Block blockBelow = pContext.getLevel().getBlockState(positionClicked.below(i)).getBlock();
            //Blockstate vom angeklickten Block finden
            BlockState blockBelowState = pContext.getLevel().getBlockState(positionClicked);
            //LevelAccessor lA = blockBelow;

            //blockBelow.destroy(lA, positionClicked, blockBelowState);
            //Block mit AIR tauschen
            //Block.updateOrDestroy(blockBelowState, Block.stateById(), 20, blockPos, 0);

            System.out.println(pContext.getClickedFace());




            Block block = pContext.getLevel().getBlockState(positionClicked).getBlock();
            for (int i = 0; i <= 12; i++) {


                if(pContext.getClickedFace().toString() == "north"){
                    block = pContext.getLevel().getBlockState(positionClicked.south(i)).getBlock();
                }else if(pContext.getClickedFace().toString() == "south"){
                    block = pContext.getLevel().getBlockState(positionClicked.north(i)).getBlock();
                }else if(pContext.getClickedFace().toString() == "west"){
                    block = pContext.getLevel().getBlockState(positionClicked.east(i)).getBlock();
                }else if(pContext.getClickedFace().toString() == "east"){
                    block = pContext.getLevel().getBlockState(positionClicked.west(i)).getBlock();
                }

                if (isValuableBlock(block)) {
                    outputValuableCoordinates(positionClicked.below(i), player, block);
                    foundBlock = true;
                    break;
                }
            }

            if (!foundBlock) {
                player.sendMessage(new TranslatableComponent("item.minersbestfriend.valuable_block_finder.no_valuables"), player.getUUID());
            }
        }

        pContext.getItemInHand().hurtAndBreak(1, pContext.getPlayer(), (player) -> player.broadcastBreakEvent(player.getUsedItemHand()));



        return super.useOn(pContext);

    }

    private void outputValuableCoordinates(BlockPos blockPos, Player player, Block blockBelow){
        TranslatableComponent foundValuable = new TranslatableComponent("item.minersbestfriend.valuable_block_finder.foundValuable");
        double tick = 20.0;
        player.sendMessage(foundValuable, player.getUUID());
        player.sendMessage(new TextComponent(blockBelow.asItem().getRegistryName().toString()), player.getUUID());

        //player.sendMessage(new TextComponent(player.getPosition(20).toString()) ,player.getUUID());
    }

    private boolean isValuableBlock(Block block){
        return block == Blocks.COAL_ORE || block == Blocks.DIAMOND_ORE || block ==  Blocks.IRON_ORE || block == Blocks.DEEPSLATE_COAL_ORE || block == Blocks.DEEPSLATE_DIAMOND_ORE
                || block == Blocks.DEEPSLATE_EMERALD_ORE || block == Blocks.EMERALD_ORE || block == Blocks.DEEPSLATE_IRON_ORE;

    }
}
