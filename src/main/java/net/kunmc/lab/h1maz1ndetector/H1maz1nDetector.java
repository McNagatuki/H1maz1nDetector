package net.kunmc.lab.h1maz1ndetector;

import net.kunmc.lab.commandlib.CommandLib;
import net.kunmc.lab.configlib.ConfigCommand;
import net.kunmc.lab.configlib.ConfigCommandBuilder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Objects;

// TODO: KUNさん等特定プレイヤーの除外リストを作成

public final class H1maz1nDetector extends JavaPlugin implements Listener {
    public static Config config;
    private BukkitTask task;
    HashMap<String, TimePoint> playerData;

    @Override
    public void onEnable() {
        config = new Config(this);
        ConfigCommand configCommand = new ConfigCommandBuilder(config).build();
        CommandLib.register(this, new H1maz1nDetectorCommand(configCommand));

        playerData = new HashMap<>();

        getServer().getPluginManager().registerEvents(this, this);

        task = new DetectTask().runTaskTimer(this, 0, 20);
    }

    @Override
    public void onDisable() {
    }

    public static void print(Object obj) {
        if (Objects.equals(System.getProperty("plugin.env"), "DEV")) {
            System.out.printf("[%s] %s%n", H1maz1nDetector.class.getSimpleName(), obj);
        }
    }

    public static void broadcast(Object obj) {
        if (Objects.equals(System.getProperty("plugin.env"), "DEV")) {
            Bukkit.broadcastMessage(String.format("[%s] %s", H1maz1nDetector.class.getSimpleName(), obj));
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        playerData.put(player.getName(), new TimePoint().set(player));
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        playerData.remove(player.getName());
    }
}
