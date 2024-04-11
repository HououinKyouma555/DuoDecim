package entity;

import main.GamePanel;

public class Lion extends Entity {

    public Lion(GamePanel gp){
        super(gp);

        name = "Lion";
        maxHealth = 600;
        health = maxHealth;

        direction = "down";
        appears = 2;

        //ATTACK OPTIONS
        attackOption[0] = "Sword";
        attackOption[1] = "Bow and Arrow";
        attackOption[2] = "Strangle";
        attackOption[3] = "punch";

        attackResponse[0] = "rawr";
        attackResponse[1] = "grrr";
        attackResponse[2] = ";)";
        attackResponse[3] = "bruh";

        getImage();
    }

    public void getImage(){
       
        up1 = setUp("lion/LionDownLeft");
        up2 = setUp("lion/LionDownRight");
        down1 = setUp("lion/LionDownLeft");
        down2 = setUp("lion/LionDownRight");
        left1 = setUp("lion/LionLeft1");
        left2 = setUp("lion/LionLeft2");
        right1 = setUp("lion/LionRight1");
        right2 = setUp("lion/LionRight2");

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
                health -= 200;
                System.out.println("Health = "+health);
                break;
        }
    }

    public void speak(){
        super.speak();
    }

    public String enemyAttack(){
        
        String attackName = "bite";
        if (gp.ui.enemyHasAttacked == false){
            gp.player.health -= 100;
        }

        return attackName;
        
    }
    
}
