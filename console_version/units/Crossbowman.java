package units;

final public class Crossbowman extends Rogue {

    static private int number = 0;
    static private String defaultName = "Crossbowman_";

    static private int maxHealthPointsIncrese = 0;
    static private int defenseIncrease = 0;
    static private int speedIncrease = 0;
    static private int attackIncrease = 1;
    static private int damageDispersionIncrease = 1;
    static private int attackRangeIncrese = 4;
    static private int initiativeIncrease = 5;

    static private int maxEnergyPointsIncrease = 0;
    static private int ePGenerationBaseIncrease = 0;

    static private int stringOfShotsCost = 30;
    static private int stringOfShotsRange = 3;


    public Crossbowman() {
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


    protected void stringOfShots(BaseCharacter target) {
        int damage = minDamage * 3;
        System.out.println(name + " dealt " + damage +
        " to " + target.name + " with 'string of shots'.");
        target.getDamage(damage);
        energyPoints -= stringOfShotsCost;
    }

    
    @Override
    protected void specificStepActions() {
        if (!alive) return;
        BaseCharacter target = getTheNearestCharacter(true);
        double targetDistance = position.getDistance(target.position);
        if (energyPoints >= stringOfShotsCost &&
            targetDistance <= stringOfShotsRange) stringOfShots(target);
        else if (targetDistance <= attackRange) commonAttack(target);
        else move(target);
    }
}
