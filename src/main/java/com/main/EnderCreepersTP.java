package com.main;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Iterator;
import java.util.Random;

import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Creeper;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class EnderCreepersTP implements Listener {
    Plugin plugin = Main.getPlugin(Main.class);
    World world;
    World worldNether;

    public EnderCreepersTP() {
        this.world = Bukkit.getWorld("world");
        this.worldNether = Bukkit.getWorld("world_nether");
    }

    public void onEnderCreeperTP() {
        Random rand = new Random();
            Bukkit.getScheduler().scheduleSyncRepeatingTask(this.plugin, new Runnable() {
                @Override
                public void run() {
                    if (new Random().nextInt(100) < 4) {
                        Iterator var1 = world.getEntitiesByClass(Creeper.class).iterator();

                        Creeper c;
                        Random rand;
                        byte rangeMax;
                        byte rangeMin;
                        int X;
                        int Z;
                        int Y;
                        int clx;
                        int clz;
                        int cly;
                        Location tp;
                        while(var1.hasNext()) {
                            c = (Creeper)var1.next();
                            rand = new Random();
                            rangeMax = 15;
                            rangeMin = -15;
                            X = rand.nextInt(rangeMax - rangeMin + 1) + rangeMin;
                            Z = rand.nextInt(rangeMax - rangeMin + 1) + rangeMin;
                            Y = rand.nextInt(rangeMax - rangeMin + 1) + rangeMin;
                            clx = c.getLocation().getBlockX();
                            clz = c.getLocation().getBlockZ();
                            cly = c.getLocation().getBlockY();
                            tp = (new Location(c.getWorld(), (double)clx, (double)cly, (double)clz)).add((double)X, (double)Y, (double)Z);
                            if (tp.getBlock().getType().equals(Material.NETHERITE_BLOCK)) {
                                return;
                            }

                            c.getWorld().playSound(c.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1.0F, 1.0F);
                            c.teleport(tp);
                        }

                        var1 = worldNether.getEntitiesByClass(Creeper.class).iterator();

                        while(var1.hasNext()) {
                            c = (Creeper)var1.next();
                            rand = new Random();
                            rangeMax = 15;
                            rangeMin = -15;
                            X = rand.nextInt(rangeMax - rangeMin + 1) + rangeMin;
                            Z = rand.nextInt(rangeMax - rangeMin + 1) + rangeMin;
                            Y = rand.nextInt(rangeMax - rangeMin + 1) + rangeMin;
                            clx = c.getLocation().getBlockX();
                            clz = c.getLocation().getBlockZ();
                            cly = c.getLocation().getBlockY();
                            tp = (new Location(c.getWorld(), (double)clx, (double)cly, (double)clz)).add((double)X, (double)Y, (double)Z);
                            if (tp.getBlock().getType().equals(Material.NETHERITE_BLOCK)) {
                                return;
                            }

                            c.getWorld().playSound(c.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1.0F, 1.0F);
                            c.teleport(tp);
                        }

                    }
                }
            }, 15 * 20L, 0L);
        }
}
