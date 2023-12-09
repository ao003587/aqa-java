package skils;

import enums.DamageType;
import enums.SkillType;

import java.util.StringJoiner;

public abstract class Skill {

    public String ApplyOn(Target target, float modifier) {
        var result = target.damage(getDamageType(), getValue() + modifier);
        return getName() + " on " + target.getName() + " for " + result + " damage";
    }

    public abstract String getName();

    public abstract DamageType getDamageType();

    public abstract float getValue();

    public abstract SkillType GetType();

    @Override
    public String toString() {
        return new StringJoiner(", ", "(", ")")
                .add("Name: " + getName())
                .add("DamageType: " + getDamageType())
                .add("Value: " + getValue())
                .toString();
    }
}
