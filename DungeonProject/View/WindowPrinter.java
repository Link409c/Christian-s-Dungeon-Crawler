package DungeonProject.View;

import DungeonProject.Model.*;

/**
 * Window Printer displays pseudo game windows to try and give the player a deeper
 * sense of immersion instead of simply looking at lines of text in the terminal window.
 * @author simpso61
 * @version 11/16/22
 */
public class WindowPrinter{
	/**
	 * printBattleWindow formats and prints a display emulating a battle window to make the battle more
	 * immersive for the user. "Health Bars" are 22 characters segmented based on max health
	 * points minus current health. Example:
	 * {}================================================================{}
	 * {}=== C. S I M P S O N  : 189/1000:  [!!!                    ] ==={}
	 * {}================================================================{}
	 * {}================================================================{}
	 * {}=== D R A G O N       : 300/ 500:  [IIIIIIIIIIIIIII        ] ==={}
	 * {}================================================================{}
	 */
	public void printBattleWindow(Player p, Monster m) {
		HealthBarCalculator calc = new HealthBarCalculator();
		double playerDisplayHP = calc.calculateHPBar(p);
		double enemyDisplayHP = calc.calculateHPBar(m);
		char barSegment = 'I';
		StringDisplayFormatter sdf = new StringDisplayFormatter();
		String playerName = sdf.spacedNameFormat(p.getName()),
				enemyName = sdf.spacedNameFormat(m.getName()), playerHPBar, enemyHPBar;
		System.out.println("\n{}==============================================================={}");
		System.out.printf("{}=== %-17S", playerName);
		System.out.printf(" :%4s/%4s:", p.getCurrHealthPoints(), p.getMaxHealthPoints());
		StringBuilder stb = new StringBuilder();
		/* if the player's current HP is less than or equal to twenty percent of max,
		 * print ! instead of I */
		double hpPercent = (double) p.getCurrHealthPoints() / p.getMaxHealthPoints();
		if(hpPercent <= .2){
			barSegment = '!';
		}
		for(int i = 0; i < playerDisplayHP; i++){
			stb.append(barSegment);
		}
		playerHPBar = stb.toString();
		System.out.printf("  [%-22S] ==={}", playerHPBar);
		System.out.println("\n{}==============================================================={}");
		stb.delete(0, stb.length());
		barSegment = 'I';
		System.out.printf("{}=== %-17S", enemyName);
		System.out.printf(" :%4s/%4s:", m.getCurrHealthPoints(), m.getMaxHealthPoints());
		//if monster is Reflection, print % instead of I
		if(m.getMonsterType() == MonsterType.Reflection){
			barSegment = '%';
		}
		for(int i = 0; i < enemyDisplayHP; i++){
			stb.append(barSegment);
		}
		enemyHPBar = stb.toString();
		System.out.printf("  [%-22s] ==={}", enemyHPBar);
		System.out.println("\n{}==============================================================={}");
	}
    
	 /**
     * printPlayerStatus prints the player's status information when selected in the menu. Example:
	  * {}============================={}
	  * {}==       S T A T U S       =={}
	  * {}============================={}
	  * {}===    J. J O L L O F     ==={}
	  * {}=== H P  ::  9999 / 9999  ==={}
	  * {}===  A T K  ::       356  ==={}
	  * {}===  D E F  ::       124  ==={}
	  * {}===  S P D  ::        76  ==={}
	  * {}============================={}
	  * {}===    E Q U I P P E D    ==={}
	  * {}============================={}
	  * {}=== W. S W O R D        X ==={}
	  * {}=== M. H A U B E R K  VII ==={}
	  * {}=== R I N G  O F  D.   IV ==={}
	  * {}============================={}
     */
	 //33 character width
    public void printPlayerStatus(Player p) {
		StringDisplayFormatter sdf = new StringDisplayFormatter();
		int lineWidth = 25;
		String playerName = sdf.spacedNameFormat(p.getName());
		playerName = sdf.centerString(lineWidth, playerName);
		System.out.println("{}============================={}");
		System.out.println("{}==       S T A T U S       =={}");
		System.out.println("{}============================={}");
		System.out.println("{}=="+playerName+"=={}");
		System.out.printf("{}=== H P  ::  %4s / %4s  ==={}", p.getCurrHealthPoints(), p.getMaxHealthPoints());
		String numValue;
		numValue = sdf.valuesFormat(p.getAttackPoints());
		System.out.printf("%n{}===  A T K  ::      %4s  ==={}", numValue);
		numValue = sdf.valuesFormat(p.getDefensePoints());
		System.out.printf("%n{}===  D E F  ::      %4s  ==={}", numValue);
		numValue = sdf.valuesFormat(p.getSpeed());
		System.out.printf("%n{}===  S P D  ::      %4s  ==={}", numValue);
		numValue = sdf.valuesFormat(p.getLevel());
		System.out.printf("%n{}===  L V L  ::      %4s  ==={}", numValue);
		System.out.println("\n{}============================={}");
		System.out.println("{}===    E Q U I P P E D    ==={}");
		System.out.println("{}============================={}");
		Equipment[] theEquipment = p.getEquipment();;
		for (int i = 0; i < 3; i++) {
			if (theEquipment[i] == null) {
				System.out.println("{}===                       ==={}");
			} else {
				String equipment = theEquipment[i].getName();
				String[] equipmentName = equipment.split(" ");
				String rank = equipmentName[equipmentName.length - 1];
				equipmentName[equipmentName.length - 1] = "";
				StringBuilder stb = new StringBuilder();
				for (int j = 0; j < equipmentName.length; j++) {
					if (j == equipmentName.length - 1) {
						stb.append(equipmentName[j]);
					} else {
						stb.append(equipmentName[j]).append(" ");
					}
				}
				equipment = stb.toString();
				equipment = sdf.spacedNameFormat(equipment);
				System.out.printf("{}=== %-17S %3S ==={}%n", equipment, rank);
			}
		}
		System.out.println("{}============================={}");
	}

