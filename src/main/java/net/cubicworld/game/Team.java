package net.cubicworld.game;

import lombok.*;
import org.bukkit.Material;

@AllArgsConstructor
@Getter
public enum Team {
    RED(Material.RED_WOOL), BLUE(Material.BLUE_WOOL);

    private final Material material;
}
