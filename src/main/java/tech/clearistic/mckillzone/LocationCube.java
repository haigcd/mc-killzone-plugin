package tech.clearistic.mckillzone;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

@SerializableAs("Cube")
public class LocationCube implements ConfigurationSerializable {
    private String world;
    private String name;
    private LocationCorner corner1;
    private LocationCorner corner2;

    public LocationCube(String world, String name, LocationCorner corner1, LocationCorner corner2) {
        this.world = world;
        this.name = name;
        this.corner1 = corner1;
        this.corner2 = corner2;
    }

    public String getWorld() {
        return world;
    }

    public void setWorld(String world) {
        this.world = world;
    }

    public LocationCorner getCorner1() {
        return corner1;
    }

    public void setCorner1(LocationCorner corner1) {
        this.corner1 = corner1;
    }

    public LocationCorner getCorner2() {
        return corner2;
    }

    public void setCorner2(LocationCorner corner2) {
        this.corner2 = corner2;
    }

    /**
     * Creates a Map representation of this class.
     * <p>
     * This class must provide a method to restore this class, as defined in
     * the {@link ConfigurationSerializable} interface javadocs.
     *
     * @return Map containing the current state of this class
     */
    @Override
    public @NotNull Map<String, Object> serialize() {
        Map<String, Object> m = new HashMap<>();

        m.put("world", this.getWorld());
        m.put("name", this.getName());
        m.put("corner1", this.getCorner1().serialize());
        m.put("corner2", this.getCorner2().serialize());

        return m;
    }

    public static LocationCube deserialize(Map<String, Object> m) {
        String world = m.get("world").toString();
        String name = m.get("name").toString();
        LocationCorner c1 = LocationCorner.deserialize((Map<String, Object>) m.get("corner1"));
        LocationCorner c2 = LocationCorner.deserialize((Map<String, Object>) m.get("corner2"));

        return new LocationCube(world, name, c1, c2);
    }

    public static LocationCube valueOf(Map<String, Object> m) {
        return deserialize(m);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
