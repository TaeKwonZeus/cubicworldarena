package net.cubicworld.game.heroes;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.cubicworld.game.abilities.Ability;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

@Getter
public abstract class AbstractHero implements Hero {
    public AbstractHero(@NotNull Player player) {
        uuid = player.getUniqueId();
    }

    private final UUID uuid;

    public abstract Ability getFirstAbility();
}
