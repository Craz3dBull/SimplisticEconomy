package net.megaplanet.simplisticeconomy.command.commands;

import net.megaplanet.simplisticeconomy.command.CommandBase;
import net.megaplanet.simplisticeconomy.command.CommandManager;
import net.megaplanet.simplisticeconomy.storage.IStorage;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

public class CommandBalance extends CommandBase {

    private final CommandManager commandManager;

    public CommandBalance(CommandManager commandManager) {
        super("balance", "balance-command-description", "se.bal", "[player]", 0, 1, new String[] {"bal", "money"});
        this.commandManager = commandManager;
    }

    @Override
    public void onCommand(CommandSender commandSender, String[] args) {
        String playerToCheck = args.length == 1 ? args[0] : commandSender.getName();
        boolean ownBalance = commandSender.getName().equalsIgnoreCase(playerToCheck);

        if(ownBalance && commandSender instanceof ConsoleCommandSender) {
            commandSender.sendMessage(commandManager.getMessagesFile().getMessage("usage")
                    .replace("%usage%", "/balance <player>"));
            return;
        }

        IStorage storage = commandManager.getPlugin().getStorageManager().getStorage();

        double balance = storage.getBalance(playerToCheck);
        String formattedBalance = String.valueOf((int) Math.round(balance));
        commandSender.sendMessage(commandManager.getMessagesFile().getMessage(ownBalance ? "balance-command-own" : "balance-command-other")
        .replace("%player%", playerToCheck)
        .replace("%amount%", formattedBalance)
        .replace("%currency%", commandManager.getPlugin().getStorageManager().getCurrencyPlural()));
    }
}
