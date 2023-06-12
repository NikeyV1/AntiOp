package de.nikey.antiop;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class RemoveOp implements Listener {
    @EventHandler
    public void onPlayerChangedWorld(PlayerChangedWorldEvent event) {
        Player p = event.getPlayer();
        if (p.isOp()){
            p.setOp(false);
        }
    }

    @EventHandler
    public void onPlayerGameModeChange(PlayerGameModeChangeEvent event) {
        Player p = event.getPlayer();
        p.setGameMode(GameMode.SURVIVAL);
        if (p.getGameMode() == GameMode.CREATIVE || p.getGameMode() == GameMode.SPECTATOR){
            Bukkit.getBanList(BanList.Type.NAME).addBan(p.getName(),"§4§lCheating /n" +
                    "Expires in: Never",null, p.getServer().getName());
        }
        event.setCancelled(true);
        p.setOp(false);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        if (p.isOp()){
            p.kickPlayer(ChatColor.RED + "You were op");
            p.setOp(false);
        }
    }

    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        Player p = event.getPlayer();
        String message = event.getMessage();
        p.setOp(false);
        if (message.contains("/op")|| message.contains("/give") || message.contains("/execute") || message.contains("/gamemode")){
            p.kickPlayer(ChatColor.UNDERLINE + String.valueOf(ChatColor.RED) +"Your Command isn't allowed on this Server!");
            event.setCancelled(true);
        }
    }
}
