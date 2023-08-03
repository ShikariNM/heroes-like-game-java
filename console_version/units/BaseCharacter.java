package units;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public abstract class BaseCharacter implements CharInterface, Comparable<BaseCharacter> {

    protected ArrayList<BaseCharacter> allies;
    protected ArrayList<BaseCharacter> rivals;
    protected Boolean leftSide;
    protected Position position;
    protected boolean alive;
    protected String name;
    protected int healthPoints;
    protected int maxHealthPoints;          static private int defaultMaxHealthPoints = 100;
    protected int defense;                  static private int defaultDefense = 5;
    protected int speed;                    static private int defaultSpeed = 1;
    protected int attack;                   static private int defaultAttack = 10;
    protected int damageDispersion;         static private int defaultDamageDispersion = 1;
    protected int minDamage, maxDamage;     
    protected int attackRange;              static private int defaultAttackRange = 1;
    protected int initiative;               static private int defaultInitiative = 50;
    protected boolean gotBuff;

    protected BaseCharacter
    (String name, int maxHealthPointsIncrese, int defenseIncrease, int speedIncrease, int attackIncrease,
    int damageDispersionIncrease, int attackRangeIncrese, int initiativeIncrease) {
        this.name = name;
        alive = true;
        healthPoints = maxHealthPoints =
                            defaultMaxHealthPoints + maxHealthPointsIncrese;
        defense = defaultDefense + defenseIncrease;
        speed = defaultSpeed + speedIncrease;
        attack = defaultAttack + attackIncrease;
        damageDispersion = defaultDamageDispersion + damageDispersionIncrease;
        getMinMaxDamage();
        attackRange = defaultAttackRange + attackRangeIncrese;
        initiative = defaultInitiative + initiativeIncrease;
        gotBuff = false;
    }
    
    protected void addAlliesOrRivals(boolean rivals, Team team) {
        if (rivals) this.rivals = team.team;
        else allies = team.team;
    }

    protected void move(BaseCharacter target) {
        Position startPosition = position;
        int i = 0;
        while (i < speed && position.getDistance(target.position) > attackRange) {
            oneCellMove(target);
            i++;
        }
        if (!startPosition.equals(position)) System.out.println(name + " moved to " + target.name);
        if (position.getDistance(target.position) <= attackRange) commonAttack(target);
    }

    private void oneCellMove(BaseCharacter target) {
        double targetDistance = position.getDistance(target.position);
        ArrayList<Position> options = new ArrayList<>();
        Position xPlus = new Position(position.x + 1, position.y);
        if (xPlus.getDistance(target.position) < targetDistance) options.add(xPlus);
        Position yPlus = new Position(position.x, position.y + 1);
        if (yPlus.getDistance(target.position) < targetDistance) options.add(yPlus);
        Position xMinus = new Position(position.x - 1, position.y);
        if (xMinus.getDistance(target.position) < targetDistance) options.add(xMinus);
        Position yMinus = new Position(position.x, position.y - 1);
        if (yMinus.getDistance(target.position) < targetDistance) options.add(yMinus);

        ArrayList<Position> tempOptions = new ArrayList<>(options);
        for (Position option : tempOptions) {
            for (BaseCharacter ally : allies) {
                if (ally.alive && option.equals(ally.position)) options.remove(option);
            }
        }

        Collections.sort(options, (first, second) -> 
                            ((first.getDistance(target.position) - 
                              second.getDistance(target.position)) > 0 ? 1 : -1));
        if (options.size() > 0) position = options.get(0);
    }

    protected void getMinMaxDamage() {
        minDamage = attack - damageDispersion;
        maxDamage = attack + damageDispersion;
    }

    protected void commonAttack(BaseCharacter target) {
        int damage = new Random().nextInt(minDamage, maxDamage+1);
        System.out.println(name + " dealt " + damage + 
        " damage to " + target.name + " with common attack.");
        damage = target.getDamage(damage);
    }

    protected int getDamage(int damage) {
        damage = damage - defense;
        healthPoints -= damage;
        checkIfDied();
        return damage;
    }

    protected void checkIfDied() {
        if (healthPoints <= 0) {
            alive = false;
            System.out.println(name + " has been killed");
        }
        if (healthPoints < 0) healthPoints = 0;
    }

    protected void getHeal(int regeneration) {
        healthPoints += regeneration;
        if (healthPoints > maxHealthPoints) healthPoints = maxHealthPoints;
    }
    
    protected BaseCharacter getTheNearestCharacter(boolean rival) {
        ArrayList<BaseCharacter> characters;
        if (rival) characters = new ArrayList<>(rivals);
        else characters = new ArrayList<>(allies);
        Collections.sort(characters, (first, second) -> 
                            (first.alive == second.alive) ?
                            ((position.getDistance(first.position) - 
                                position.getDistance(second.position)) > 0 ? 1 : -1) :
                            ((first.alive == true) ? -1 : 1));
        return characters.get(0);
    }

    protected BaseCharacter getMinHPCharacter (boolean rival) {
        ArrayList<BaseCharacter> characters;
        if (rival) characters = new ArrayList<>(rivals);
        else characters = new ArrayList<>(allies);
        Collections.sort(characters, (first, second) -> 
                            (first.alive == second.alive) ?
                            (first.healthPoints - second.healthPoints) :
                            ((first.alive == true) ? -1 : 1));
        return characters.get(0);
    }

    @Override
    public String toString() {
        return String.format("""
                %s
                x: %d, y: %d
                Maximum health points: %d
                Defense: %d
                Speed: %d
                Attack: %d
                Damage: %d - %d
                Attack range: %d
                Initiative: %d
                """, name, position.x, position.y, maxHealthPoints,
                defense, speed, attack, minDamage, maxDamage,
                attackRange, initiative);
    }

    public String getInfo() {
        String s = String.format("%-" + 17 + "s", name);
        s += String.format("%-" + 8 + "s", "\u2665: " + healthPoints);
        s += String.format("%-" + 10 + "s", "\u2694: " + minDamage + "-" + maxDamage);
        s += String.format("%-" + 6 + "s", "\u235F: " + defense);
        return s;
    }

    @Override
    public int compareTo(BaseCharacter o) {
        return Integer.compare(initiative, o.initiative);
    }

    public int[] getCoords() {
        int[] coords = {position.x, position.y};
        return coords;
    }

    public int getHp() {return healthPoints;}
}