	/**
	 * printBattleResultWindow prints the rewards screen after a player wins a battle. Example:
	 * {}============================={}
	 * {}==      V I C T O R Y!     =={}
	 * {}============================={}
	 * {}===    D E F E A T E D    ==={}
	 * {}===      G O B L I N      ==={}
	 * {}===                       ==={}
	 * {}===   E X P  ::      999  ==={}
	 * {}============================={}
	 * {}===     R E W A R D S     ==={}
	 * {}============================={}
	 * {}===                       ==={}
	 * {}===   M. H A U B E R K    ==={}
	 * {}===                       ==={}
	 * {}============================={}
	 */
	//33 character width
	public void printBattleResultWindow(Monster m){
		StringDisplayFormatter sdf = new StringDisplayFormatter();
		int lineWidth = 23;
		String monsterName = sdf.spacedNameFormat(m.getName());
		monsterName = sdf.centerString(lineWidth, monsterName);
		String reward = "";
		if(m.hasReward()) {
			reward = sdf.spacedNameFormat(m.getReward().getName());
		}
		reward = sdf.centerString(lineWidth, reward);

		System.out.println("\n{}============================={}");
		System.out.println("{}==      V I C T O R Y!     =={}");
		System.out.println("{}============================={}");
		System.out.println("{}===    D E F E A T E D    ==={}");
		System.out.println("{}==="+ monsterName +"==={}");
		System.out.println("{}===                       ==={}");
		System.out.printf("{}===   E X P  ::     %4s  ==={}%n", m.getExperience());
		System.out.println("{}============================={}");
		System.out.println("{}===     R E W A R D S     ==={}");
		System.out.println("{}============================={}");
		System.out.println("{}===                       ==={}");
		System.out.println("{}==="+ reward +"==={}");
		System.out.println("{}===                       ==={}");
		System.out.println("{}============================={}");
	}

	/**
	 * printItemsHeader prints the header to display the item type being viewed.
	 * Example:
	 * {}============================={}
	 * {}==       U S A B L E       =={}
	 * {}============================={}
	 */
	//33 character width
	public void printItemsHeader(String itemType){
		StringDisplayFormatter sdf = new StringDisplayFormatter();
		int lineWidth = 25;
		String theType = sdf.spacedNameFormat(itemType);
		theType = sdf.centerString(lineWidth, theType);
		System.out.println("\n{}============================={}");
		System.out.println("{}=="+theType+"=={}");
		System.out.println("{}============================={}");
	}

	/**
	 * printDeathWindow prints when the player dies.
	 */
	public void printDeathWindow(){
		System.out.println("\n{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}");
		System.out.println("{{++++++++++++++++++++  Y O U  D I E D . . . +++++++++++++++++++++}}");
		System.out.println("{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}");
	}

	/**
	 * printQuitWindow prints when the player quits the game.
	 */
	public void printQuitWindow(){
		System.out.println("\n[=====================  Y O U  Q U I T . ==========================]");
	}

}
