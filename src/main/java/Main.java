import enums.ImpactType;
import skils.Skill;
import skils.SkillsFactory;
import utils.CsvReader;
import utils.MeasuredOperationOutput;
import utils.SkillsDataFieldsMap;

import java.io.IOException;
import java.util.Arrays;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static final String FILE_PATH = "src/main/resources/skills100.csv";
    private static Logger logger;

    public static void main(String[] args) throws IOException {

        configureLogger();

        logger.log(Level.CONFIG, String.format("Reading data from file: %s", FILE_PATH));

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

        logger.log(Level.CONFIG, String.format("Skills list size: %d", skillsListOperationResult.output().size()));
    }

    private static void configureLogger() {
        var logConfigPath = System.getenv("FILE_NAME");
        System.setProperty("java.util.logging.config.file", logConfigPath);
        logger = Logger.getLogger(Main.class.getName());
    }

    private static Skill toSkill(String paramsString) throws RuntimeException {
        var params = paramsString.replace("\r", "").split(";");
        if (params.length != SkillsDataFieldsMap.fieldsCount)
            throw new RuntimeException("Invalid skill params: " + params.length);
        try {
            var impactType = ImpactType.valueOf(params[SkillsDataFieldsMap.impactType]);
            var logLevel = mapSkillToLogLevel(impactType);
            logger.log(logLevel, "Creating skill from params: " + Arrays.toString(params));
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

    public static Level mapSkillToLogLevel(ImpactType impactType) {
        return switch (impactType) {
            case PHYSICAL -> Level.SEVERE;
            case ICE -> Level.WARNING;
            case FIRE -> Level.INFO;
            case ELECTRICITY -> Level.FINE;
            case POISON -> Level.FINER;
            case LIFE -> Level.FINEST;
            case MEDICINE -> Level.ALL;
        };
    }
}
