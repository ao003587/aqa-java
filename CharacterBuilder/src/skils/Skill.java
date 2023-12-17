package skils;

import enums.ImpactType;
import enums.SkillType;

import java.util.Arrays;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public abstract class Skill {
    private final SkillType type;
    private final float value;
    private final String name;
    private final int maximumTargets;

    protected ImpactType impactType;

    public Skill(SkillType type, float value, String name, int maximumTargets) {
        this.type = type;
        this.value = value;
        this.name = name;
        this.maximumTargets = maximumTargets;
    }

    public String ApplyOn(Target[] targets, float modifier) {
        if (targets.length == 0) {
            return "No targets";
        }
        if (targets.length > maximumTargets) {
            return "Too many targets";
        }
        return Arrays.stream(targets)
                .map(target -> applyOnTarget(modifier, target))
                .collect(Collectors.joining(" and "));
    }

    private String applyOnTarget(float modifier, Target target) {
        StringBuilder builder = new StringBuilder();
        var result = target.damage(getImpactType(), getValue() + modifier);
        builder.append(getName())
                .append(" on ")
                .append(target.getName())
                .append(" for ")
                .append(result)
                .append( type == SkillType.HEAL ? " heal " : " damage ");
        return builder.toString();
    }

    public String getName() {
        return name;
    }

    public ImpactType getImpactType() {
        return impactType;
    }

    public float getValue() {
        return value;
    }

    public SkillType GetType() {
        return type;
    }

    public int getMaximumTargets() {
        return maximumTargets;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", "(", ")")
                .add("Name: " + getName())
                .add("Damage Type: " + getImpactType())
                .add("Value: " + getValue())
                .add("Maximum Targets: " + getMaximumTargets())
                .toString();
    }
}
