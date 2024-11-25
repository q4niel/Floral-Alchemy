package q4niel.floral.mixin;

import net.minecraft.block.entity.BrewingStandBlockEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(BrewingStandBlockEntity.class)
public class BrewingStandBlockEntityMixin {

    @ModifyArg (
            method = "tick(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;Lnet/minecraft/block/entity/BrewingStandBlockEntity;)V",
            at = @At (
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z",
                    ordinal = 0
            ),
            index = 0
    )
    private static Item tickModifyIsOf(Item item) {
        return Items.HONEYCOMB;
    }

    @ModifyArg (
            method = "isValid(ILnet/minecraft/item/ItemStack;)Z",
            at = @At (
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z",
                    ordinal = 0
            ),
            index = 0
    )
    private Item isValidModifyIsOf(Item item) {
        return Items.HONEYCOMB;
    }
}