package q4niel.floral.mixin;

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
    private static BrewingRecipeRegistry.Builder BUILDER;

    private static void register (
            Item coreIngredient,
            RegistryEntry<Potion> defaultPotion,
            RegistryEntry<Potion> defaultPotionOpposite,
            RegistryEntry<Potion> boostedPotion,
            RegistryEntry<Potion> boostedPotionOpposite,
            RegistryEntry<Potion> extendedPotion,
            RegistryEntry<Potion> extendedPotionOpposite
    ) {
        BUILDER.registerRecipes(coreIngredient, defaultPotion);
        if (defaultPotionOpposite != null)
            BUILDER.registerPotionRecipe(defaultPotion, Items.ALLIUM, defaultPotionOpposite);

        if (boostedPotion != null) {
            BUILDER.registerPotionRecipe(defaultPotion, coreIngredient, boostedPotion);
            if (boostedPotionOpposite != null)
                BUILDER.registerPotionRecipe(boostedPotion, Items.ALLIUM, boostedPotionOpposite);
        }

        if (extendedPotion != null) {
            BUILDER.registerPotionRecipe(defaultPotion, Items.SUGAR, extendedPotion);
            if (extendedPotionOpposite != null)
                BUILDER.registerPotionRecipe(extendedPotion, Items.ALLIUM, extendedPotionOpposite);
        }
    }

    /**@author q4niel @reason suckondeeznuts*/@Overwrite()
    public static void registerDefaults(BrewingRecipeRegistry.Builder builder) {
        BUILDER = builder;

        BUILDER.registerPotionType(Items.POTION);
        BUILDER.registerPotionType(Items.SPLASH_POTION);
        BUILDER.registerPotionType(Items.LINGERING_POTION);

        BUILDER.registerItemRecipe(Items.POTION, Items.GUNPOWDER, Items.SPLASH_POTION);
        BUILDER.registerItemRecipe(Items.SPLASH_POTION, Items.DRAGON_BREATH, Items.LINGERING_POTION);

        BUILDER.registerPotionRecipe(Potions.WATER, Items.ALLIUM, Potions.THICK);
        BUILDER.registerPotionRecipe(Potions.WATER, Items.SUGAR, Potions.MUNDANE);
        BUILDER.registerPotionRecipe(Potions.WATER, Items.DANDELION, Potions.AWKWARD);

        register(Items.POPPY, Potions.HEALING, Potions.HARMING, Potions.STRONG_HEALING, Potions.STRONG_HARMING, null, null);
        register(Items.ROSE_BUSH, Potions.HARMING, Potions.HEALING, Potions.STRONG_HARMING, Potions.STRONG_HEALING, null, null);
        register(Items.PINK_PETALS, Potions.REGENERATION, Potions.POISON, Potions.STRONG_REGENERATION, Potions.STRONG_POISON, Potions.LONG_REGENERATION, Potions.LONG_POISON);
        register(Items.WITHER_ROSE, Potions.POISON, Potions.REGENERATION, Potions.STRONG_POISON, Potions.STRONG_REGENERATION, Potions.LONG_POISON, Potions.LONG_REGENERATION);
        register(Items.SUNFLOWER, Potions.FIRE_RESISTANCE, null, null, null, Potions.LONG_FIRE_RESISTANCE, null);
        register(Items.KELP, Potions.WATER_BREATHING, null, null, null, Potions.LONG_WATER_BREATHING, null);
        register(Items.HANGING_ROOTS, Potions.STRENGTH, Potions.WEAKNESS, Potions.STRONG_STRENGTH, Potions.WEAKNESS, Potions.LONG_STRENGTH, Potions.LONG_WEAKNESS);
        register(Items.LILAC, Potions.WEAKNESS, Potions.STRENGTH, null, null, Potions.LONG_WEAKNESS, Potions.LONG_STRENGTH);
        register(Items.LILY_OF_THE_VALLEY, Potions.SLOW_FALLING, null, null, null, Potions.LONG_SLOW_FALLING, null);
        register(Items.OXEYE_DAISY, Potions.LEAPING, null, Potions.STRONG_LEAPING, null, Potions.LONG_LEAPING, null);
        register(Items.GLOW_LICHEN, Potions.NIGHT_VISION, null, null, null, Potions.LONG_NIGHT_VISION, null);
        register(Items.BLUE_ORCHID, Potions.SWIFTNESS, Potions.SLOWNESS, Potions.STRONG_SWIFTNESS, Potions.STRONG_SLOWNESS, Potions.LONG_SWIFTNESS, Potions.LONG_SLOWNESS);
        register(Items.PEONY, Potions.SLOWNESS, Potions.SWIFTNESS, Potions.STRONG_SLOWNESS, Potions.STRONG_SWIFTNESS, Potions.LONG_SLOWNESS, Potions.LONG_SWIFTNESS);
        register(Items.PINK_TULIP, Potions.WIND_CHARGED, null, null, null, null, null);
        register(Items.WHITE_TULIP, Potions.OOZING, null, null, null, null, null);
        register(Items.ORANGE_TULIP, Potions.INFESTED, null, null, null, null, null);
        register(Items.RED_TULIP, Potions.WEAVING, null, null, null, null, null);
        register(Items.AZURE_BLUET, Potions.INVISIBILITY, null, null, null, Potions.LONG_INVISIBILITY, null);
        register(Items.CORNFLOWER, Potions.TURTLE_MASTER, null, Potions.STRONG_TURTLE_MASTER, null, Potions.LONG_TURTLE_MASTER, null);
    }
}