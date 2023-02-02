package net.cubicworld.game.heroes;

import org.jetbrains.annotations.NotNull;

public interface Hero {
    @NotNull Ability getFirstAbility();
}
