package main;
public class Bow {
    static Player player;
    //Scales damage with player reputation
    static public int baseBowAttack=60;
    static public int bowAttackValue=baseBowAttack*Player.reputation;
}
