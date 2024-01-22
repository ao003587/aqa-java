package game;

import enums.ImpactType;
import objects.Building;
import objects.builders.CharacterBuilder;
import objects.builders.MonsterBuilder;
import objects.builders.NPCBuilder;
import skils.Skill;
import skils.Target;

import java.util.List;
import java.util.Objects;

public class Game {

    private final List<CharacterSkills> skills;

    public Game(List<CharacterSkills> charactersSkills) {
        this.skills = charactersSkills;
    }

    public void BuildSimpleGame() {

        var bobSkillSet = getBasicSkillsByName("Bob");

        var mage = new CharacterBuilder()
                .setName("Bob")
                .setHealth(20)
                .setAgility(5)
                .setIntelligence(7)
                .setStrength(7)
                .setSkills(bobSkillSet)
                .createCharacter();

        var skeletonSkillSet =  getBasicSkillsByName("Skeleton");

        var skeleton = new MonsterBuilder()
                .setName("Skeleton")
                .setMaxHealthLevel(10)
                .setAttackReaction("Hrrrrr!")
                .setResistance(new ImpactType[0])
                .setSkills(skeletonSkillSet)
                .setExperience(10)
                .createMonster();

        var citizen = new NPCBuilder()
                .setName("Citizen")
                .setProfession("Farmer")
                .setMaxHealthLevel(20)
                .setAttackReaction("OH NO!!")
                .setCoins(5)
                .setRespectLevel(50)
                .createNPC();

        var house = new Building(3, "House");
        house.addObject(mage);
        house.addObject(skeleton);
        house.addObject(citizen);

        System.out.printf("%s\n", house);
        System.out.printf("%s %s\n", mage.getName(), mage.useSkillOn(1, new Target[]{ skeleton, citizen }));
        System.out.printf("%s says %s\n", skeleton.getName(), skeleton.onAttack());
        System.out.printf("%s %s\n", mage.getName(), mage.useSkillOn(0, new Target[]{ citizen }));
        System.out.printf("%s %s says %s\n", citizen.getName(), citizen.getProfession(), citizen.onAttack());
    }

    private List<Skill> getBasicSkillsByName(String name) {
        return this.skills.stream().filter(characterSkills -> Objects.equals(characterSkills.name(), name)).flatMap(characterSkills -> characterSkills.skills().stream()).toList();
    }
}
