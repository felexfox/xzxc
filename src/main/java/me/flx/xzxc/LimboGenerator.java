package me.flx.xzxc;

import org.bukkit.*;
import org.bukkit.block.Biome;
import org.bukkit.generator.BiomeProvider;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.generator.WorldInfo;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class LimboGenerator {

    private World world = null;

    public LimboGenerator() {
        WorldCreator creator = new WorldCreator("limbo");
        creator.generateStructures(false).type(WorldType.NORMAL).biomeProvider(new BiomeProvider() {
            @Override
            public @NotNull Biome getBiome(@NotNull WorldInfo worldInfo, int x, int y, int z) {
                return Biome.THE_VOID;
            }

            @Override
            public @NotNull List<Biome> getBiomes(@NotNull WorldInfo worldInfo) {
                return List.of(Biome.THE_VOID);
            }
        }).generator(new ChunkGenerator() {

            @Override
            public @NotNull List<BlockPopulator> getDefaultPopulators(@NotNull World world) {
                return List.of();
            }

            @Override
            public boolean canSpawn(@NotNull World world, int x, int z) {
                return true;
            }

            public Location getFixedSpawnLocation(@NotNull World world, @NotNull Random random) {
                return new Location(world, 0.0D, 100.0D, 0.0D);
            }

        }).seed(0);

        this.world = creator.createWorld();
        assert world != null;

    }

    public World getWorld() {
        return world;
    }
}


