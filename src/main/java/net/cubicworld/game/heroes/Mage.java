package net.cubicworld.game.heroes;

import lombok.Getter;
import net.cubicworld.game.abilities.Ability;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@Getter
public class Mage extends AbstractHero {
    public Mage(@NotNull Player player) {
        super(player);
    }

    @Override
    public Ability getFirstAbility() {
        return null;
    }
}
