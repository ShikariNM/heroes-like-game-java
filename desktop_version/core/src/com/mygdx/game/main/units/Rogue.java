package com.mygdx.game.main.units;

abstract class Rogue extends BaseCharacter {
    // Для применения скиллов использует энергию, которая генерируется с течением времени.
    // Например, каждый ход.

    protected int maxEnergyPoints;  static private int defaultMaxEnergyPoints = 100;
    protected int energyPoints;
    protected int ePgenerationBase; static private int defaultEPGenerationBase = 5;

    static protected int maxHealthPointsIncrese = -10;
    static protected int defenseIncrease = -1;
    static protected int speedIncrease = 0;
    static protected int attackIncrease = 2;
    static protected int attackRangeIncrese = 0;
    static protected int initiativeIncrease = 5;

    
    protected Rogue(String defaultName, int maxHealthPointsIncrese, int defenseIncrease,
                      int speedIncrease, int attackIncrease, int damageDispersionIncrease, 
                      int attackRangeIncrese, int initiativeIncrease, int maxEnergyPointsIncrease,
                      int ePGenerationBaseIncrease) {
        
        super(defaultName,
              Rogue.maxHealthPointsIncrese + maxHealthPointsIncrese,
              Rogue.defenseIncrease + defenseIncrease,
              Rogue.speedIncrease + speedIncrease,
              Rogue.attackIncrease + attackIncrease,
              damageDispersionIncrease,
              Rogue.attackRangeIncrese + attackRangeIncrese,
              Rogue.initiativeIncrease + initiativeIncrease);

        energyPoints = maxEnergyPoints = defaultMaxEnergyPoints + maxEnergyPointsIncrease;
        ePgenerationBase = defaultEPGenerationBase + ePGenerationBaseIncrease;
    }

    abstract void specificStepActions();
    
    public void step() {
        specificStepActions();
        energyPoints += ePgenerationBase;
    }

    @Override
    public String getInfo() {
        String s = String.format("%-" + 9 + "s", "EP: " + energyPoints);
        return super.getInfo() + s;
    }
}
