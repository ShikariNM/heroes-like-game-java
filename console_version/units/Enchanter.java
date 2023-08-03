package units;

final public class Enchanter extends Caster {
    
    static private int number = 0;
    static private String defaultName = "Enchanter_";

    static private int maxHealthPointsIncrese = 0;
    static private int defenseIncrease = -1;
    static private int speedIncrease = 0;
    static private int attackIncrease = -2;
    static private int damageDispersionIncrease = 0;
    static private int attackRangeIncrese = 14;
    static private int initiativeIncrease = 5;

    static private int maxManaPointsIncrease = 0;

    static private int buffCost = 20;
    static private String[] buffPriority = {"Bandit", "Archer", "Peasant", "Enchanter"};
    protected boolean allBuffed;


    public Enchanter() {
        super(defaultName + number++,
              maxHealthPointsIncrese,
              defenseIncrease, 
              speedIncrease,
              attackIncrease,
              damageDispersionIncrease,
              attackRangeIncrese,
              initiativeIncrease,
              maxManaPointsIncrease);

        allBuffed = false;
    }


    protected BaseCharacter getBuffTarget() {
        for (String allyType : buffPriority) {
            for (BaseCharacter ally : allies) {
                if (ally.alive &&
                    ally.name.startsWith(allyType) &&
                    !ally.gotBuff) return ally;
            }
        }
        return null;
    }


    protected void buff(BaseCharacter target) {
        target.defense += 2;
        target.attack += 2;
        target.getMinMaxDamage();
        target.gotBuff = true;
        manaPoints -= buffCost;
        System.out.println(name + " buffed " + target.name);  
    }

    
    @Override
    public void step() {
        if (!alive) return;
        BaseCharacter buffTarget = getBuffTarget();
        if (buffTarget == null) allBuffed = true;

        if (manaPoints >= buffCost && !allBuffed) buff(buffTarget);
        else {
            BaseCharacter attackTarget = getMinHPCharacter(true);
            commonAttack(attackTarget);
        }
    }
}