package DungeonProject.Model;

public interface BeingInterface{

    //attack the target b
    int attack(Being b);

    //defend, increasing defense and regain some hp
    void defend();

    //check whether the being is alive or not
    boolean isAlive();

}
