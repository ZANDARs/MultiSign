package org.example.zandar.multiSign;

import org.bukkit.command.CommandMap;
import org.bukkit.plugin.java.JavaPlugin;
import org.example.zandar.multiSign.commands.SignCommand;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;

public final class MultiSign extends JavaPlugin {
    public int maxSignatures;

    @Override
    public void onEnable() {
        maxSignatures = getConfig().getInt("max-signatures", 2);
        saveDefaultConfig();
        this.getServer().getCommandMap().register("sign", new SignCommand(this));

        // Plugin startup logic

    }

    @Override
    public void onDisable() {

    }
        // Plugin shutdown logic



}
