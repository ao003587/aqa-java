package objects;

import skils.Skill;
import skils.Target;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Character extends InteractiveObject {
    public static final float SKILL_MODIFICATION_MULTIPLICATOR = 0.1f;
    private final int strength;
    private final int agility;
    private final int intelligence;
    private final ArrayList<Skill> skills;

    public Character(float health, String name, int strength, int agility, int intelligence, ArrayList<Skill> skills) {
        super(name, health, "I'm under attack!");
        this.strength = strength;
        this.agility = agility;
        this.intelligence = intelligence;
        this.skills = skills;
    }

    public int getStrength() {
        return strength;
    }

    public int getAgility() {
        return agility;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public ArrayList<Skill> getSkills() {
        return skills;
    }

    public String useSkillOn(int idx, Target[] targets) {
        if (skills.isEmpty())
            return "No skills to use";
        var skill = skills.get(idx);
        if(skill == null)
            return "No skill at index " + idx;
        return "used " + skill.ApplyOn(targets, getModifier(skill));
    }

    private float getModifier(Skill skill) {
        return switch (skill.GetType()) {
            case MAGIC, HEAL -> getIntelligence();
            case INTERACTION -> getStrength() + getAgility();
        } * SKILL_MODIFICATION_MULTIPLICATOR;
    }

    @Override
    public String toString() {
        return new StringBuffer("Character{ ")
                .append("name=").append(getName())
                .append(", strength=").append(strength)
                .append(", agility=").append(agility)
                .append(", intelligence=").append(intelligence)
                .append(", skills=").append(skills.stream().map(Skill::toString).collect(Collectors.joining(", ", "[", "]")))
                .append(" }")
                .toString();
    }
}
