package q4niel.natures_cauldron.mixin;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.BrewingRecipeRegistry;
import net.minecraft.registry.entry.RegistryEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(BrewingRecipeRegistry.class)
public class BrewingRecipeRegistryMixin {
    private static BrewingRecipeRegistry.Builder builder;
    private static final Item splashModifiers[] = {Items.GUNPOWDER};
    private static final Item lingeringModifiers[] = {Items.DRAGON_BREATH};
    private static final Item baseIngredients[] = {Items.BROWN_MUSHROOM, Items.RED_MUSHROOM};
    private static final Item oppositeIngredients[] = {Items.CRIMSON_FUNGUS, Items.WARPED_FUNGUS};
    private static final Item lengthIngredients[] = {Items.SUGAR};

    private static void register (
            Item coreIngredient,
            RegistryEntry<Potion> defaultPotion,
            RegistryEntry<Potion> defaultPotionOpposite,
            RegistryEntry<Potion> boostedPotion,
            RegistryEntry<Potion> boostedPotionOpposite,
            RegistryEntry<Potion> extendedPotion,
            RegistryEntry<Potion> extendedPotionOpposite
    ) {
        builder.registerRecipes(coreIngredient, defaultPotion);
        for (Item i : oppositeIngredients) {
            if (defaultPotionOpposite != null)
                builder.registerPotionRecipe(defaultPotion, i, defaultPotionOpposite);
        }

        if (boostedPotion != null) {
            builder.registerPotionRecipe(defaultPotion, coreIngredient, boostedPotion);
            for (Item i : oppositeIngredients) {
                if (boostedPotionOpposite != null)
                    builder.registerPotionRecipe(boostedPotion, i, boostedPotionOpposite);
            }
        }

        if (extendedPotion != null) {
            for (Item i : lengthIngredients) {
                builder.registerPotionRecipe(defaultPotion, i, extendedPotion);
            }
            for (Item i : oppositeIngredients) {
                if (extendedPotionOpposite != null)
                    builder.registerPotionRecipe(extendedPotion, i, extendedPotionOpposite);
            }
        }
    }

    /**@author q4niel @reason suckondeeznuts*/@Overwrite()
    public static void registerDefaults(BrewingRecipeRegistry.Builder builder) {
        BrewingRecipeRegistryMixin.builder = builder;

        builder.registerPotionType(Items.POTION);
        builder.registerPotionType(Items.SPLASH_POTION);
        builder.registerPotionType(Items.LINGERING_POTION);

        for (Item i : splashModifiers) {
            builder.registerItemRecipe(Items.POTION, i, Items.SPLASH_POTION);
        }
        for (Item i : lingeringModifiers) {
            builder.registerItemRecipe(Items.SPLASH_POTION, i, Items.LINGERING_POTION);
        }

        for (Item i : baseIngredients) {
            builder.registerPotionRecipe(Potions.WATER, i, Potions.AWKWARD);
        }
        for (Item i : oppositeIngredients) {
            builder.registerPotionRecipe(Potions.WATER, i, Potions.THICK);
        }
        for (Item i : lengthIngredients) {
            builder.registerPotionRecipe(Potions.WATER, i, Potions.MUNDANE);
        }

        register(Items.POPPY, Potions.HEALING, Potions.HARMING, Potions.STRONG_HEALING, Potions.STRONG_HARMING, null, null);
        register(Items.ROSE_BUSH, Potions.REGENERATION, Potions.POISON, Potions.STRONG_REGENERATION, Potions.STRONG_POISON, Potions.LONG_REGENERATION, Potions.LONG_POISON);
        register(Items.SUGAR_CANE, Potions.SWIFTNESS, Potions.SLOWNESS, Potions.STRONG_SWIFTNESS, Potions.STRONG_SLOWNESS, Potions.LONG_SWIFTNESS, Potions.LONG_SLOWNESS);
        register(Items.AMETHYST_SHARD, Potions.STRENGTH, Potions.WEAKNESS, Potions.STRONG_STRENGTH, Potions.WEAKNESS, Potions.LONG_STRENGTH, Potions.LONG_WEAKNESS);
        register(Items.SUNFLOWER, Potions.FIRE_RESISTANCE, null, null, null, Potions.LONG_FIRE_RESISTANCE, null);
        register(Items.VINE, Potions.SLOW_FALLING, null, null, null, Potions.LONG_SLOW_FALLING, null);
        register(Items.LILY_PAD, Potions.LEAPING, null, Potions.STRONG_LEAPING, null, Potions.LONG_LEAPING, null);
        register(Items.CARROT, Potions.NIGHT_VISION, null, null, null, Potions.LONG_NIGHT_VISION, null);
        register(Items.SEA_PICKLE, Potions.WATER_BREATHING, null, null, null, Potions.LONG_WATER_BREATHING, null);
        register(Items.GLOW_LICHEN, Potions.INVISIBILITY, null, null, null, Potions.LONG_INVISIBILITY, null);
        register(Items.POTATO, Potions.TURTLE_MASTER, null, Potions.STRONG_TURTLE_MASTER, null, Potions.LONG_TURTLE_MASTER, null);
        register(Items.CACTUS, Potions.HARMING, Potions.HEALING, Potions.STRONG_HARMING, Potions.STRONG_HEALING, null, null);
        register(Items.WITHER_ROSE, Potions.POISON, Potions.REGENERATION, Potions.STRONG_POISON, Potions.STRONG_REGENERATION, Potions.LONG_POISON, Potions.LONG_REGENERATION);
        register(Items.MOSS_BLOCK, Potions.SLOWNESS, Potions.SWIFTNESS, Potions.STRONG_SLOWNESS, Potions.STRONG_SWIFTNESS, Potions.LONG_SLOWNESS, Potions.LONG_SWIFTNESS);
        register(Items.SHORT_GRASS, Potions.WEAKNESS, Potions.STRENGTH, null, null, Potions.LONG_WEAKNESS, Potions.LONG_STRENGTH);
    }
}