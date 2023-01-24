package net.cubicworld.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MetadataUtil {
    public static void setTags(@NotNull ItemStack itemStack, @NotNull String... tags) {
        List<Component> components = Arrays.stream(tags)
                .map(MetadataUtil::toInvisibleString)
                .map(tag -> Component.text(tag).asComponent()).toList();

        itemStack.lore(components);
    }

    public static @Nullable List<String> getTags(@NotNull ItemStack itemStack) {
        if (itemStack.lore() == null) return null;

        return Objects.requireNonNull(itemStack.lore()).stream()
                .map(component -> ((TextComponent) component).content())
                .map(MetadataUtil::fromInvisibleString).toList();
    }

    // TODO make working invisible metadata
    public static @NotNull String toInvisibleString(@NotNull String string) {
        // StringBuilder hidden = new StringBuilder();
        // for (char c : string.toCharArray()) hidden.append(ChatColor.COLOR_CHAR).append(c);
        // return hidden.toString();
        return string;
    }

    public static @NotNull String fromInvisibleString(@NotNull String string) {
        // return ChatColor.stripColor(string);
        return string;
    }
}
