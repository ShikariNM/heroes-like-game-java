package com.mygdx.game.main.units;

abstract class Caster extends BaseCharacter {
    // Для применения скиллов использует ману, которя генерируется банками маны
    // Или никак.

    protected int maxManaPoints;    static private int defaultMaxManaPoints = 100;
    protected int manaPoints;

    static private int maxHealthPointsIncrese = -20;
    static private int defenseIncrease = -2;
    static private int speedIncrease = 0;
    static private int attackIncrease = 0;
    static private int attackRangeIncrese = 0;
    static private int initiativeIncrease = 10;


    protected Caster(String defaultName, int maxHealthPointsIncrese, int defenseIncrease,
                      int speedIncrease, int attackIncrease, int damageDispersionIncrease, 
                      int attackRangeIncrese, int initiativeIncrease, int maxManaPointsIncrease) {
        
        super(defaultName,
              Caster.maxHealthPointsIncrese + maxHealthPointsIncrese,
              Caster.defenseIncrease + defenseIncrease,
              Caster.speedIncrease + speedIncrease,
              Caster.attackIncrease + attackIncrease,
              damageDispersionIncrease,
              Caster.attackRangeIncrese + attackRangeIncrese,
              Caster.initiativeIncrease + initiativeIncrease);

        manaPoints = maxManaPoints = defaultMaxManaPoints + maxManaPointsIncrease;
    }

    @Override
    public String getInfo() {
        String s = String.format("%-" + 9 + "s", "MP: " + manaPoints);
        return super.getInfo() + s;
    }
}
