package objects;

import skils.Target;
import enums.DamageType;

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
    public float damage(DamageType type, float value) {
        var health = getHealth();
        setHealth(health - value);
        return value;
    }
}
