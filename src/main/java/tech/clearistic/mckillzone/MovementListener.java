package tech.clearistic.mckillzone;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class MovementListener implements Listener {
    private final LocationCube cube;

    private final World world;
    private final Location cubeCorner1;
    private final Location cubeCorner2;
    private final boolean killSpectators;

    public MovementListener(LocationCube cube, boolean killSpectators) {
        this.cube = cube;

        this.world = Bukkit.getWorld(cube.getWorld());
        this.cubeCorner1 = new Location(world, cube.getCorner1().getX(), cube.getCorner1().getY(), cube.getCorner1().getZ());
        this.cubeCorner2 = new Location(world, cube.getCorner2().getX(), cube.getCorner2().getY(), cube.getCorner2().getZ());
        this.killSpectators = killSpectators;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Location movingTo = event.getTo();

        if (!isKillable(event)) {
            return;
        }

        if (movingTo.getX() > cubeCorner1.getX() && movingTo.getX() < cubeCorner2.getX()
                && movingTo.getY() > cubeCorner1.getY() && movingTo.getY() < cubeCorner2.getY()
                && movingTo.getZ() > cubeCorner1.getZ() && movingTo.getZ() < cubeCorner2.getZ()) {
            Player player = event.getPlayer();

            Bukkit.getLogger().info("Player " + player.getName() + " entered kill zone '"
                    + this.cube.getName() + "', bye...");
            player.setHealth(0.0);

            TextComponent text = Component.text(player.getName() + " entered '"
                    + this.cube.getName() + "' and was terminated");
            Bukkit.getServer().broadcast(text);
        }
    }

    private boolean isKillable(PlayerMoveEvent event) {
        World world = event.getTo().getWorld();
        Player player = event.getPlayer();

        return world.getName().equals(this.world.getName())
                && (killSpectators || player.getGameMode() != GameMode.SPECTATOR);
    }
}
