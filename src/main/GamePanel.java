package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

import entity.Entity;
import entity.Player;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
    
    // TILE SIZE
    final int originalTileSize = 16;
    final int scale = 3;
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;

    // SCREEN SIZE
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    // WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    //fps
    final int fps = 60;

    // Links to other classes and objects
    public TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    Sound music = new Sound();
    Sound se = new Sound();
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    
    // Player and Objects
    public Player player = new Player(this, keyH);
    public SuperObject[] obj = new SuperObject[10];
    public Entity[] npc = new Entity[10];
    public Entity[] enemy = new Entity[10];
    // combat turn
    public int turn = 0;

    // GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int combatState = 4;
    public final int overState = 5;
    public final int winState = 6;

    // MAP STATE
    public int mapState;
    public final int Nemea = 0;
    public final int npcHouse1 = 1;
    public final int cave = 2;

    // Constructor
    public GamePanel(){

        this.setPreferredSize(new DimensionUIResource(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        
    }

    // Calls AssetSetter's setObject method which places objects on map
    public void setUpGame(){
        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setEnemy();
        // playMusic(0);
        gameState = titleState;
    }

    // Starts gameThread
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/fps;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null){
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;

            lastTime = currentTime;

            if (delta >= 1){
                update();
                repaint();
                delta--;
            }
        }
    }

    // update and paintComponent work in tandem to update the position of objects and
    // draw them in the correct spot. Should contain all objects
    // obj[i]'s' aren't updated because they don't move, only drawn
    // Called 60 times each second (fps)
    public void update(){
        
        if (gameState == playState){
            // player updates
            player.update();

            //NPC updates
            for (int i = 0; i < npc.length; i++){
                if (npc[i] != null){
                    npc[i].update();
                }
            }

            // ENEMY UPDATES
            for (int i = 0; i < enemy.length; i++){
                if (enemy[i] != null){
                    enemy[i].update();
                }
            }

        }
        if (gameState == pauseState){

        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        // TITLE STATE
        if (gameState == titleState){
            ui.draw(g2);
        }
        // COMBAT STATE
        else if (gameState == combatState){
            ui.draw(g2);
        }
        // PLAY STATE
        else{
            tileM.draw(g2);

            for (int i = 0; i < obj.length; i++){
                if (obj[i] != null){
                    if (obj[i].appears == mapState){
                        obj[i].draw(g2, this);
                    }
                }
            }
    
            for (int i = 0; i < npc.length; i++){
                if (npc[i] != null){
                    if (npc[i].appears == mapState){
                        npc[i].draw(g2);
                    }
                }
            }

            for (int i = 0; i < enemy.length; i++){
                if (enemy[i] != null){
                    if (enemy[i].appears == mapState){
                        enemy[i].draw(g2);
                    }
                }
            }
    
            player.draw(g2);
    
            ui.draw(g2);
        }

        
        g2.dispose();
    }

    public void resetValues(){
        player.health = player.maxHealth;
        for (int i = 0; i < enemy.length; i++){
            if (enemy[i] != null){
                enemy[i].health = enemy[i].maxHealth;
            }
        }
        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setEnemy();
        mapState = Nemea;
        tileM.loadMap("/res/maps/nemea.txt");
        player.worldX = tileSize*7;
        player.worldY = tileSize*9;
    }

    public void playMusic(int i){
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic(){
        music.stop();
    }

    public void playSE(int i){
        se.setFile(i);
        se.play();
    }
}
