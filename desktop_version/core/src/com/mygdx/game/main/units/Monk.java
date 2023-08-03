package com.mygdx.game.main.units;

import java.util.Random;

final public class Monk extends Caster {
    
    static private int number = 0;
    static private String defaultName = "Monk_";

    static private int maxHealthPointsIncrese = 5;
    static private int defenseIncrease = 0;
    static private int speedIncrease = 0;
    static private int attackIncrease = 2;
    static private int damageDispersionIncrease = 0;
    static private int attackRangeIncrese = 0;
    static private int initiativeIncrease = 10;

    static private int maxManaPointsIncrease = 25;

    static private int healCost = 25;


    public Monk() {
        super(defaultName + number++,
              maxHealthPointsIncrese,
              defenseIncrease, 
              speedIncrease,
              attackIncrease,
              damageDispersionIncrease,
              attackRangeIncrese,
              initiativeIncrease,
              maxManaPointsIncrease);
    }


    protected void heal(BaseCharacter target) {
        int heal = 0;
        for (int i = 0; i < 3; i++) {
            heal += new Random().nextInt(minDamage, maxDamage+1);
        }
        target.getHeal(heal);
        manaPoints -= healCost;
        System.out.println(name + " restored " + heal +
                           " health points to " + target.name);  
    }


    @Override
    public void step() {
        if (!alive) return;
        BaseCharacter healTarget = getMinHPCharacter(false);
        BaseCharacter attackTarget = getTheNearestCharacter(true);
        double attackTargetDistance = position.getDistance(attackTarget.position);
        if (manaPoints >= healCost &&
            healTarget.healthPoints <= healTarget.maxHealthPoints - 30) heal(healTarget);
        else if (attackTargetDistance <= attackRange) commonAttack(attackTarget);
        else move(attackTarget);
    }
}
