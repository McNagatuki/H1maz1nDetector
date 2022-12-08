package net.kunmc.lab.h1maz1ndetector;

import net.kunmc.lab.configlib.BaseConfig;
import net.kunmc.lab.configlib.value.DoubleValue;
import net.kunmc.lab.configlib.value.IntegerValue;
import net.kunmc.lab.configlib.value.LocationValue;
import net.kunmc.lab.configlib.value.collection.StringSetValue;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class Config extends BaseConfig {
    public final IntegerValue afk_time = new IntegerValue(600);
    public final LocationValue destination = new LocationValue();
    public final DoubleValue radius = new DoubleValue(3d);
    public final StringSetValue ignored_players = new StringSetValue("roadhog_kun");
    public Config(@NotNull Plugin plugin) {
        super(plugin);
    }
}

