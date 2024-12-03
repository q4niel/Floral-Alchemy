package q4niel.floral.mixin;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(targets = "net.minecraft.screen.BrewingStandScreenHandler$FuelSlot")
public class BrewingStandScreenHandlerMixin {
    @ModifyArg (
            method = "matches(Lnet/minecraft/item/ItemStack;)Z",
            at = @At (
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z",
                    ordinal = 0
            ),
            index = 0
    )
    private static Item matchesModifyIsOf(Item item) {
        return Items.CHARCOAL;
    }
}