package objects;

public abstract class InteractiveObject extends GameObject {

    private final float maxHealthLevel;

    private float health;

    private final String attackReaction;

    public InteractiveObject(String name, float maxHealthLevel, String attackReaction) {
        super(name);
        this.maxHealthLevel = maxHealthLevel;
        this.health = maxHealthLevel;
        this.attackReaction = attackReaction;
    }

    public float getMaxHealthLevel() {
        return maxHealthLevel;
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public String onAttack(){
        return attackReaction;
    }
}
