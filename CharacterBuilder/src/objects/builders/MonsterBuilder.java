package objects.builders;

import enums.DamageType;
import objects.Monster;
import skils.Skill;

import java.util.ArrayList;

public class MonsterBuilder {
    private String name;
    private float maxHealthLevel;
    private String attackReaction;
    private DamageType[] resistance;
    ArrayList<Skill> skills;
    private int experience;

    public MonsterBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public MonsterBuilder setMaxHealthLevel(float maxHealthLevel) {
        this.maxHealthLevel = maxHealthLevel;
        return this;
    }

    public MonsterBuilder setAttackReaction(String attackReaction) {
        this.attackReaction = attackReaction;
        return this;
    }

    public MonsterBuilder setResistance(DamageType[] resistance) {
        this.resistance = resistance;
        return this;
    }

    public MonsterBuilder setSkills(ArrayList<Skill> skills) {
        this.skills = skills;
        return this;
    }

    public MonsterBuilder setExperience(int experience) {
        this.experience = experience;
        return this;
    }

    public Monster createMonster() {
        return new Monster(name, maxHealthLevel, attackReaction, resistance, skills, experience);
    }
}