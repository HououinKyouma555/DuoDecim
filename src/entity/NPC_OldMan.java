package entity;

import java.util.Random;

import main.GamePanel;


public class NPC_OldMan extends Entity{


    public NPC_OldMan(GamePanel gp){
        super(gp);

        direction = "down";
        speed = 1;

        maxHealth = 50;
        health = maxHealth;

        name = "Old Wizard";
        appears = 0;

        attackOption[0] = "Trip";
        attackOption[1] = "Rob Cane";
        attackOption[2] = "Give Bagel";
        attackOption[3] = "Quit";

        attackResponse[0] = "oof";
        attackResponse[1] = "why would you do that";
        attackResponse[2] = "thank you";
        attackResponse[3] = "Please leave";

        getImage();
        setDialogue();

    }

     public void getImage(){

        up1 = setUp("npc/oldman_up_1");
        up2 = setUp("npc/oldman_up_2");
        down1 = setUp("npc/oldman_down_1");
        down2 = setUp("npc/oldman_down_2");
        left1 = setUp("npc/oldman_left_1");
        left2 = setUp("npc/oldman_left_2");
        right1 = setUp("npc/oldman_right_1");
        right2 = setUp("npc/oldman_right_2");

    }

    public void setDialogue(){

        dialogues[0] = "hello, Hercules.";
        dialogues[1] = "Please, my son was killed by\nTHE lion.";
        dialogues[2] = "It escaped into the forest\nbut it will be back for more";
        dialogues[3] = "Please, save Nemea";
    }

    public void setAction(){
        if (actionLockCounter == 120){
            Random random = new Random();
            int i = random.nextInt(4);

            switch (i) {
                case 0:
                    direction = "up";
                    break;
                case 1:
                    direction = "down";
                    break;
                case 2:
                    direction = "right";
                    break;
                case 3:
                    direction = "left";
                    break;
            }
            actionLockCounter = 0;
        }
        actionLockCounter++;
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
                lostCane = true;

                up1 = setUp("npc/oldman_no_cane");
                up2 = setUp("npc/oldman_no_cane");
                down1 = setUp("npc/oldman_no_cane");
                down2 = setUp("npc/oldman_no_cane");
                left1 = setUp("npc/oldman_no_cane");
                left2 = setUp("npc/oldman_no_cane");
                right1 = setUp("npc/oldman_no_cane");
                right2 = setUp("npc/oldman_no_cane");

                speed = 0;
                direction = "down";
                System.out.println("Health = "+health);
                break;
            case (2):
                System.out.println("Attack 2 was chosen");
                health += 5;
                System.out.println("Health = "+health);
                break;
            case (3):
                System.out.println("Quit Battle");
        }
    }

    public String enemyAttack(){
        
        String attackName = "hit with staff";
        if (gp.ui.enemyHasAttacked == false){
            gp.player.health -= 2;
        }

        return attackName;
        
    }
    

    public void speak(){
        super.speak();
    }
}
