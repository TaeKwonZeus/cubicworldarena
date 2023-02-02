package net.cubicworld.game.heroes.mage;

import lombok.Getter;
import net.cubicworld.game.heroes.Ability;
import net.cubicworld.game.heroes.Hero;
import org.jetbrains.annotations.NotNull;

@Getter
public class Mage implements Hero {
    @Override
    public @NotNull Ability getFirstAbility() {
        return new MageFirstAbility();
    }
}
