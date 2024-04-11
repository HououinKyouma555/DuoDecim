package entity;

import main.GamePanel;
import main.Sword;

import java.util.Random;

import main.Bow;






public class Lion extends Entity {
    static Random random = new Random();
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
        attackOption[3] = "Nothing";

        attackResponse[0] = "rawr";
        attackResponse[1] = "grrr";
        attackResponse[2] = ":(";
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
                health -= Sword.swordAttackValue;
                System.out.println("The enemy took " + Sword.swordAttackValue +" damage!");
                break;
            case (1):
            int randomNumber = random.nextInt(101);

            if (randomNumber < 75) {
                System.out.println("The shot landed!");
                health -=Bow.bowAttackValue;
                System.out.println("The enemy took " + Bow.bowAttackValue +" damage!");
            } else {
                System.out.println("The shot missed...");
            }
                break;
            case (2):
                //Instant kill Lion
                health -=100000000;
                break;
        }
    }

    public void speak(){
        super.speak();
    }

    public String enemyAttack(){
        
        String attackName = "bite";
        if (gp.ui.enemyHasAttacked == false){
            gp.player.health -= 200;
        }

        return attackName;
        
    }
    
}
