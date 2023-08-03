package units;

final public class Spearman extends Warrior {

    static private int number = 0;
    static private String defaultName = "Spearman_";

    static private int maxHealthPointsIncrese = 10;
    static private int defenseIncrease = 1;
    static private int speedIncrease = 0;
    static private int attackIncrease = 1;
    static private int damageDispersionIncrease = 1;
    static private int attackRangeIncrese = 1;
    static private int initiativeIncrease = -5;
    
    static private int maxRagePointsIncrease = 0;
    static private int startRagePointsIncrease = 0;
    static private int rPGenerationBaseIncrease = 0;

    static private int getFuriousCost = 80;
    static private int getFuriousRange = 5;
    protected boolean gotFurious;

    public Spearman() {
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

        gotFurious = false;
    }


    protected void getFurious(BaseCharacter target) {
        int damage = maxDamage*2;
        System.out.println(name + " dealt " + damage +
        " to " + target.name + " with 'get furious'.");
        target.getDamage(damage);
        ragePoints -= getFuriousCost;
        attackRange = 1;
        initiative = 60;
        attack = 13;
        damageDispersion = 1;
        getMinMaxDamage();
        gotFurious = true;
    }

    
    @Override
    public void step() {
        if (!alive) return;
        BaseCharacter target = getTheNearestCharacter(true);
        double targetDistance = position.getDistance(target.position);
        if (ragePoints >= getFuriousCost &&
            targetDistance <= getFuriousRange &&
            !gotFurious) getFurious(target);
        else if (targetDistance <= attackRange) commonAttack(target);
        else move(target);
    }
}
