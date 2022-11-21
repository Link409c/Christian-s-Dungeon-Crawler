package DungeonProject.Model;

/**
 * Health Bar Calculator does the necessary math to determine the amount of characters to
 * print in the simulation of a dynamic HP display in the battle window.
 */
public class HealthBarCalculator {

    public double calculateHPBar(Being b){
        //get the being's max hp divided by 22
        //this value is the value of each segment
        double segmentValue = b.getMaxHealthPoints() / 22.0 ;
        //get the being's current hp divided multiplied by segmentValue
        //this is the number of bar segments to display
        if(b.getMaxHealthPoints() != b.getCurrHealthPoints()) {
            return b.getCurrHealthPoints() / segmentValue;
        }
        return 22;
    }
    public HealthBarCalculator(){

    }
}
