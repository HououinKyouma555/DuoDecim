package entity;

import main.GamePanel;

public class BlueBoy extends Entity{

    public BlueBoy(GamePanel gp) {
        super(gp);
        name = "Stevie";
        maxHealth = 100;
        health = maxHealth;

        direction = "down";
        appears = 1;

        //ATTACK OPTIONS
        attackOption[0] = "Punch that fool";
        attackOption[1] = "Throw Rock";
        attackOption[2] = "Mean insult";
        attackOption[3] = "Quit";

        attackResponse[0] = "bruh";
        attackResponse[1] = "oww";
        attackResponse[2] = "why";
        attackResponse[3] = "Please just leave";

        getImage();
        
    }

    public void getImage(){
        down1 = setUp("player/boy_down_1");
        down2 = setUp("player/boy_down_1");
    }

    public void attackEnemy(int attackSelected){
        switch (attackSelected){
            case (0):
                System.out.println("Attack 0 was chosen");
                health -= 10;
                System.out.println("Health = "+health);
                break;
            case (1):
                System.out.println("Attack 1 was chosen");
                health -= 20;
                System.out.println("Health = "+health);
                break;
            case (2):
                System.out.println("Attack 2 was chosen");
                System.out.println("Health = "+health);
                break;
        }
    }

    public void speak(){
        super.speak();
    }

    public String enemyAttack(){
        
        String attackName = "punch";
        if (gp.ui.enemyHasAttacked == false){
            gp.player.health -= 5;
        }

        return attackName;
        
    }
    
}
