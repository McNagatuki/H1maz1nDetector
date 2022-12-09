package net.kunmc.lab.h1maz1ndetector;

import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collection;
import java.util.Objects;

public class DetectTask extends BukkitRunnable {
    private H1maz1nDetector plugin;
    private Config config;

    DetectTask() {
        plugin = H1maz1nDetector.getPlugin(H1maz1nDetector.class);
        config = plugin.config;
    }

    @Override
    public void run() {
        Collection<? extends Player> players = plugin.getServer().getOnlinePlayers();

        for (Player player : players) {
            if (Objects.isNull(player)) continue;

            String playerName = player.getName();
            if (!plugin.playerData.containsKey(playerName)) continue;

            // check if ignore list contains the player
            if (config.ignored_players.contains(playerName)) continue;

            TimePoint curPoint = new TimePoint().set(player);
            TimePoint lastPoint = plugin.playerData.get(playerName);

            Location destination = config.destination.isEmpty() ? player.getLocation() : config.destination.value();

            // check if player is already near destination
            if (curPoint.getLocation().distance(destination) < config.radius.value()) {
                continue;
            }

            // if player is in vehicle but his/her face has been moved
            if (player.isInsideVehicle() & curPoint.isFaceMoved(lastPoint)) {
                plugin.playerData.put(playerName, curPoint);
                continue;
            }

            // if player has been moved
            if (!player.isInsideVehicle() & curPoint.isMoved(lastPoint)) {
                plugin.playerData.put(playerName, curPoint);
                continue;
            }

            // if player has been stopped
            // if player is still not AFK
            if (curPoint.getDurationSec(lastPoint) < config.afk_time.value()) {
                continue;
            }

            // player is now AFK
            double r = config.radius.value() * (1 - Math.random());
            double theta = Math.PI * Math.random() * 2;
            double x = destination.getX() + r * Math.cos(theta);
            double y = destination.getY();
            double z = destination.getZ() + r * Math.sin(theta);
            player.teleport(player.getLocation().set(x, y, z));

            curPoint = new TimePoint().set(player);
            plugin.playerData.put(playerName, curPoint);

            String text = playerName + "は休憩に行った。";
            player.sendMessage(text);
        }
    }
}
