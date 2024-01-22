package objects;

import skils.Target;
import enums.ImpactType;

public class NPC extends InteractiveObject implements Target {
    private static final int MAX_RESPECT_LEVEL = 100;
    public String profession;
    public int coins;
    public int respectLevel;

    public NPC(String name, float maxHealthLevel, String attackReaction, String profession, int coins, int respectLevel) {
        super(name, maxHealthLevel, attackReaction);
        this.profession = profession;
        this.coins = coins;
        this.respectLevel = respectLevel;
    }

    public String getProfession() {
        return profession;
    }

    public int getCoins() {
        return coins;
    }

    public int getRespectLevel() {
        return respectLevel;
    }

    @Override
    public float damage(ImpactType type, float value) {
        var health = getHealth();
        if (health == 0)
            return value;
        setHealth(health - value);
        respectLevel -= 1;
        return value;
    }

    @Override
    public float heal(ImpactType type, float value) {
        var health = getHealth();
        respectLevel += 1;
        if (health == getMaxHealthLevel())
            return value;
        setHealth(health + value);
        return value;
    }

    @Override
    public String toString() {
        return "NPC{ " +
                "name=" + getName() +
                ", profession='" + profession + '\'' +
                ", coins=" + coins +
                ", respectLevel=" + respectLevel +
                " } " + super.toString();
    }
}
