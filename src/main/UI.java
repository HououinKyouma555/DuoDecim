package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class UI {
    
    GamePanel gp;
    Font arial_40;
    Font arial_80B;
    Graphics2D g2;
    
    public String message = "";
    public boolean messageOn = false;
    public int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = "";
    int commandNum = 0;
    public int combatChoice = 0;
    int waitTime = 0;
    public int attackSelected;
    public boolean enemyHasAttacked;

    public UI(GamePanel gp){
        this.gp = gp;

        arial_40 = new Font("Arial", Font.BOLD, 40);

        arial_80B = new Font("Arial", Font.BOLD, 80);

    }

    public void showMessage(String text){
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2){
        this.g2 = g2;

        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);
        if (gp.gameState == gp.titleState){
            drawTitleScreen();
        }
        if (gp.gameState == gp.playState){
            // add stuff later
        }
        if (gp.gameState == gp.pauseState){
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80));
            String text = "PAUSED";
            int x = getXForCenteredText(text);
            int y = gp.screenHeight/2;
            g2.drawString(text, x, y);
        }
        if (gp.gameState == gp.dialogueState){
            drawDialogueScreen();
        }
        if (gp.gameState == gp.combatState){
            drawCombatScreen(gp.player.enemyTag);
        }
        if (gp.gameState == gp.overState)
        {
            drawGameOverScreen();
        }
        if (gp.gameState == gp.winState){
            drawVictoryScreen();
        }
    }

    public void drawTitleScreen(){

        g2.setColor(new Color(70, 120, 80));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        // TITLE NAME
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80F));
        String text = "DuoDecim";
        int x = getXForCenteredText(text);
        int y = gp.screenHeight/2 - gp.tileSize * 3;

        g2.setColor(Color.BLACK);
        g2.drawString(text, x-5, y+5);

        g2.setColor(new Color(210, 70, 130));
        g2.drawString(text, x, y);

        x = gp.screenWidth /2 - gp.tileSize * 2;
        y += gp.tileSize * 2 - gp.tileSize;
        g2.drawImage(gp.player.down1, x, y, gp.tileSize * 3, gp.tileSize * 3, null);

        g2.setFont(gp.getFont().deriveFont(Font.PLAIN, 40F));
        g2.setColor(Color.WHITE);

        y+= gp.tileSize * 4;

        text = "NEW GAME";
        x = getXForCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (commandNum == 0){
            g2.drawString(">", x-gp.tileSize, y);
        }

        text = "CONTROLS";
        x = getXForCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (commandNum == 1){
            g2.drawString(">", x-gp.tileSize, y);
        }

        text = "CREDITS";
        x = getXForCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (commandNum == 2){
            g2.drawString(">", x-gp.tileSize, y);
        }
    }

    public void drawDialogueScreen(){
        
        // WINDOW
        int x = gp.tileSize * 2;
        int y = gp.tileSize/2;
        int width = gp.screenWidth - (gp.tileSize*4);
        int height = gp.tileSize*4;

        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
        x += gp.tileSize;
        y += gp.tileSize;
        

        for (String line : currentDialogue.split("\n")){
            g2.drawString(line, x, y);
            y+= 40;
        }
    }

    public void drawCombatScreen(int enemyTag){
        g2.setColor(new Color(70, 120, 80));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        // Enemy Health Bar
        g2.setFont(new Font("ARIAL", Font.PLAIN, 20));
        g2.setColor(Color.WHITE);
        String text = "Enemy HP: ";
        int x = 10;
        int y = 30;
        g2.drawString(text, x, y);

        g2.setFont(new Font("ARIAL", Font.PLAIN, 20));
        g2.setColor(Color.WHITE);
        text = "Your HP: ";
        x = gp.tileSize*7;
        y = 30;
        g2.drawString(text, x, y);

        // ENEMY HEALTH BAR
        g2.setColor(Color.RED);
        g2.fillRect(120, 12, Math.round(200 * (gp.enemy[enemyTag].health / gp.enemy[enemyTag].maxHealth)), 20);
        
        // Player Health Bar
        g2.setColor(Color.GREEN);
        g2.fillRect(gp.tileSize*7 + 90, 12, Math.round(200 * (gp.player.health / gp.player.maxHealth)), 20);
       
        // COMBAT TITLE
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 60F));
        text = "Name: " + gp.enemy[enemyTag].name;
        x = getXForCenteredText(text);
        y = gp.screenHeight/2 - gp.tileSize * 3;

        g2.setColor(Color.BLACK);
        g2.drawString(text, x, y);

        g2.setColor(Color.WHITE);
        g2.drawString(text, x-5, y-5);

        // ENEMY IMAGE
        x = gp.screenWidth/2 - gp.tileSize - gp.tileSize/2;
        y = gp.screenHeight/2 - gp.tileSize*2;
        
        g2.drawImage(gp.enemy[enemyTag].down1, x, y, gp.tileSize * 3, gp.tileSize * 3, null);
        

        // DRAW COMBAT BOX
        if (gp.enemy[enemyTag].health > 0 && gp.player.health > 0){
            g2.setColor(Color.WHITE);
            g2.fillRect(5, gp.tileSize*8, gp.screenWidth-10, gp.tileSize*4 - 10);

            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 40F));
            g2.setColor(Color.BLACK);

            if (gp.turn %2 == 1){
                
                String attack = gp.enemy[enemyTag].enemyAttack();
                enemyHasAttacked = true;
                
                x = gp.tileSize*2;
                y = gp.tileSize * 9;
                g2.drawString(gp.enemy[enemyTag].name +" used "+ attack+ "!", x, y);

                text = gp.enemy[enemyTag].attackResponse[combatChoice];
                x = gp.tileSize * 2;
                y = gp.tileSize * 8 - 24;
                g2.drawString(text, x, y);

                if (gp.keyH.enterPressed == true){
                    gp.turn++;
                }
            }
            else{
                
                enemyHasAttacked = false;
                gp.keyH.enterPressed = false;
                text = gp.enemy[enemyTag].attackOption[0];
                x = gp.tileSize*2;
                y = gp.tileSize * 9;
                g2.drawString(text, x, y);
                if (combatChoice == 0){
                    g2.drawString(">", x-gp.tileSize, y);
                }

                text = gp.enemy[enemyTag].attackOption[1];
                x = gp.tileSize*9;
                y = gp.tileSize * 9;
                g2.drawString(text, x, y);
                if (combatChoice == 1){
                    g2.drawString(">", x-gp.tileSize, y);
                }

                text = gp.enemy[enemyTag].attackOption[2];
                x = gp.tileSize*2;
                y = gp.tileSize * 11;
                g2.drawString(text, x, y);
                if (combatChoice == 2){
                    g2.drawString(">", x-gp.tileSize, y);
                }

                text = gp.enemy[enemyTag].attackOption[3];
                x = gp.tileSize*9;
                y = gp.tileSize * 11;
                g2.drawString(text, x, y);
                if (combatChoice == 3){
                    g2.drawString(">", x-gp.tileSize, y);
                    text = gp.enemy[enemyTag].attackResponse[3];
                    x = gp.tileSize * 2;
                    y = gp.tileSize * 8 - 24;
                    g2.drawString(text, x, y);
                }
            }
        }
        else if (gp.enemy[enemyTag].health <= 0){
            if (enemyTag == 2){
                gp.gameState = gp.winState;
            }
            gp.stopMusic();
            text = "oh.. :(";
            x = gp.tileSize * 2;
            y = gp.tileSize * 8 - 24;
            g2.drawString(text, x, y);
            if (waitTime > 120){
                gp.enemy[enemyTag] = null;
                gp.gameState = gp.playState;
                gp.playMusic(0);
            }
            waitTime++;
            System.out.println(waitTime);
            // gp.enemy[enemyTag] = null;
            // gp.gameState = gp.playState;
        }
        else if (gp.player.health <= 0){
            gp.gameState = gp.overState;
        }
        

    }

    public void drawGameOverScreen()
    {
        g2.setColor(new Color(70, 120, 80));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        // TITLE NAME
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80F));
        String text = "GAME OVER";
        int x = getXForCenteredText(text);
        int y = gp.screenHeight/2 - gp.tileSize * 3;

        g2.setColor(Color.BLACK);
        g2.drawString(text, x-5, y+5);

        g2.setColor(new Color(210, 70, 130));
        g2.drawString(text, x, y);

        x = gp.screenWidth /2 - gp.tileSize * 2;
        y += gp.tileSize * 2 - gp.tileSize;
        g2.drawImage(gp.enemy[3].left1, x, y, gp.tileSize * 3, gp.tileSize * 3, null);

        g2.setFont(gp.getFont().deriveFont(Font.PLAIN, 40F));
        g2.setColor(Color.WHITE);

        y+= gp.tileSize * 4;
    }

    public void drawVictoryScreen(){
        g2.setColor(new Color(70, 120, 80));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
        String text = "CONGRATULATIONS";
        int x = getXForCenteredText(text);
        int y = gp.screenHeight/2 - gp.tileSize * 4;

        g2.setColor(Color.BLACK);
        g2.drawString(text, x-5, y+5);

        g2.setColor(new Color(210, 70, 130));
        g2.drawString(text, x, y);

        text = "You have beaten the first labor";
        x = getXForCenteredText(text);
        y += gp.tileSize * 2;

        g2.setColor(Color.BLACK);
        g2.drawString(text, x, y);

        x = gp.screenWidth /2 - gp.tileSize * 2;
        y += gp.tileSize;
        g2.drawImage(gp.player.down1, x, y, gp.tileSize * 3, gp.tileSize * 3, null);

        g2.setFont(gp.getFont().deriveFont(Font.PLAIN, 40F));
        g2.setColor(Color.WHITE);

        y+= gp.tileSize * 3;

        text = "PLAY AGAIN?";
        x = getXForCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (commandNum == 0){
            g2.drawString(">", x-gp.tileSize, y);
        }

        text = "FOLLOW ON IG";
        x = getXForCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (commandNum == 1){
            g2.drawString(">", x-gp.tileSize, y);
        }

        text = "DONATE";
        x = getXForCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (commandNum == 2){
            g2.drawString(">", x-gp.tileSize, y);
        }
    }

    public void enemyTalk(){
        Font arial_20 = new Font("ARIAL", Font.PLAIN, 40);
        g2.setFont(arial_20);
        g2.setColor(Color.BLACK);
        String text = "oww";
        int x = gp.tileSize * 2;
        int y = gp.tileSize * 10;
        g2.drawString(text, x, y);
    }

    public void drawSubWindow(int x, int y, int width, int height){

        Color c = new Color(0, 0, 0, 210);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
    }

    public int getXForCenteredText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }
}
