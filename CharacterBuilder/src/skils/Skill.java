package skils;

import enums.DamageType;
import enums.SkillType;

public abstract class Skill {

    public String ApplyOn(Target target, float modifier) {
        var result = target.damage(getDamageType(), getValue() + modifier);
        return getName() + " on " + target.getName() + " for " + result + " damage";
    }

    public abstract String getName();

    public abstract DamageType getDamageType();

    public abstract float getValue();

    public abstract SkillType GetType();
}
