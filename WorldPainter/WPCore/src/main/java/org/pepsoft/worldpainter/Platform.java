package org.pepsoft.worldpainter;

import com.google.common.collect.ImmutableList;

import java.io.Serializable;
import java.util.List;

import static com.google.common.collect.ImmutableList.copyOf;

/**
 * A WorldPainter-supported block-based game platform.
 *
 * <p>Created by Pepijn on 11-12-2016.
 */
public final class Platform implements Serializable {
    public Platform(String id, String displayName, boolean supportsBiomes, int minMaxHeight, int standardMaxHeight,
                    int maxMaxHeight, int minX, int maxX, int minY, int maxY, List<GameType> supportedGameTypes,
                    List<Generator> supportedGenerators, List<Integer> supportedDimensions) {
        this.id = id;
        this.displayName = displayName;
        this.supportsBiomes = supportsBiomes;
        this.minMaxHeight = minMaxHeight;
        this.standardMaxHeight = standardMaxHeight;
        this.maxMaxHeight = maxMaxHeight;
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
        this.supportedGameTypes = copyOf(supportedGameTypes);
        this.supportedGenerators = copyOf(supportedGenerators);
        this.supportedDimensions = copyOf(supportedDimensions);
    }

    // Object

    @Override
    public boolean equals(Object o) {
        return (o instanceof Platform) && id.equals(((Platform) o).id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    /**
     * The globally unique ID of this platform.
     */
    public final String id;

    /**
     * The human-friendly display name of this platform.
     */
    public final String displayName;

    /**
     * Indicates whether this platform supports storing biome IDs.
     */
    public final boolean supportsBiomes;

    /**
     * Get the lowest map height supported by this platform.
     *
     * @return The lowest map height supported by this platform.
     */
    public final int minMaxHeight;

    /**
     * Get the default height of maps for this platform.
     *
     * @return The default height of maps for this platform.
     */
    public final int standardMaxHeight;

    /**
     * Get the highest map height supported by this platform.
     *
     * @return The highest map height supported by this platform.
     */
    public final int maxMaxHeight;

    /**
     * The lowest possible X coordinate (in blocks) for this platform.
     */
    public final int minX;

    /**
     * The highest possible X coordinate (in blocks) for this platform.
     */
    public final int maxX;

    /**
     * The lowest possible Y coordinate (in blocks) for this platform.
     */
    public final int minY;

    /**
     * The highest possible Y coordinate (in blocks) for this platform.
     */
    public final int maxY;

    /**
     * Get the list of game types supported by this platform.
     */
    public final List<GameType> supportedGameTypes;

    /**
     * The list of generators supported by this platform.
     */
    public final List<Generator> supportedGenerators;

    /**
     * The list of dimension IDs supported by this platform.
     */
    public final List<Integer> supportedDimensions;

    private static final long serialVersionUID = 1L;
}