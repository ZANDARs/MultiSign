package org.example.zandar.multiSign;

import org.bukkit.command.CommandMap;
import org.bukkit.plugin.java.JavaPlugin;
import org.example.zandar.multiSign.commands.SignCommand;

import java.lang.reflect.Field;
import java.util.Objects;

public final class MultiSign extends JavaPlugin {
    public int maxSignatures;

    @Override
    public void onEnable() {
        registerCommands();
        maxSignatures = getConfig().getInt("max-signatures", 2);
        saveDefaultConfig();

        // Plugin startup logic

    }

    @Override
    public void onDisable() {

    }
        // Plugin shutdown logic

    private void registerCommands() {
        try {
            Field commandMapField = getServer().getClass().getDeclaredField("commandMap");
            commandMapField.setAccessible(true);
            CommandMap commandMap = (CommandMap) commandMapField.get(getServer());

            SignCommand signCommand = new SignCommand(this);
            commandMap.register("signbook", signCommand);
        } catch (Exception e) {
            getLogger().severe("Не вдалося зареєструвати команду!");
            e.printStackTrace();
        }

        }
}
