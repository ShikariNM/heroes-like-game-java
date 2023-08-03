package com.mygdx.game.main.units;

final class Archer extends Rogue {

    static private int number = 0;
    static private String defaultName = "Archer_";

    static private int maxHealthPointsIncrese = -10;
    static private int defenseIncrease = -2;
    static private int speedIncrease = 0;
    static private int attackIncrease = 0;
    static private int damageDispersionIncrease = 2;
    static private int attackRangeIncrese = 6;
    static private int initiativeIncrease = 10;

    static private int maxEnergyPointsIncrease = 0;
    static private int ePGenerationBaseIncrease = 0;

    static private int accurateShotCost = 30;
    static private String[] archerTargetPriority = {"Monk", "Bandit", "Crossbowman", "Spearman"};
    protected int arrows; static private int startArrows = 5;


    public Archer() {
        super(defaultName + number++,
            maxHealthPointsIncrese,
            defenseIncrease, 
            speedIncrease,
            attackIncrease,
            damageDispersionIncrease,
            attackRangeIncrese,
            initiativeIncrease,
            maxEnergyPointsIncrease,
            ePGenerationBaseIncrease);

        arrows = startArrows;
    }


    protected void accurateShot(BaseCharacter target) {
        // достает любую цель, без ограничения дальности
        int damage = maxDamage * 2;
        System.out.println(name + " dealt " + (damage - target.defense) +
        " damage to " + target.name + " with 'accurate shot'."); 
        target.getDamage(damage);
        energyPoints -= accurateShotCost;       
    }


    private BaseCharacter getArcherTargetPriority() {
        BaseCharacter nearestRival = getTheNearestCharacter(true);
        if(position.getDistance(nearestRival.position) <= 4){
            return nearestRival;
        }
        else {
            for (String rivalType : archerTargetPriority) {
                for (BaseCharacter rival : rivals) {
                    if (rival.alive && rival.name.startsWith(rivalType)) return rival;
                }
            }
            return null; // логически недостижимо, написал, чтобы Java не ругалась
        }
    }


    @Override
    protected void specificStepActions() {
        if (!alive) return;
        if (arrows > 0) {
            BaseCharacter target = getArcherTargetPriority();
            if (energyPoints >= accurateShotCost) accurateShot(target);
            else if (position.getDistance(target.position) <= attackRange) commonAttack(target);
            else move(target);

            boolean peasant = false;
            for (BaseCharacter ally : allies) {
                if (ally.getClass() == Peasant.class && ((Peasant) ally).isFree) {
                    peasant = true;
                    ((Peasant) ally).isFree = false;
                    break;
                }
            }
            if (!peasant) arrows--;

            if (arrows == 0) {
                attackRange = 1;
                initiative = 40;
                attack = 8;
                damageDispersion = 1;
                getMinMaxDamage();
                System.out.println(name + " used all his arrows.");
            }
        }
        else {
            BaseCharacter target = getTheNearestCharacter(true);
            if (position.getDistance(target.position) <= attackRange) commonAttack(target);
            else move(target);
        }
    }

    @Override
    public String getInfo() {
        String s = String.format("%-" + 10 + "s", "arr: " + arrows);
        return super.getInfo() + s;
    }
}