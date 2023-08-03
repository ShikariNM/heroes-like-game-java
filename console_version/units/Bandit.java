package units;

final public class Bandit extends Rogue {

    static private int number = 0;
    static private String defaultName = "Bandit_";

    static private int maxHealthPointsIncrese = -15;
    static private int defenseIncrease = -2;
    static private int speedIncrease = 2;
    static private int attackIncrease = 2;
    static private int damageDispersionIncrease = 0;
    static private int attackRangeIncrese = 0;
    static private int initiativeIncrease = 15;

    static private int maxEnergyPointsIncrease = 0;
    static private int ePGenerationBaseIncrease = 0;

    static private int deathBlowCost = 40;

    public Bandit() {
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
    }


    protected void deathBlow(BaseCharacter target) {
        int damage;
        if (target.healthPoints <= 30) damage = target.healthPoints + target.defense;
        else damage = attack * 2;
        System.out.println(name + " dealt " + (damage - target.defense) +
        " to " + target.name + " with 'death blow'.");
        target.getDamage(damage);
        energyPoints -= deathBlowCost;
    }

    
    @Override
    protected void specificStepActions() {
        if (!alive) return;
        BaseCharacter target = getTheNearestCharacter(true);
        double targetDistance = position.getDistance(target.position);
        if (energyPoints >= deathBlowCost &&
            targetDistance <= attackRange) deathBlow(target);
        else if (targetDistance <= attackRange) commonAttack(target);
        else move(target);
    }
}
