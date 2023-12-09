package objects;

import skils.Skill;
import skils.Target;
import enums.DamageType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class Monster extends InteractiveObject implements Target {

    public DamageType[] resistance;

    private final ArrayList<Skill> skills;

    private final int experience;
    
    public Monster(String name, float maxHealthLevel, String attackReaction, DamageType[] resistance, ArrayList<Skill> skills, int experience) {
        super(name, maxHealthLevel, attackReaction);
        this.resistance = resistance;
        this.skills = skills;
        this.experience = experience;
    }

    public DamageType[] getResistance() {
        return resistance;
    }

    public ArrayList<Skill> getSkills() {
        return skills;
    }

    public int getExperience() {
        return experience;
    }

    @Override
    public float damage(DamageType type, float value) {
        var health = getHealth();
        setHealth(health - value);

        if(type == DamageType.PHYSICAL)
            value *= 0.5f;

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
