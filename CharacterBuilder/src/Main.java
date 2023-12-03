import enums.DamageType;
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
        battleMageSkillSet.add(new Attack());

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

        System.out.printf("%s %s", mage.getName(), mage.useSkillOn(1, skeleton));
        System.out.println();
        System.out.printf("%s says %s", skeleton.getName(), skeleton.OnAttack());
        System.out.println();
        System.out.printf("%s %s", mage.getName(), mage.useSkillOn(0, citizen));
        System.out.println();
        System.out.printf("%s %s says %s", citizen.getName(), citizen.getProfession(), citizen.OnAttack());
    }
}
