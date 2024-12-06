package q4niel.natures_cauldron.mixin;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(Items.class)
public class ItemsMixin {
    @ModifyArg (
            method = "<clinit>",
            at = @At (
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/GlassBottleItem;<init>(Lnet/minecraft/item/Item$Settings;)V"
            ),
            index = 0
    )
    private static Item.Settings GlassBottleItem(Item.Settings settings) {
        return settings.maxCount(16);
    }

    @ModifyArg (
            method = "<clinit>",
            at = @At (
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/Item$Settings;maxCount(I)Lnet/minecraft/item/Item$Settings;",
                    ordinal = 97 // Potion
            ),
            index = 0
    )
    private static int potionCount(int maxCount) { return 16; }

    @ModifyArg (
            method = "<clinit>",
            at = @At (
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/Item$Settings;maxCount(I)Lnet/minecraft/item/Item$Settings;",
                    ordinal = 125 // Splash Potion
            ),
            index = 0
    )
    private static int splashCount(int maxCount) { return 16; }

    @ModifyArg (
            method = "<clinit>",
            at = @At (
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/Item$Settings;maxCount(I)Lnet/minecraft/item/Item$Settings;",
                    ordinal = 126 // Lingering Potion
            ),
            index = 0
    )
    private static int lingeringCount(int maxCount) { return 16; }
}