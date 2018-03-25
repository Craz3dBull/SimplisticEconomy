package net.megaplanet.simplisticeconomy.storage.types;

import net.megaplanet.simplisticeconomy.storage.IStorage;
import net.megaplanet.simplisticeconomy.storage.StorageManager;
import net.megaplanet.simplisticeconomy.storage.TransactionResponse;

public class MySQLStorage implements IStorage {

    private final StorageManager storageManager;

    public MySQLStorage(StorageManager storageManager) {
        this.storageManager = storageManager;
    }

    @Override
    public TransactionResponse loadAccount(String player) {
        return null;
    }

    @Override
    public TransactionResponse depositPlayer(String player, int amount) {
        return null;
    }

    @Override
    public TransactionResponse withdrawPlayer(String player, int amount) {
        return null;
    }

    @Override
    public boolean hasEnough(String player, int amount) {
        return false;
    }

    @Override
    public double getBalance(String player, int amount) {
        return 0;
    }
}
