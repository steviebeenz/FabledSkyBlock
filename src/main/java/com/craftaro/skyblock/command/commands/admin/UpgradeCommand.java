package com.craftaro.skyblock.command.commands.admin;

import com.craftaro.third_party.com.cryptomorin.xseries.XSound;
import com.craftaro.skyblock.SkyBlock;
import com.craftaro.skyblock.command.SubCommand;
import com.craftaro.skyblock.config.FileManager;
import com.craftaro.skyblock.config.FileManager.Config;
import com.craftaro.skyblock.menus.admin.Upgrade;
import com.craftaro.skyblock.message.MessageManager;
import com.craftaro.skyblock.playerdata.PlayerDataManager;
import com.craftaro.skyblock.sound.SoundManager;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class UpgradeCommand extends SubCommand {
    public UpgradeCommand(SkyBlock plugin) {
        super(plugin);
    }

    @Override
    public void onCommandByPlayer(Player player, String[] args) {
        PlayerDataManager playerDataManager = this.plugin.getPlayerDataManager();
        MessageManager messageManager = this.plugin.getMessageManager();
        SoundManager soundManager = this.plugin.getSoundManager();
        FileManager fileManager = this.plugin.getFileManager();

        Config config = fileManager.getConfig(new File(this.plugin.getDataFolder(), "language.yml"));
        FileConfiguration configLoad = config.getFileConfiguration();

        if (this.plugin.getUpgradeManager() == null) {
            messageManager.sendMessage(player, configLoad.getString("Command.Island.Admin.Upgrade.Disabled.Message"));
            soundManager.playSound(player, XSound.BLOCK_ANVIL_LAND);
        } else {
            if (playerDataManager.hasPlayerData(player)) {
                playerDataManager.getPlayerData(player).setViewer(new Upgrade.Viewer(Upgrade.Viewer.Type.UPGRADES, null));
                Upgrade.getInstance().open(player);
                soundManager.playSound(player, XSound.BLOCK_CHEST_OPEN);
            }
        }
    }

    @Override
    public void onCommandByConsole(ConsoleCommandSender sender, String[] args) {
        sender.sendMessage("SkyBlock | Error: You must be a player to perform that command.");
    }

    @Override
    public String getName() {
        return "upgrade";
    }

    @Override
    public String getInfoMessagePath() {
        return "Command.Island.Admin.Upgrade.Info.Message";
    }

    @Override
    public String[] getAliases() {
        return new String[0];
    }

    @Override
    public String[] getArguments() {
        return new String[0];
    }
}
