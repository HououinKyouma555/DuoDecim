package entity;

import main.GamePanel;

public class FemaleNPC2 extends Entity{
    
    public FemaleNPC2(GamePanel gp){
        super(gp);

        name = "Shawty2";
        maxHealth = 100;
        health = maxHealth;

        direction = "down";
        appears = 0;

        getImage();
        setDialogue();
    }

    public void getImage()
    {
        up1 = setUp("femaleNPC/female");
        down1 = setUp("femaleNPC/female");
        left1 = setUp("femaleNPC/female");
        right1 = setUp("femaleNPC/female");
    }

    public void setDialogue()
    {
        dialogues[0] = "The lions kinda cute";
    }

    public void speak()
    {
        super.speak();
    }

}
