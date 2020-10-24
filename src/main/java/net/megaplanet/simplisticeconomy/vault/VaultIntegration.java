package net.megaplanet.simplisticeconomy.vault;

import net.megaplanet.simplisticeconomy.SimplisticEconomy;
import net.megaplanet.simplisticeconomy.storage.TransactionResponse;
import net.megaplanet.simplisticeconomy.storage.TransactionResponseType;
import net.milkbowl.vault.economy.AbstractEconomy;
import net.milkbowl.vault.economy.EconomyResponse;

import java.text.DecimalFormat;

public class VaultIntegration extends AbstractEconomy implements UnsupportedAccountNameEconomy, UnsupportedBankEconomy {

    private final SimplisticEconomy plugin;

    public VaultIntegration(SimplisticEconomy plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean isEnabled() {
        return plugin.isEnabled();
    }

    @Override
    public String getName() {
        return plugin.getName();
    }

    @Override
    public int fractionalDigits() {
        return 0;
    }

    @Override
    public String format(double amount) {
        return String.valueOf((int) Math.round(amount));
    }

    @Override
    public String currencyNamePlural() {
        return plugin.getStorageManager().getCurrencyPlural();
    }

    @Override
    public String currencyNameSingular() {
        return plugin.getStorageManager().getCurrencySingular();
    }

    @Override
    public boolean hasAccount(String playerName) {
        return plugin.getStorageManager().getStorage().hasAccount(playerName);
    }

    @Override
    public double getBalance(String playerName) {
        return plugin.getStorageManager().getStorage().getBalance(playerName);
    }

    @Override
    public boolean has(String playerName, double amount) {
        return plugin.getStorageManager().getStorage().hasEnough(playerName, amount);
    }

    @Override
    public EconomyResponse withdrawPlayer(String playerName, double amount) {
        return transform(plugin.getStorageManager().getStorage().withdrawPlayer(playerName, amount));
    }

    @Override
    public EconomyResponse depositPlayer(String playerName, double amount) {
        return transform(plugin.getStorageManager().getStorage().depositPlayer(playerName, amount));
    }

    @Override
    public boolean createPlayerAccount(String playerName) {
        return plugin.getStorageManager().getStorage().createAccount(playerName);
    }

    private EconomyResponse transform(TransactionResponse transactionResponse) {
        EconomyResponse.ResponseType responseType = transactionResponse.getTransactionResponseType() == TransactionResponseType.SUCCESS ? EconomyResponse.ResponseType.SUCCESS : EconomyResponse.ResponseType.FAILURE;
        return new EconomyResponse(transactionResponse.getAmount(), transactionResponse.getBalance(), responseType, transactionResponse.getFailureReason());
    }
}
