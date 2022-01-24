package com.main;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public final class Main extends JavaPlugin implements Listener {
    World world;
    World worldNether;
    private Plugin plugin = this;

    public Main() {
        this.world = Bukkit.getWorld("world");
        this.worldNether = Bukkit.getWorld("world_nether");
    }
    @Override
    public void onEnable() {
        getServer().getWorld("world").setGameRule(GameRule.NATURAL_REGENERATION, false);
        getServer().getWorld("world").setGameRule(GameRule.KEEP_INVENTORY, true);
        getServer().getWorld("world_nether").setGameRule(GameRule.KEEP_INVENTORY, true);
        getServer().getWorld("world_the_end").setGameRule(GameRule.KEEP_INVENTORY, true);
        getServer().getWorld("world").setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        getServer().getWorld("world").setGameRule(GameRule.DO_IMMEDIATE_RESPAWN, true);
        getServer().getPluginManager().registerEvents(this, this);
        Random rand = new Random();
                VeniCriper();

    }

    public void VeniCriper() {
        getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                if (new Random().nextInt(100) < 4) {
                    Iterator var1 = Bukkit.getWorld("world").getEntitiesByClass(Creeper.class).iterator();
                    Creeper c;
                    Random rand = new Random();
                    byte rangeMax;
                    byte rangeMin;
                    int X;
                    int Z;
                    int Y;
                    int clx;
                    int clz;
                    int cly;
                    Location tp;
                    while (var1.hasNext()) {
                        c = (Creeper) var1.next();
                        rand = new Random();
                        rangeMax = 15;
                        rangeMin = -15;
                        X = rand.nextInt(rangeMax - rangeMin + 1) + rangeMin;
                        Z = rand.nextInt(rangeMax - rangeMin + 1) + rangeMin;
                        Y = rand.nextInt(rangeMax - rangeMin + 1) + rangeMin;
                        clx = c.getLocation().getBlockX();
                        clz = c.getLocation().getBlockZ();
                        cly = c.getLocation().getBlockY();
                        tp = (new Location(c.getWorld(), (double) clx, (double) cly, (double) clz)).add((double) X, (double) Y, (double) Z);
                        if (tp.getBlock().getType().equals(Material.NETHERITE_BLOCK)) {
                            return;
                        }
                        c.getWorld().playSound(c.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1.0F, 1.0F);
                        c.teleport(tp);
                    }
                }
            }
        }, 0, 15 * 20L);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    @EventHandler
    public void OnJoin(PlayerJoinEvent e){
        e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 300 * 20, 0, false, false), true);
        e.getPlayer().teleport(new Location(Bukkit.getWorld("world"), 0, 150, 0));
        ItemStack h = new ItemStack(Material.NETHERITE_HELMET);
        ItemStack c = new ItemStack(Material.NETHERITE_CHESTPLATE);
        ItemStack l = new ItemStack(Material.NETHERITE_LEGGINGS);
        ItemStack b = new ItemStack(Material.NETHERITE_BOOTS);
        ItemStack bow = new ItemStack(Material.BOW);
        ItemStack s = new ItemStack(Material.NETHERITE_SWORD);
        bow.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 7);
        bow.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 69);
        s.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 7);
        s.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 120);


        h.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
        c.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
        l.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
        b.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
        h.addUnsafeEnchantment(Enchantment.DURABILITY, 15);
        c.addUnsafeEnchantment(Enchantment.DURABILITY, 15);
        l.addUnsafeEnchantment(Enchantment.DURABILITY, 15);
        b.addUnsafeEnchantment(Enchantment.DURABILITY, 15);
        EntityEquipment eq = e.getPlayer().getEquipment();
        eq.setHelmet(h);
        eq.setItemInOffHand(new ItemStack(Material.TOTEM_OF_UNDYING, 17));
        eq.setChestplate(c);
        eq.setLeggings(l);
        eq.setBoots(b);
        ItemStack ga = new ItemStack(Material.GOLDEN_APPLE, 64);
        ItemStack pick = new ItemStack(Material.NETHERITE_PICKAXE);
        pick.addUnsafeEnchantment(Enchantment.DIG_SPEED, 5);
        pick.addUnsafeEnchantment(Enchantment.SOUL_SPEED, 5);
        pick.addUnsafeEnchantment(Enchantment.DEPTH_STRIDER, 5);
        e.getPlayer().getInventory().setItem(0, s);
        e.getPlayer().getInventory().setItem(1, bow);
        e.getPlayer().getInventory().setItem(2, pick);
        e.getPlayer().getInventory().setItem(3, ga);
        e.getPlayer().getInventory().setItem(10, new ItemStack(Material.ARROW));
        for(int i = 0; i < e.getPlayer().getInventory().getSize(); i++){
            if(e.getPlayer().getInventory().getItem(i) == null){
                e.getPlayer().getInventory().setItem(i, new ItemStack(Material.TOTEM_OF_UNDYING));
            }
        }

    }
    @EventHandler
    public void Mueranporfa(PlayerDeathEvent e){
        Iterator itera = Bukkit.getServer().getOnlinePlayers().iterator();
        while (itera.hasNext()){
            Player p = (Player) itera.next();
            p.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 30 * 20, 29, false, false), true);
            p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_DEATH, 100, -8.4F);
            p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_DEATH, 100, -8.3F);
            p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_DEATH, 100, -8.2F);
            p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_DEATH, 100, -8.1F);
            p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_DEATH, 100, -8.0F);
            p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_DEATH, 100, 30F);

            p.sendTitle(ChatColor.translateAlternateColorCodes('&', "&4&lMEGATROLEO MUERTE"), ChatColor.translateAlternateColorCodes('&', "&6&lHOLA SI %player% te moriste.".replaceAll("%player%", e.getEntity().getName())));
        }
        if(new Random().nextInt(100) <= 25) {

            List<Player> players = new ArrayList<>();
            for (Player online : Bukkit.getOnlinePlayers()) {

                if (Bukkit.getOnlinePlayers().size() > players.size()) {

                    if (players.contains(online)) {

                        return;
                    }

                    players.add(online);
                    

                }
            }

            int randomPlayer = new Random().nextInt(players.size());

            Player player = players.get(randomPlayer);
                Bukkit.getServer().broadcastMessage(ChatColor.DARK_PURPLE+"EL JUGADOR "+player+" MURIO POR OTRO NUB");
                player.setHealth(0);
                player.damage(10000000);
        }
        Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&6??????????????????????? 10000 horas de mutants!!11\n &8&l[&6Basado&8&l]&7 Mr_StupidMutant: &f1zzZzZz"));
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "weather thunder "+240 * 3600);
        e.getEntity().setGameMode(GameMode.SPECTATOR);
    }
    @EventHandler
    public void onJoinActionBar(PlayerJoinEvent e) {
        World world = Bukkit.getWorld("world");
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {

            Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
                @Override
                public void run() {
                    if (world.isThundering()) {
                        long segundos = (long) (world.getWeatherDuration() / 20);
                        long hours = segundos % 86400L / 3600L;
                        long minutes = segundos % 3600L / 60L;
                        long seconds = segundos % 60L;
                        long days = segundos / 86400L;
                        if (days < 1) {
                            String time = String.format("%02d:%02d:%02d", hours, minutes, seconds);
                            String s = ChatColor.GRAY + "Quedan " + time + " de Hola T.";
                            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(s));
                        }
                        if (days >= 1) {
                            String time = String.format("%02d:%02d:%02d", hours, minutes, seconds);
                            String dias = String.format("%02d", days);
                            String m = ChatColor.GRAY + "Quedan " + dias + " dias " + time + " de Hola T.";
                            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(m));
                        }
                    }
                }
            }, 0L, 20L);
        }
    }
    @EventHandler
    public void OPMOBS(CreatureSpawnEvent e){
        if(e.getEntity().getType() != EntityType.PLAYER){
            if(e.getEntity() instanceof LivingEntity){
                ItemStack h = new ItemStack(Material.NETHERITE_HELMET);
                ItemStack c = new ItemStack(Material.NETHERITE_CHESTPLATE);
                ItemStack l = new ItemStack(Material.NETHERITE_LEGGINGS);
                ItemStack b = new ItemStack(Material.NETHERITE_BOOTS);
                ItemStack bow = new ItemStack(Material.BOW);
                ItemStack s = new ItemStack(Material.NETHERITE_SWORD);
                bow.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 69);
                s.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 60);

                h.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
                c.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
                l.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
                b.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
                LivingEntity entity = e.getEntity();
                EntityEquipment eq = entity.getEquipment();
                entity.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100000, 3, false, false), true);
                entity.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100000, 3, false, false), true);
                entity.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 100000, 3, false, false), true);
            if(e.getEntity().getType().equals(EntityType.ZOMBIE)){
                eq.setHelmet(h);
                eq.setChestplate(c);
                eq.setLeggings(l);
                eq.setBoots(b);
                eq.setItemInMainHand(s);
                entity.setCustomName(ChatColor.DARK_RED+"MEGATROLEO");
            }
            if(e.getEntity().getType().equals(EntityType.SKELETON)){
                eq.setHelmet(h);
                eq.setChestplate(c);
                eq.setLeggings(l);
                eq.setBoots(b);
                eq.setItemInMainHand(bow);
               entity.setCustomName(ChatColor.DARK_RED+"MEGATROLEO");
            }
            if(e.getEntity().getType().equals(EntityType.PIGLIN) || e.getEntity().getType().equals(EntityType.ZOMBIFIED_PIGLIN)){
                eq.setHelmet(h);
                eq.setChestplate(c);
                eq.setLeggings(l);
                eq.setBoots(b);
                eq.setItemInMainHand(s);
                entity.setCustomName(ChatColor.DARK_RED+"MEGATROLEO");
            }
            if(e.getEntity().getType().equals(EntityType.MAGMA_CUBE)){
                MagmaCube cube = (MagmaCube) e.getEntity();
                cube.setSize(30);
            }
                if(e.getEntity().getType().equals(EntityType.SLIME)){
                    Slime cube = (Slime) e.getEntity();
                    cube.setSize(30);
                }
                if(e.getEntity().getType().equals(EntityType.CREEPER)){
                    Creeper cr = (Creeper) entity;
                    int rand = new Random().nextInt(2)+1;
                    if(rand == 1){
                        cr.setExplosionRadius(35);
                        cr.setPowered(true);
                        cr.setMaxFuseTicks(10);
                        cr.setCustomName(ChatColor.DARK_RED+"INSTA CREPEER");
                    }
                    if(rand == 2){
                        cr.setExplosionRadius(70);
                        cr.setPowered(true);
                        cr.setCustomName(ChatColor.DARK_RED+"MEGA CREEPER");
                    }
                    if(rand == 3){
                        cr.setExplosionRadius(4000);
                        cr.setPowered(true);
                        cr.setMaxFuseTicks(12000);
                        cr.setCustomName(ChatColor.DARK_RED+"SAD CREEPER");
                    }

                }
                if(e.getEntity().getType().equals(EntityType.ENDERMAN)){
                    Enderman em = (Enderman) entity;
                    if(em.getWorld().getName().equalsIgnoreCase("world_the_end")){
                        Random random = new Random();
                        if(random.nextInt(100) <= 6){
                            Creeper creeper = (Creeper) e.getEntity().getWorld().spawnEntity(em.getLocation(), EntityType.CREEPER);
                            creeper.setCustomName(ChatColor.GOLD+"Ender Creeper (Veni Criper)");
                            creeper.setExplosionRadius(15);
                            creeper.setPowered(true);
                        }
                        if(random.nextInt(100) > 6 && random.nextInt(100) <= 12 ){
                            Ghast ghast = (Ghast) e.getEntity().getWorld().spawnEntity(em.getLocation(), EntityType.GHAST);
                            ghast.setCustomName(ChatColor.GOLD+"VENI ENDER GHAST");
                        }
                    }
                    if(e.getEntity().getType().equals(EntityType.ENDERMAN)){
                        if(!e.isCancelled()){
                            Enderman end = (Enderman) e.getEntity();
                            end.setCustomName(ChatColor.GOLD+"Enderman Dorado");
                            end.setMaxHealth(250);
                            end.setHealth(250);
                            end.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 14, true, true), true);
                        }
                    }
                }
            }
        }
    }
    @EventHandler
    public void OnGhastFireBall(ProjectileLaunchEvent e) {

        Random rand = new Random();

        int randomexplosion = rand.nextInt(3) + 3;

        if (e.getEntity().getShooter() instanceof Ghast && e.getEntity() instanceof Fireball) {

            Fireball fireball = (Fireball) e.getEntity();

            Ghast ghast = (Ghast) fireball.getShooter();

            fireball.setYield(randomexplosion);
        }
    }

    @EventHandler
    public void OnExplode(EntityExplodeEvent e) {

        if (e.getEntity() instanceof Fireball) {

            Fireball fireball = (Fireball) e.getEntity();
            Random rand = new Random();
            int randomexplosion = rand.nextInt(3) + 3;
                e.setCancelled(true);
                fireball.getWorld().createExplosion(fireball.getLocation(), randomexplosion);

            }
        if(e.getEntity().getType().equals(EntityType.CREEPER)){
            Creeper c = (Creeper) e.getEntity();
            c.removePotionEffect(PotionEffectType.SPEED);
            c.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
            c.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);

        }

    }
    @EventHandler
    public void creeperholasexo(EntityDamageEvent e){
        if(e.getEntity().getType().equals(EntityType.GHAST) || e.getEntity().getType().equals(EntityType.CREEPER)){
            if(e.getCause() != EntityDamageEvent.DamageCause.ENTITY_ATTACK && e.getCause() != EntityDamageEvent.DamageCause.FALL && e.getCause() != EntityDamageEvent.DamageCause.VOID){
                e.setCancelled(true);
                LivingEntity c = (LivingEntity) e.getEntity();
                Random rand = new Random();
                int rangeMax = 15;
                int rangeMin = -15;
                int X = rand.nextInt(rangeMax - rangeMin + 1) + rangeMin;
                int Z = rand.nextInt(rangeMax - rangeMin + 1) + rangeMin;
                int Y = rand.nextInt(rangeMax - rangeMin + 1) + rangeMin;
                int clx = c.getLocation().getBlockX();
                int clz = c.getLocation().getBlockZ();
                int cly = c.getLocation().getBlockY();
                Location tp = (new Location(c.getWorld(), (double)clx, (double)cly, (double)clz)).add((double)X, (double)Y, (double)Z);
                if (tp.getBlock().getType().equals(Material.NETHERITE_BLOCK)) {
                    return;
                }

                c.getWorld().playSound(c.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1.0F, 1.0F);
                c.teleport(tp);
            }else if(e.getCause() == EntityDamageEvent.DamageCause.FALL){
                e.setCancelled(true);
            }
        }
    }
    @EventHandler
    public void OnTotem(EntityResurrectEvent e){
        if(e.getEntity().getType().equals(EntityType.PLAYER)){
            Player p = (Player) e.getEntity();
            new BukkitRunnable() {
                @Override
                public void run() {
                    p.playEffect(EntityEffect.TOTEM_RESURRECT);
                }
            }.runTaskLater(this, 10l);
            new BukkitRunnable() {
                @Override
                public void run() {
                    p.playEffect(EntityEffect.TOTEM_RESURRECT);
                }
            }.runTaskLater(this, 20l);
            new BukkitRunnable() {
                @Override
                public void run() {
                    p.playEffect(EntityEffect.TOTEM_RESURRECT);
                }
            }.runTaskLater(this, 30l);
            new BukkitRunnable() {
                @Override
                public void run() {
                    p.playEffect(EntityEffect.TOTEM_RESURRECT);
                }
            }.runTaskLater(this, 40l);
            new BukkitRunnable() {
                @Override
                public void run() {
                    p.playEffect(EntityEffect.TOTEM_RESURRECT);
                }
            }.runTaskLater(this, 50l);
            new BukkitRunnable() {
                @Override
                public void run() {
                    p.playEffect(EntityEffect.TOTEM_RESURRECT);
                }
            }.runTaskLater(this, 60l);
            new BukkitRunnable() {
                @Override
                public void run() {
                    p.playEffect(EntityEffect.TOTEM_RESURRECT);
                }
            }.runTaskLater(this, 70l);
            new BukkitRunnable() {
                @Override
                public void run() {
                    p.playEffect(EntityEffect.TOTEM_RESURRECT);
                }
            }.runTaskLater(this, 80l);
            new BukkitRunnable() {
                @Override
                public void run() {
                    p.playEffect(EntityEffect.TOTEM_RESURRECT);
                }
            }.runTaskLater(this, 90l);
            new BukkitRunnable() {
                @Override
                public void run() {
                    p.playEffect(EntityEffect.TOTEM_RESURRECT);
                }
            }.runTaskLater(this, 100l);
            new BukkitRunnable() {
                @Override
                public void run() {
                    int totems = p.getInventory().all(Material.TOTEM_OF_UNDYING).size() + 1;
                    if(totems >= 9){
                        p.getInventory().removeItem(new ItemStack(Material.TOTEM_OF_UNDYING, 10));
                    }else{
                        e.setCancelled(true);
                    }
                }
            }.runTaskLater(this, 2l);


                if(((Player) e.getEntity()).hasCooldown(Material.TOTEM_OF_UNDYING)){

                    e.setCancelled(true);
                    Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&',"&5&lEl Totem ha consumido el cooldown!!11"));
                }
                Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&',"&5&lEl Jugador &6&l%p%&5&l ha consumido un &6&LTotem De La Inmortalidad!".replaceAll("%p%", p.getDisplayName())));
            getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                @Override
                public void run() {
                    ((Player) e.getEntity()).setCooldown(Material.TOTEM_OF_UNDYING, 1 * 20);
                }
            }, 1L);
        }
    }
    @EventHandler
    public void OnJoinPlayer(PlayerJoinEvent e){
        getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            int i = 300;
            @Override
            public void run() {
                if(i  == 300){
                    e.getPlayer().sendMessage(ChatColor.DARK_PURPLE+"Tu Creeper Spawneara en 15 segundos");
                }
                if(i == 200){
                    e.getPlayer().sendMessage(ChatColor.DARK_PURPLE+"Tu Creeper Spawneara en 10 segundos");
                }
                if(i == 100){
                    e.getPlayer().sendMessage(ChatColor.DARK_PURPLE+"Tu Creeper Spawneara en 5 segundos");
                }
                if(i == 80){
                    e.getPlayer().sendMessage(ChatColor.DARK_PURPLE+"Tu Creeper Spawneara en 4 segundos");
                }
                if(i == 60){
                    e.getPlayer().sendMessage(ChatColor.DARK_PURPLE+"Tu Creeper Spawneara en 3 segundos");

                }
                if(i == 40){
                    e.getPlayer().sendMessage(ChatColor.DARK_PURPLE+"Tu Creeper Spawneara en 2 segundos");
                }
                if(i == 20){
                    e.getPlayer().sendMessage(ChatColor.DARK_PURPLE+"Tu Creeper Spawneara en 1 segundos");
                }
                if(i == 0){
                    i = 300;
                    e.getPlayer().sendMessage(ChatColor.DARK_PURPLE+"Tu Creeper Spawneo");
                    e.getPlayer().getWorld().spawnEntity(e.getPlayer().getLocation(), EntityType.CREEPER);
                }
                i = i - 20;

            }
        }, 0L, 20L);
    }
}
