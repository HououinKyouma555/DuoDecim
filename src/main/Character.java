package main;
public abstract class Character {
    public String name;
    public int hp, maxHP;

    public Character(String name, int maxHP, int hp){
        this.name = name;
        this.maxHP = maxHP;
        this.hp = hp;
    }
}
