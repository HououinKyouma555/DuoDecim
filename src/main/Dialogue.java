package main;
public class Dialogue {
    //Calls on the player class so I can use any variables from that class
    static Player player;
    static Bow bow;
    static Sword sword;
    public void talk() {
        switch(Player.reputation){
            case 1: 
                System.out.println("What do you want? Leave me alone, I'm working.");
                break;
            case 2:
                System.out.println("Thanks for your hard work!");
                break;
            case 3:
                System.out.println("Thank you again! Take these weapons for your troubles!");
                Bow.baseBowAttack = 100;
                Sword.baseSwordAttack = 50;
                break;
            case 4:
                System.out.println("Thank you again! Now I can return back to my peaceful life!");
                break;
        }
    }
}
