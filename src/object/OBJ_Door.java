package object;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Door extends SuperObject{

    GamePanel gp;

    public OBJ_Door(GamePanel gp){
        name = "Door";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/door.png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        appears = 0;

        collision = true;
    }
}
