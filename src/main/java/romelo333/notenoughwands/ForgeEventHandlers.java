package romelo333.notenoughwands;

import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.ExplosionEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import romelo333.notenoughwands.Items.FreezingWand;
import romelo333.notenoughwands.varia.WrenchChecker;

import java.util.List;

public class ForgeEventHandlers {
    @SubscribeEvent
    public void onBlockBreakEvent (BlockEvent.BreakEvent event){
        World world = event.world;
        if (world.isRemote) {
            return;
        }
        BlockPos pos = event.pos;
        ProtectedBlocks protectedBlocks = ProtectedBlocks.getProtectedBlocks(world);
        if (protectedBlocks.isProtected(world, pos)) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onDetonate(ExplosionEvent.Detonate event) {
        World world = event.world;
        if (world.isRemote) {
            return;
        }
        ProtectedBlocks protectedBlocks = ProtectedBlocks.getProtectedBlocks(world);
        if (!protectedBlocks.hasProtections()) {
            return;
        }


        List<BlockPos> affectedBlocks = event.getAffectedBlocks();

        int i = 0;
        while (i < affectedBlocks.size()) {
            BlockPos block = affectedBlocks.get(i);
            if (protectedBlocks.isProtected(world, block)) {
                affectedBlocks.remove(i);
            } else {
                i++;
            }
        }
    }

    @SubscribeEvent
    public void onPlayerInteractEvent(PlayerInteractEvent event) {
        ItemStack heldItem = event.entityPlayer.getHeldItem();
        if (heldItem == null || heldItem.getItem() == null) {
            return;
        }
        if (event.entityPlayer.isSneaking() && WrenchChecker.isAWrench(heldItem.getItem())) {
            // If the block is protected we prevent sneak-wrenching it.
            ProtectedBlocks protectedBlocks = ProtectedBlocks.getProtectedBlocks(event.world);
            BlockPos pos = event.pos;
            if (protectedBlocks != null && protectedBlocks.isProtected(event.world, pos)) {
                event.setCanceled(true);
            }
        }

    }

    @SubscribeEvent
    public void onlivingUpdate(LivingEvent.LivingUpdateEvent event){
        if (FreezingWand.frozen.contains(event.entityLiving)){
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onlivingHurt(LivingHurtEvent event){
        if (FreezingWand.frozen.contains(event.entityLiving)){
            event.setCanceled(true);
        }
    }
}