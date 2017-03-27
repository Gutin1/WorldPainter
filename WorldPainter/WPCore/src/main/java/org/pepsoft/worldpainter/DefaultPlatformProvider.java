package org.pepsoft.worldpainter;

import com.google.common.collect.ImmutableList;
import org.pepsoft.minecraft.Chunk;
import org.pepsoft.minecraft.ChunkImpl;
import org.pepsoft.minecraft.ChunkImpl2;
import org.pepsoft.minecraft.ChunkStore;
import org.pepsoft.worldpainter.exporting.*;
import org.pepsoft.worldpainter.mapexplorer.MapRecognizer;
import org.pepsoft.worldpainter.plugins.AbstractPlugin;
import org.pepsoft.worldpainter.plugins.PlatformProvider;
import org.pepsoft.worldpainter.util.MinecraftUtil;

import java.io.File;
import java.util.List;

import static org.pepsoft.worldpainter.Constants.*;
import static org.pepsoft.worldpainter.DefaultPlugin.JAVA_ANVIL;
import static org.pepsoft.worldpainter.DefaultPlugin.JAVA_MCREGION;

/**
 * Created by Pepijn on 9-3-2017.
 */
public class DefaultPlatformProvider extends AbstractPlugin implements PlatformProvider {
    public DefaultPlatformProvider() {
        super("DefaultPlatforms", Version.VERSION);
    }

    @Override
    public List<Platform> getKeys() {
        return PLATFORMS;
    }

    @Override
    public Chunk createChunk(Platform platform, int x, int z, int maxHeight) {
        if (platform.equals(JAVA_MCREGION)) {
            return new ChunkImpl(x, z, maxHeight);
        } else if (platform.equals(JAVA_ANVIL)) {
            return new ChunkImpl2(x, z, maxHeight);
        } else {
            throw new IllegalArgumentException("Platform " + platform + " not supported");
        }
    }

    @Override
    public ChunkStore getChunkStore(Platform platform, File worldDir, int dimension) {
        if (platform.equals(JAVA_MCREGION) || platform.equals(JAVA_ANVIL)) {
            File regionDir;
            switch (dimension) {
                case DIM_NORMAL:
                    regionDir = new File(worldDir, "region");
                    break;
                case DIM_NETHER:
                    regionDir = new File(worldDir, "DIM-1/region");
                    break;
                case DIM_END:
                    regionDir = new File(worldDir, "DIM1/region");
                    break;
                default:
                    throw new IllegalArgumentException("Dimension " + dimension + " not supported");
            }
            return new JavaChunkStore(platform, regionDir, false, null, platform.standardMaxHeight);
        } else {
            throw new IllegalArgumentException("Platform " + platform + " not supported");
        }
    }

    @Override
    public WorldExporter getExporter(World2 world) {
        Platform platform = world.getPlatform();
        if (platform.equals(JAVA_MCREGION) || platform.equals(JAVA_ANVIL)) {
            return new JavaWorldExporter(world);
        } else {
            throw new IllegalArgumentException("Platform " + platform + " not supported");
        }
    }

    @Override
    public File getDefaultExportDir(Platform platform) {
        File minecraftDir = MinecraftUtil.findMinecraftDir();
        return (minecraftDir != null) ? new File(minecraftDir, "saves") : null;
    }

    @Override
    public PostProcessor getPostProcessor(Platform platform) {
        if (platform.equals(JAVA_MCREGION) || platform.equals(JAVA_ANVIL)) {
            return new JavaPostProcessor();
        } else {
            throw new IllegalArgumentException("Platform " + platform + " not supported");
        }
    }

    @Override
    public MapRecognizer getMapRecognizer() {
        return null;
    }

    private static final List<Platform> PLATFORMS = ImmutableList.of(JAVA_ANVIL, JAVA_MCREGION);
}