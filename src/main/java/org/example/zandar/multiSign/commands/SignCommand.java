package org.example.zandar.multiSign.commands;


import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.example.zandar.multiSign.MultiSign;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;

public class SignCommand extends BukkitCommand {

    private final MultiSign plugin;

    public SignCommand(MultiSign plugin) {
        super("signbook");
        this.plugin = plugin;
        this.description = "Додає підпис до книги";
        this.usageMessage = "/signbook";
        this.setAliases(Collections.singletonList("sign"));
    }

    public boolean execute(CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            ItemStack item = player.getInventory().getItemInMainHand();

            if (item.getType() == Material.WRITTEN_BOOK) {
                BookMeta meta = (BookMeta) item.getItemMeta();
                String oldAuthor = meta.getAuthor() != null ? meta.getAuthor() : "";
                if (oldAuthor.contains(player.getName())) {
                    player.sendMessage("Ви вже підписали цю книгу!");
                    return true;
                }
                int currentSignatures = oldAuthor.isEmpty() ? 0 : oldAuthor.split(" & ").length;


                if (currentSignatures >= plugin.maxSignatures) {
                    player.sendMessage("Досягнуто максимальну кількість підписантів (" + plugin.maxSignatures + ")");
                    return true;
                }

                meta.setAuthor(oldAuthor + " & " + player.getName());
                item.setItemMeta(meta);



            } else {
                player.sendMessage("Тримайте книгу у руці");
            }
            return true;

            

        }

        return false;
    }

}
