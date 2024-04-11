package main;
import java.util.Scanner;

public class GameStart {
    static Player player;
    static Enemy enemy;
    static Lion lion;
    static BattleSystem battle = new BattleSystem();
    
    public static void GameStartt(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.println("Hello, " + name + "!");
        //Sets player/enemy name and HP
        player = new Player(name,100,100);
        enemy = new Enemy("Enemy",100,100);
        lion = new Lion("Lion",500,500);
        //Run this command to fight the final boss or a random enemy
        //BattleSystem.Lionbattle();
        //BattleSystem.battle();
        scanner.close();
    }
}