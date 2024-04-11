package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, rightPressed, leftPressed, enterPressed, enemyTalk;
    public boolean running;
    GamePanel gp;

    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        // GAME STATE
        if (gp.gameState == gp.playState){
                // Movement, WASD
            if (code == KeyEvent.VK_W){
                upPressed = true;
            }
            if (code == KeyEvent.VK_A){
                leftPressed = true;
            }
            if (code == KeyEvent.VK_S){
                downPressed = true;
            }
            if (code == KeyEvent.VK_D){
                rightPressed = true;
            }
            if (code == KeyEvent.VK_ENTER){
                enterPressed = true;
            }
            if (code == KeyEvent.VK_P){
                gp.gameState = gp.pauseState; 
            }
            // messing around shit
            if (code == KeyEvent.VK_Q){
                gp.mapState = gp.cave;
                gp.tileM.loadMap("/res/maps/cave.txt"); 
            }
            if (code == KeyEvent.VK_E){
                gp.mapState = gp.Nemea;
                gp.tileM.loadMap("/res/maps/nemea.txt"); 
            }
            if (code == KeyEvent.VK_X){
                gp.player.reputation++;
                System.out.println("Player reputation = " + gp.player.reputation);
            }

            // Run function. Uses Shift
            if (code == KeyEvent.VK_SHIFT){
                if (running == false){
                    running = true;
                }
                else if (running == true){
                    running = false;
                }
            }
        }
        
        // TITLE
        else if (gp.gameState == gp.titleState || gp.gameState == gp.winState){
            if (code == KeyEvent.VK_W){
                gp.playSE(5);
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0){
                    gp.ui.commandNum = 2;
                }
            }
            if (code == KeyEvent.VK_S){
                gp.playSE(5);
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 2){
                    gp.ui.commandNum = 0;
                }
            }
            if (gp.ui.commandNum == 0){
                if (code == KeyEvent.VK_ENTER){
                    if (gp.gameState == gp.winState){
                        gp.resetValues();
                    }
                    
                    gp.playSE(1);
                    gp.gameState = gp.playState;
                    gp.playMusic(0);
                }
            }
        }

        // COMBAT STATE
        else if (gp.gameState == gp.combatState){
            if (code == KeyEvent.VK_A){
                gp.ui.combatChoice--;
                enemyTalk = false;
                if (gp.ui.combatChoice < 0){
                    gp.ui.combatChoice = 3;
                }
            }
            if (code == KeyEvent.VK_D){
                gp.ui.combatChoice++;
                enemyTalk = false;
                if (gp.ui.combatChoice > 3){
                    gp.ui.combatChoice = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER){
                if (gp.turn % 2 == 0){
                    switch(gp.ui.combatChoice){
                        case (0):
                            enemyTalk = true;
                            gp.ui.attackSelected = 0;
                            gp.enemy[gp.player.enemyTag].attackEnemy(gp.ui.attackSelected);
                            break;
                        case (1):
                            enemyTalk = true;
                            gp.ui.attackSelected = 1;
                            gp.enemy[gp.player.enemyTag].attackEnemy(gp.ui.attackSelected);
                            break;
                        case (2):
                            enemyTalk = true;
                            gp.ui.attackSelected = 2;
                            gp.enemy[gp.player.enemyTag].attackEnemy(gp.ui.attackSelected);
                            break;
                        case (3):
                            gp.ui.attackSelected = 3;
                            gp.enemy[gp.player.enemyTag].attackEnemy(gp.ui.attackSelected);
                            gp.stopMusic();
                            gp.playMusic(0);
                            gp.gameState = gp.playState;
                            break;
                    }
                }
                else if (gp.turn % 2 == 1){
                    enterPressed = true;
                }
                gp.turn++;
             }
        }

        // PAUSE STATE
        else if (gp.gameState == gp.pauseState){
            gp.gameState = gp.playState;
        }

        // DIALOGUE STATE
        else if (gp.gameState == gp.dialogueState){
            if (code == KeyEvent.VK_ENTER){
                gp.gameState = gp.playState;
            }
        }

        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W){
            upPressed = false;
        }
        if (code == KeyEvent.VK_A){
            leftPressed = false;
        }
        if (code == KeyEvent.VK_S){
            downPressed = false;
        }
        if (code == KeyEvent.VK_D){
            rightPressed = false;
        }
    }
    
}
