package com.mygdx.game.main.units;

final public class Peasant extends Warrior {

    static private int number = 0;
    static private String defaultName = "Peasant_";
    
    static private int maxHealthPointsIncrese = 5;
    static private int defenseIncrease = 0;
    static private int speedIncrease = 1;
    static private int attackIncrease = 0;
    static private int damageDispersionIncrease = 0;
    static private int attackRangeIncrese = 0;
    static private int initiativeIncrease = 5;

    static private int maxRagePointsIncrease = 0;
    static private int startRagePointsIncrease = 20;
    static private int rPGenerationBaseIncrease = 5;

    static private int throwStoneCost = 10;
    static private int throwStoneRange = 5;

    protected boolean isFree;


    public Peasant() {
        super(defaultName + number++,
              maxHealthPointsIncrese,
              defenseIncrease, 
              speedIncrease,
              attackIncrease,
              damageDispersionIncrease,
              attackRangeIncrese,
              initiativeIncrease,
              maxRagePointsIncrease,
              startRagePointsIncrease,
              rPGenerationBaseIncrease);

        isFree = true;
    }


    protected void throwStone(BaseCharacter target) {
        int damage = minDamage;
        System.out.println(name + " dealt " + damage +
        " to " + target.name + " with 'throw stone'.");
        target.getDamage(damage);
        ragePoints -= throwStoneCost;
    }


    @Override
    public void step() {
        if (!alive) return;
        isFree = true;
        BaseCharacter target = getTheNearestCharacter(true);
        double targetDistance = position.getDistance(target.position);
        if (ragePoints >= throwStoneCost &&
            !target.name.startsWith("Crossbowman") &&
            targetDistance <= throwStoneRange &&
            targetDistance > attackRange) {
                throwStone(target);
            }
        else if (targetDistance <= attackRange) commonAttack(target);
        else move(target);
    }
}
