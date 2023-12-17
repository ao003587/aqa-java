package objects;

import skils.Skill;
import skils.Target;
import enums.ImpactType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class Monster extends InteractiveObject implements Target {

    public ImpactType[] resistance;

    private final ArrayList<Skill> skills;

    private final int experience;
    
    public Monster(String name, float maxHealthLevel, String attackReaction, ImpactType[] resistance, ArrayList<Skill> skills, int experience) {
        super(name, maxHealthLevel, attackReaction);
        this.resistance = resistance;
        this.skills = skills;
        this.experience = experience;
    }

    public ImpactType[] getResistance() {
        return resistance;
    }

    public ArrayList<Skill> getSkills() {
        return skills;
    }

    public int getExperience() {
        return experience;
    }

    @Override
    public float damage(ImpactType type, float value) {
        var health = getHealth();
        if(type == ImpactType.PHYSICAL)
            value *= 0.5f;
        if (health == 0)
            return value;
        setHealth(health - value);
        return value;
    }

    @Override
    public float heal(ImpactType type, float value) {
        var health = getHealth();
        if (health == getMaxHealthLevel())
            return value;
        setHealth(health + value);
        return value;
    }

    @Override
    public String toString() {
        return "Monster{ " +
                "name=" + getName() +
                ", resistance=" + Arrays.toString(resistance) +
                ", skills=" + skills.stream().map(Objects::toString).collect(Collectors.joining(",", "[", "]")) +
                ", experience=" + experience +
                " }";
    }
}
