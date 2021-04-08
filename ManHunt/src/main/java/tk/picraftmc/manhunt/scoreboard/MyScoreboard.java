package tk.picraftmc.manhunt.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class MyScoreboard {
    public static Scoreboard scoreboard;

    public static int setScoreboard() {
        ScoreboardManager manager = Bukkit.getScoreboardManager();

        if (manager != null) {
            Scoreboard board = manager.getNewScoreboard();

            Team hunter = board.registerNewTeam(ChatColor.GOLD + "HUNTER");
            Team hunted = board.registerNewTeam(ChatColor.GOLD + "HUNTED");

            hunter.setPrefix(ChatColor.GRAY + "[" + ChatColor.GOLD + "HUNTER" + ChatColor.GRAY + "]");
            hunted.setPrefix(ChatColor.GRAY + "[" + ChatColor.GOLD + "HUNTED" + ChatColor.GRAY + "]");

            hunter.setDisplayName(ChatColor.GOLD + "HUNTER");
            hunted.setDisplayName(ChatColor.GOLD + "HUNTED");

            hunter.setAllowFriendlyFire(false);
            hunted.setAllowFriendlyFire(false);

            Player[] playersList = new Player[Bukkit.getServer().getOnlinePlayers().size()];
            Bukkit.getServer().getOnlinePlayers().toArray(playersList);
            List<Player> players = Arrays.asList(playersList);

            int number = 0;
            int rand = 0;
            if (players.contains(Bukkit.getPlayer("Gleich_Oben"))) {
                hunted.addEntry(Objects.requireNonNull(Bukkit.getPlayer("Gleich_Oben")).getDisplayName());
            }
            if (Bukkit.getOnlinePlayers().size() >= 6) {
                number = 1;
                Random random = new Random();
                rand = random.nextInt(players.size());
                while (players.get(rand) == Bukkit.getPlayer("Gleich_Oben")) {
                    rand = random.nextInt(players.size());
                }
                hunted.addEntry(players.get(rand).getDisplayName());
                for (Player player : players) {
                    if (player != Bukkit.getPlayer("Gleich_Oben") && player != players.get(rand)) {
                        hunter.addEntry(player.getDisplayName());
                    }
                }
            } else {
                for (Player player : players) {
                    if (player != Bukkit.getPlayer("Gleich_Oben")) {
                        hunter.addEntry(player.getDisplayName());
                    }
                }
            }

            Objective objective = board.registerNewObjective("showHealth", "health", ChatColor.BLUE + "" + ChatColor.BOLD + "PiCraftMc.tk");
            objective.setDisplayName(ChatColor.RED + "/ 20");
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);
            Objective dummies = board.registerNewObjective("dummies", "dummy", ChatColor.RED + "" + ChatColor.BOLD + "PiCraftMc.tk");
            dummies.setDisplayName(ChatColor.BLUE + "");
            dummies.setDisplaySlot(DisplaySlot.SIDEBAR);

            Score hunterScore = dummies.getScore(ChatColor.GREEN + "» Hunter: ");
            Score huntedScore = dummies.getScore(ChatColor.GREEN + "» Hunted: ");

            hunterScore.setScore(players.size() + 1);
            huntedScore.setScore(players.size() + number + 2);

            int i = 1;
            boolean b = false;
            for (Player player : players) {
                Score score = objective.getScore(player.getDisplayName());
                if (Bukkit.getOnlinePlayers().size() >= 6) {
                    if (player != Bukkit.getPlayer("Gleich_Oben") && player != players.get(rand)) {
                        score.setScore(i);
                    } else {
                        if (b) {
                            score.setScore(players.size() + number + 1);
                        } else {
                            score.setScore(players.size() + number);
                            b = true;
                        }
                    }
                } else {
                    if (player != Bukkit.getPlayer("Gleich_Oben")) {
                        score.setScore(i);
                    } else {
                        score.setScore(players.size() + number + 1);
                    }
                }
                i++;
            }

            for (Player player : players) {
                player.setScoreboard(board);
            }

            scoreboard = board;

            return rand;
        } else {
            return -1;
        }
    }
}
