import enums.ImpactType;
import skils.Skill;
import skils.SkillsFactory;
import utils.CsvReader;
import utils.SkillsDataFieldsMap;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {

    private static final String FILE_PATH = "resources/skills100.csv";

    public static void main(String[] args) throws IOException {

        var skillSet = CsvReader.readFromFile(FILE_PATH);
        int sortSkip = 14;
        var sortOut = 25;
        var filterOut = 13;

        System.out.println("1:");
        Arrays
                .stream(skillSet)
                .map(Main::toSkill)
                .sorted((left, right) -> right.getId() - left.getId())
                .skip(sortSkip)
                .limit(sortOut)
                .forEach(System.out::println);

        System.out.println("2:");
        Arrays
                .stream(skillSet)
                .map(Main::toSkill)
                .filter(skill -> skill.getId() % 2 == 0)
                .limit(filterOut)
                .forEach(System.out::println);

        System.out.println("3:");
        Arrays
                .stream(skillSet)
                .map(Main::toSkill)
                .collect(Collectors.toMap(Skill::getId, skill -> skill))
                .forEach((integer, skill) -> System.out.println(integer + " => " + skill));
    }

    private static Skill toSkill(String paramsString) throws RuntimeException {
        var params = paramsString.replace("\r", "").split(";");
        if (params.length != SkillsDataFieldsMap.fieldsCount)
            throw new RuntimeException("Invalid skill params: " + params.length);
        try {
            var impactType = ImpactType.valueOf(params[SkillsDataFieldsMap.impactType]);
            return SkillsFactory.createSkillFromStringsByImpactType(params, impactType);
        } catch (Exception e) {
            throw new RuntimeException("Invalid skill params: " + params, e);
        }
    }
}
