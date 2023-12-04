package objects.builders;

import objects.Character;
import skils.Skill;

import java.util.ArrayList;

public class CharacterBuilder {
    private float health;
    private String name;
    private int strength;
    private int agility;
    private int intelligence;
    private ArrayList<Skill> skills;

    public CharacterBuilder setHealth(float health) {
        this.health = health;
        return this;
    }

    public CharacterBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public CharacterBuilder setStrength(int strength) {
        this.strength = strength;
        return this;
    }

    public CharacterBuilder setAgility(int agility) {
        this.agility = agility;
        return this;
    }

    public CharacterBuilder setIntelligence(int intelligence) {
        this.intelligence = intelligence;
        return this;
    }

    public CharacterBuilder setSkills(ArrayList<Skill> skills) {
        this.skills = skills;
        return this;
    }

    public Character createCharacter() {
        return new Character(health, name, strength, agility, intelligence, skills);
    }
}