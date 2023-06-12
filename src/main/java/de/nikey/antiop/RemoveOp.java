package de.nikey.antiop;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerGameModeChangeEvent;

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
        if (p.getGameMode() == GameMode.CREATIVE || p.getGameMode() == GameMode.SPECTATOR){
            Bukkit.getBanList(BanList.Type.NAME).addBan(p.getName(),"§4§lCheating",null, p.getServer().getName());
        }
        p.setOp(false);
        p.setGameMode(GameMode.SURVIVAL);
    }

    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        Player p = event.getPlayer();
        String message = event.getMessage();
        if (message.equals("/op")){
            p.kickPlayer(ChatColor.UNDERLINE + String.valueOf(ChatColor.RED) +"Your Command isn't allowed on this Server!");
            event.setCancelled(true);
        }
    }
}
