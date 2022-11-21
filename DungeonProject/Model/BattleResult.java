package DungeonProject.Model;

import DungeonProject.View.ScriptPrinter;
import DungeonProject.View.WindowPrinter;

/**
 * Battle Result is used after the player wins a battle to update their data.
 */
public class BattleResult {

    public Player battleRewards(Player p, Monster m){
        //increase kill counts, experience, items etc. and check level up
        WindowPrinter w = new WindowPrinter();
        w.printBattleResultWindow(m);
        p.setKillCount(p.getKillCount() + 1);
        p.setExperience(p.getExperience() + m.getExperience());
        p.checkLevelUp(p.getExperience());
        if(m.hasReward()){
            p.getInventory().getItems().add(m.getReward());
            p.setItemsAcquired(p.getItemsAcquired() + 1);
        }
        if(m.getMonsterType() == MonsterType.Reflection){
            ScriptPrinter victory = new ScriptPrinter();
            victory.printVictoryScript();
        }
        return p;
    }

    public BattleResult(){

    }
}