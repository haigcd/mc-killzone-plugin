package tech.clearistic.mckillzone;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

@SerializableAs("Corner")
public class LocationCorner implements ConfigurationSerializable {
    private double x;
    private double y;
    private double z;

    public LocationCorner(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public LocationCorner(Map<String, Object> map) {
        LocationCorner lc = deserialize(map);
        this.x = lc.getX();
        this.y = lc.getY();
        this.z = lc.getZ();
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
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

        m.put("x", this.getX());
        m.put("y", this.getY());
        m.put("z", this.getZ());

        return m;
    }

    public static LocationCorner deserialize(Map<String, Object> m) {
        double x = Double.parseDouble(m.get("x").toString());
        double y = Double.parseDouble(m.get("y").toString());
        double z = Double.parseDouble(m.get("z").toString());

        return new LocationCorner(x, y, z);
    }

    public static LocationCorner valueOf(Map<String, Object> m) {
        return deserialize(m);
    }
}
