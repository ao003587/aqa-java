import enums.ImpactType;
import enums.SkillType;
import objects.Building;
import objects.builders.CharacterBuilder;
import objects.builders.MonsterBuilder;
import objects.builders.NPCBuilder;
import skils.Target;
import skils.battle.Ice;
import skils.battle.Fire;
import skils.battle.Electricity;
import skils.battle.Poison;
import skils.battle.Physical;
import skils.Skill;
import skils.heal.Life;
import skils.heal.Medicine;
import utils.CsvReader;
import utils.SkillsDataFieldsMap;
import java.io.FileReader;
import java.io.IOException;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Arrays;
import java.util.AbstractMap;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        var skills = GetSkills();

        skills.forEach((unit, skillSet) -> {
            System.out.printf("%s:\n", unit);
            skillSet.forEach(skill -> System.out.printf("\t%s\n", skill.toString()));
        });

        var bobSkillSet = new ArrayList<Skill>(skills.get("Bob"));

        var mage = new CharacterBuilder()
                .setName("Bob")
                .setHealth(20)
                .setAgility(5)
                .setIntelligence(7)
                .setStrength(7)
                .setSkills(bobSkillSet)
                .createCharacter();

        var skeletonSkillSet = new ArrayList<Skill>(skills.get("Skeleton"));

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
        house.content.add(mage);
        house.content.add(skeleton);
        house.content.add(citizen);

        System.out.printf("%s\n", house);
        System.out.printf("%s %s\n", mage.getName(), mage.useSkillOn(1, new Target[]{ skeleton, citizen }));
        System.out.printf("%s says %s\n", skeleton.getName(), skeleton.OnAttack());
        System.out.printf("%s %s\n", mage.getName(), mage.useSkillOn(0, new Target[]{ citizen }));
        System.out.printf("%s %s says %s\n", citizen.getName(), citizen.getProfession(), citizen.OnAttack());
    }

    private static Map<String, List<Skill>> GetSkills() {
        try {
            var skillSet = CsvReader.readFromFile("resources/skills.csv");
            return Arrays
                    .stream(skillSet)
                    .map(Main::toSkill)
                    .collect(Collectors.groupingBy(AbstractMap.SimpleEntry::getKey, Collectors.mapping(AbstractMap.SimpleEntry::getValue, Collectors.toList())));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

   private static AbstractMap.SimpleEntry<String, Skill> toSkill(String paramsString) throws RuntimeException {
        var params = paramsString.replace("\r", "").split(";");
        if (params.length != 6)
            throw new RuntimeException("Invalid skill params: " + params);
        try {
            var impactType = ImpactType.valueOf(params[SkillsDataFieldsMap.impactType]);
            var skill = CreateSkill(params, impactType);
            return new AbstractMap.SimpleEntry<>(params[SkillsDataFieldsMap.owner], skill);
        } catch (Exception e) {
            throw new RuntimeException("Invalid skill params: " + params, e);
        }
    }

    private static Skill CreateSkill(String[] params, ImpactType impactType) {
        return switch (impactType) {
            case PHYSICAL ->
                    new Physical(SkillType.valueOf(params[SkillsDataFieldsMap.type]), Float.parseFloat(params[SkillsDataFieldsMap.value]), params[SkillsDataFieldsMap.name], Integer.parseInt(params[SkillsDataFieldsMap.maximumTargets]));
            case ICE ->
                    new Ice(SkillType.valueOf(params[SkillsDataFieldsMap.type]), Float.parseFloat(params[SkillsDataFieldsMap.value]), params[SkillsDataFieldsMap.name], Integer.parseInt(params[SkillsDataFieldsMap.maximumTargets]));
            case FIRE ->
                    new Fire(SkillType.valueOf(params[SkillsDataFieldsMap.type]), Float.parseFloat(params[SkillsDataFieldsMap.value]), params[SkillsDataFieldsMap.name], Integer.parseInt(params[SkillsDataFieldsMap.maximumTargets]));
            case ELECTRICITY ->
                    new Electricity(SkillType.valueOf(params[SkillsDataFieldsMap.type]), Float.parseFloat(params[SkillsDataFieldsMap.value]), params[SkillsDataFieldsMap.name], Integer.parseInt(params[SkillsDataFieldsMap.maximumTargets]));
            case POISON ->
                    new Poison(SkillType.valueOf(params[SkillsDataFieldsMap.type]), Float.parseFloat(params[SkillsDataFieldsMap.value]), params[SkillsDataFieldsMap.name], Integer.parseInt(params[SkillsDataFieldsMap.maximumTargets]));
            case LIFE ->
                    new Life(SkillType.valueOf(params[SkillsDataFieldsMap.type]), Float.parseFloat(params[SkillsDataFieldsMap.value]), params[SkillsDataFieldsMap.name], Integer.parseInt(params[SkillsDataFieldsMap.maximumTargets]));
            case MEDICINE ->
                    new Medicine(SkillType.valueOf(params[SkillsDataFieldsMap.type]), Float.parseFloat(params[SkillsDataFieldsMap.value]), params[SkillsDataFieldsMap.name], Integer.parseInt(params[SkillsDataFieldsMap.maximumTargets]));
        };
    }
}
