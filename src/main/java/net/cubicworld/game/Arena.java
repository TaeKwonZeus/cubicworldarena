package net.cubicworld.game;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class Arena {
    private final int id;

    private final List<UUID> redTeam;
    private final List<UUID> blueTeam;
}
