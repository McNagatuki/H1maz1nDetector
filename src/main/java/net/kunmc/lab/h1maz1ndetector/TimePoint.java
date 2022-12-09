package net.kunmc.lab.h1maz1ndetector;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class TimePoint {
    private Location location;
    private long time;

    public TimePoint set(Player player) {
        location = player.getLocation();
        time = System.currentTimeMillis();
        return this;
    }

    public boolean isFaceMoved(TimePoint from) {
        if (location.getPitch() != from.location.getPitch()) return true;
//        if (location.getYaw() != from.location.getYaw()) return true;
        return false;
    }

    public boolean isMoved(TimePoint from) {
        if (location.getX() != from.location.getX()) return true;
        if (location.getY() != from.location.getY()) return true;
        if (location.getZ() != from.location.getZ()) return true;

        return isFaceMoved(from);
    }

    public long getDurationSec(TimePoint from) {
        return (time - from.time) / 1000;
    }

    public Location getLocation() {
        return location;
    }
}
