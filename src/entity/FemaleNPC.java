package entity;

import main.GamePanel;

public class FemaleNPC extends Entity{
    
    public FemaleNPC(GamePanel gp){
        super(gp);

        name = "Shawty";
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
        dialogues[0] = "Ever since that lion appeared,\nI havent been able to leave\nthe village.";
    }

    public void speak()
    {
        super.speak();
    }

}
