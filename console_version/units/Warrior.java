package units;

abstract class Warrior extends BaseCharacter {
    // Для применения скиллов использует ярость, которая генерируется при получении урона и атаке.

    protected int maxRagePoints;    static private int defaultMaxRagePoints = 100;
    protected int ragePoints;       static private int defaultStartRagePoints = 0;
    protected int rPgenerationBase; static private int defaultRPGenerationBase = 10;

    static private int maxHealthPointsIncrese = 10;
    static private int defenseIncrease = 1;
    static private int speedIncrease = 0;
    static private int attackIncrease = 1;
    static private int attackRangeIncrese = 0;
    static private int initiativeIncrease = 0;


    protected Warrior(String defaultName, int maxHealthPointsIncrese, int defenseIncrease,
                      int speedIncrease, int attackIncrease, int damageDispersionIncrease, 
                      int attackRangeIncrese, int initiativeIncrease, int maxRagePointsIncrease,
                      int startRagePointsIncrease, int rPGenerationBaseIncrease) {
        
        super(defaultName,
              Warrior.maxHealthPointsIncrese + maxHealthPointsIncrese,
              Warrior.defenseIncrease + defenseIncrease,
              Warrior.speedIncrease + speedIncrease,
              Warrior.attackIncrease + attackIncrease,
              damageDispersionIncrease,
              Warrior.attackRangeIncrese + attackRangeIncrese,
              Warrior.initiativeIncrease + initiativeIncrease);

        maxRagePoints = defaultMaxRagePoints + maxRagePointsIncrease;
        ragePoints = defaultStartRagePoints + startRagePointsIncrease;
        rPgenerationBase = defaultRPGenerationBase + rPGenerationBaseIncrease;
    }


    @Override
    protected void commonAttack(BaseCharacter target) {
        super.commonAttack(target);
        if (ragePoints < maxRagePoints) ragePoints += rPgenerationBase;
        if (ragePoints > maxRagePoints) ragePoints = maxRagePoints;
    }


    @Override
    protected int getDamage(int damage) {
        damage = super.getDamage(damage);
        if (ragePoints < maxRagePoints) ragePoints += rPgenerationBase;
        if (ragePoints > maxRagePoints) ragePoints = maxRagePoints;
        return damage;
    }

    @Override
    public String getInfo() {
        String s = String.format("%-" + 9 + "s", "RP: " + ragePoints);
        return super.getInfo() + s;
    }
}
