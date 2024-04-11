package entity;

import main.GamePanel;

public class MaleNPC2 extends Entity{
    
    public MaleNPC2(GamePanel gp){
        super(gp);

        name = "fatty2";
        maxHealth = 100;
        health = maxHealth;

        direction = "down";
        appears = 0;

        getImage();
    }

    public void getImage(){
        down1 = setUp("maleNPC/male");
        up1 = setUp("maleNPC/male");
        right1 = setUp("maleNPC/male");
        left1 = setUp("maleNPC/male");
    }

    public void setDialogue()
    {
        dialogues[0] = "what's up brother!";
    }

    public void speak()
    {
        super.speak();
    }


}
