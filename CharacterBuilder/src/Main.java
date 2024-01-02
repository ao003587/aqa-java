import enums.ImpactType;
import skils.Skill;
import skils.SkillsFactory;
import utils.CsvReader;
import utils.MeasuredOperation;
import utils.MeasuredOperationOutput;
import utils.SkillsDataFieldsMap;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    private static final String FILE_PATH = "resources/skills100.csv";

    public static void main(String[] args) {

        var readFileOperationOutput = measured(ipt -> {
            try {
                return CsvReader.readFromFile(ipt);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, FILE_PATH, "Read from file");

        var skillsListOperationResult =  measured(input -> Arrays
                .stream(input)
                .map(Main::toSkill)
                .toList(), readFileOperationOutput.output(), "Create skills list");

        var streamProcessingOperationResult = measured(Main::streamProcessing, skillsListOperationResult.output(), "Stream processing");

        Stream.Builder<MeasuredOperation> measurementStream = Stream.builder();
        measurementStream
                .add(readFileOperationOutput)
                .add(skillsListOperationResult)
                .add(streamProcessingOperationResult)
                .build()
                .sorted((leftOperation, rightOperation) -> (int)(leftOperation.time() - rightOperation.time()))
                .forEach(operation -> System.out.printf("%s: %d ms\n", operation.name(), operation.time()));

        var dateFrom = new Date(2023, Calendar.APRIL, 6, 10, 23);
        var dateTo = new Date(2024, Calendar.AUGUST, 12, 5, 23);
        var dateDiff = dateTo.getTime() - dateFrom.getTime();
        var dateDiffInDays = dateDiff / (1000 * 60 * 60 * 24);
        System.out.printf("%d days between %s and %s", dateDiffInDays, dateFrom, dateTo);
    }

    private static List<Skill> streamProcessing(List<Skill> list) {
        int sortSkip = 14;
        var sortOut = 25;
        var filterOut = 13;

        System.out.println("1:");
        list.stream()
                .sorted((left, right) -> right.getId() - left.getId())
                .skip(sortSkip)
                .limit(sortOut)
                .forEach(System.out::println);

        System.out.println("2:");
        list.stream()
                .filter(skill -> skill.getId() % 2 == 0)
                .limit(filterOut)
                .forEach(System.out::println);

        System.out.println("3:");
        list.stream()
                .collect(Collectors.toMap(Skill::getId, skill -> skill))
                .forEach((integer, skill) -> System.out.println(integer + " => " + skill));
        return list;
    }

    private static Skill toSkill(String paramsString) throws RuntimeException {
        var params = paramsString.replace("\r", "").split(";");
        if (params.length != SkillsDataFieldsMap.fieldsCount)
            throw new RuntimeException("Invalid skill params: " + params.length);
        try {
            var impactType = ImpactType.valueOf(params[SkillsDataFieldsMap.impactType]);
            return SkillsFactory.createSkillFromStringsByImpactType(params, impactType);
        } catch (Exception e) {
            throw new RuntimeException("Invalid skill params: " + Arrays.toString(params), e);
        }
    }

    private static <TI, TO> MeasuredOperationOutput<TO> measured(Function<TI, TO> function, TI input, String operationName) {
        var operationStart = System.currentTimeMillis();
        var output = function.apply(input);
        var operationEnd = System.currentTimeMillis();
        return new MeasuredOperationOutput<>(operationName, operationEnd - operationStart, output) ;
    }
}
