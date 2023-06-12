package de.nikey.antiop;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class AntiOp extends JavaPlugin {

    @Override
    public void onEnable() {
        PluginManager plM = Bukkit.getPluginManager();
        plM.registerEvents(new RemoveOp(),this);
        //
        System.out.println("Plugin started!");
        System.out.println("Please add an anti XRay plugin for better experience!");
        for (OfflinePlayer player : Bukkit.getServer().getOperators()){
            player.setOp(false);
        }

    }

    @Override
    public void onDisable() {
        System.out.println("Plugin closing");
        for (OfflinePlayer player : Bukkit.getServer().getOperators()){
            player.setOp(false);
        }
    }
}
