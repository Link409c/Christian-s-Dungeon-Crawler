package DungeonProject.Model;

import java.util.Random;

/**
 * A Being object is a player or monster in the dungeon.
 */
public class Being implements BeingInterface {

    /**
     * attack method reduces the target's health points by the attack points of the attacker,
     * reduced by the defense points of the target.
     * @param b The target of the attack.
     */
    @Override
    public int attack(Being b) {
        //variable for damage calculation
        int inflicted = 0;
        //check if target exists
        if(b != null){
            //check if target is dead
            if(b.isAlive()){
                //check if object is player or monster
                //prints a different message to show action
                //calculate damage and reduce target hp accordingly
                Random r = new Random();
                int crit = r.nextInt(0, 21);
                //critical hit chance
                if(crit == 20){
                    if (this instanceof Player) {
                        System.out.println("You attack " + b.getName() + ".");
                        inflicted = Math.max((int) (getAttackPoints()*1.5 - b.getDefensePoints()), 0);
                        System.out.println("Critical hit! You deal " + inflicted + " damage.");
                    } else if (this instanceof Monster) {
                        System.out.println(getName() + " attacks! Critical hit!");
                        inflicted = Math.max((int) (getAttackPoints()*1.5 - b.getDefensePoints()), 0);
                        System.out.println("You take " + inflicted + " damage.");
                    }
                }else {
                    if (this instanceof Player) {
                        System.out.println("You attack " + b.getName() + ".");
                        inflicted = Math.max((getAttackPoints() - b.getDefensePoints()), 0);
                        System.out.println("You deal " + inflicted + " damage.");
                    } else if (this instanceof Monster) {
                        System.out.println(getName() + " attacks!");
                        inflicted = Math.max((getAttackPoints() - b.getDefensePoints()), 0);
                        System.out.println("You take " + inflicted + " damage.");
                    }
                }
            }
        }
        return inflicted;
    }

    /**
     * defend method increases the HP of the being by a small amount and
     * determines which calculation to apply in the attack method.
     */
    @Override
    public void defend() {
        //variables for defense calculation and HP regeneration
        int newDefense;
        int heal;
        //check if defender is player or monster
        //prints a different message to show action
        //calculate new defense, HP regeneration and assign values accordingly
        if(getClass().equals(Player.class)) {
            System.out.println("Defending.");
            heal = (int) (getCurrHealthPoints() * .05);
            setCurrHealthPoints(getCurrHealthPoints() + heal);
            System.out.println("You gain " + heal + "HP.");
            newDefense = getDefensePoints() * 2;
            setDefensePoints(newDefense);
        }else if(getClass().equals(Monster.class)){
            System.out.println(getName() + " is defending.");
            heal = (int) (getCurrHealthPoints() * .02);
            setCurrHealthPoints(getCurrHealthPoints() + heal);
            System.out.println(getName() + " gains " + heal + "HP.");
            newDefense = getDefensePoints() * 2;
            setDefensePoints(newDefense);
        }
    }

    /**
     * isDead method checks whether the being is dead.
     * @return Returns true if being HP is zero. False if not.
     */
    @Override
    public boolean isAlive() {
        //check current HP of the being and return result
        return getCurrHealthPoints() > 0;
    }

    private String name;
    private int maxHealthPoints, currentHealthPoints, attackPoints, defensePoints, speed, level;

    public Being() {
    }

    public Being(String name, int maxHealthPoints, int attackPoints, int defensePoints, int speed, int level) {
        this.name = name;
        this.maxHealthPoints = maxHealthPoints;
        this.currentHealthPoints = maxHealthPoints;
        this.attackPoints = attackPoints;
        this.defensePoints = defensePoints;
        this.speed = speed;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxHealthPoints() {
        return maxHealthPoints;
    }

    public void setMaxHealthPoints(int maxHealthPoints) {
        this.maxHealthPoints = maxHealthPoints;
    }

    public int getCurrHealthPoints() {
        return currentHealthPoints;
    }

    public void setCurrHealthPoints(int currentHealthPoints) {
        this.currentHealthPoints = Math.min(currentHealthPoints, getMaxHealthPoints());
    }

    public int getAttackPoints() {
        return attackPoints;
    }

    public void setAttackPoints(int attackPoints) {
        this.attackPoints = attackPoints;
    }

    public int getDefensePoints() {
        return defensePoints;
    }

    public void setDefensePoints(int defensePoints) {
        this.defensePoints = defensePoints;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
