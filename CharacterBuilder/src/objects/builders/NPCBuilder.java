package objects.builders;

import objects.NPC;

public class NPCBuilder {
    private String name;
    private float maxHealthLevel;
    private String attackReaction;
    private String profession;
    private int coins;
    private int respectLevel;

    public NPCBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public NPCBuilder setMaxHealthLevel(float maxHealthLevel) {
        this.maxHealthLevel = maxHealthLevel;
        return this;
    }

    public NPCBuilder setAttackReaction(String attackReaction) {
        this.attackReaction = attackReaction;
        return this;
    }

    public NPCBuilder setProfession(String profession) {
        this.profession = profession;
        return this;
    }

    public NPCBuilder setCoins(int coins) {
        this.coins = coins;
        return this;
    }

    public NPCBuilder setRespectLevel(int respectLevel) {
        this.respectLevel = respectLevel;
        return this;
    }

    public NPC createNPC() {
        return new NPC(name, maxHealthLevel, attackReaction, profession, coins, respectLevel);
    }
}