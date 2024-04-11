package entity;

import main.GamePanel;

public class Chicken extends Entity{
    
    public Chicken(GamePanel gp){
        super(gp);

        name = "Chic Fil A";
        maxHealth = 100;
        health = maxHealth;

        direction = "right";
        appears = 0;

        getImage();
        setDialogue();
    }

    public void getImage(){
        left1 = setUp("chicken/ChickenLeft");
        left2 = setUp("chicken/ChickenLeft");
        right1 = setUp("chicken/ChickenRight");
        right2 = setUp("chicken/ChickenRight");
        up1 = setUp("chicken/ChickenLeft");
        up2 = setUp("chicken/ChickenLeft");
        down1 = setUp("chicken/ChickenRight");
        down2 = setUp("chicken/ChickenRight");
    }

    public void setDialogue(){

        if (gp.player.reputation == 0)
        {
            dialogues[0] = "bok bok";
        }
        else if (gp.player.reputation == 1)
        {
            dialogues[0] = "frick you bro.";
        }
        else if (gp.player.reputation == 2)
        {
            dialogues[0] = "you kinda cute.";
        }
    }

    public void speak(){
        super.speak();
    }
}
