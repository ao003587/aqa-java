import enums.DamageType;
import objects.Building;
import objects.builders.CharacterBuilder;
import objects.builders.MonsterBuilder;
import objects.builders.NPCBuilder;
import skils.Attack;
import skils.FireBall;
import skils.Skill;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        var battleMageSkillSet = new ArrayList<Skill>();
        battleMageSkillSet.add(new Attack());
        battleMageSkillSet.add(new FireBall());

        var mage = new CharacterBuilder()
                .setName("Bob")
                .setHealth(20)
                .setAgility(5)
                .setIntelligence(7)
                .setStrength(7)
                .setSkills(battleMageSkillSet)
                .createCharacter();

        var skeletonSkillSet = new ArrayList<Skill>();
        skeletonSkillSet.add(new Attack());

        var skeleton = new MonsterBuilder()
                .setName("Skeleton")
                .setMaxHealthLevel(10)
                .setAttackReaction("Hrrrrr!")
                .setResistance(new DamageType[0])
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
        house.content.add(mage);
        house.content.add(skeleton);
        house.content.add(citizen);

        System.out.printf("%s\n", house);
        System.out.printf("%s %s\n", mage.getName(), mage.useSkillOn(1, skeleton));
        System.out.printf("%s says %s\n", skeleton.getName(), skeleton.OnAttack());
        System.out.printf("%s %s\n", mage.getName(), mage.useSkillOn(0, citizen));
        System.out.printf("%s %s says %s\n", citizen.getName(), citizen.getProfession(), citizen.OnAttack());
    }
}
