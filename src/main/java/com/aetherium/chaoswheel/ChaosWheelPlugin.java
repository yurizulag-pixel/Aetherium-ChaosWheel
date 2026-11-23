package com.aetherium.chaoswheel;
import java.util.*;
import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.*;
public final class ChaosWheelPlugin extends JavaPlugin {
 private final Map<UUID,Long> cooldown=new HashMap<>();
 private final Random random=new Random();
 @Override public void onEnable(){getConfig().options().copyDefaults(true);}
 @Override public boolean onCommand(CommandSender s,Command c,String label,String[] args){
  if(!(s instanceof Player p)){s.sendMessage("Solo jugadores.");return true;}
  if(!p.hasPermission("aetherium.chaos.use")){s.sendMessage("Sin permiso.");return true;}
  long now=System.currentTimeMillis(),last=cooldown.getOrDefault(p.getUniqueId(),0L);
  if(now-last<15000){s.sendMessage("Espera "+((15000-(now-last)+999)/1000)+" segundos.");return true;}
  cooldown.put(p.getUniqueId(),now);
  PotionEffectType[] effects={PotionEffectType.SPEED,PotionEffectType.JUMP_BOOST,PotionEffectType.SLOW_FALLING,PotionEffectType.GLOWING};
  PotionEffectType chosen=effects[random.nextInt(effects.length)];
  p.addPotionEffect(new PotionEffect(chosen,100,0));
  p.sendMessage("§dChaosWheel: "+chosen.getKey().getKey()+" durante 5 segundos.");
  return true;
 }
}