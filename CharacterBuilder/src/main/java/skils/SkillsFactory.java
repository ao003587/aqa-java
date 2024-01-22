package skils;

import enums.ImpactType;
import enums.SkillType;
import skils.battle.*;
import skils.heal.Life;
import skils.heal.Medicine;
import utils.SkillsDataFieldsMap;

public class SkillsFactory {
    public static Skill createSkillFromStringsByImpactType(String[] params, ImpactType impactType) {
        return switch (impactType) {
            case PHYSICAL ->
                    new Physical(Integer.parseInt(params[SkillsDataFieldsMap.id]), SkillType.valueOf(params[SkillsDataFieldsMap.type]), Float.parseFloat(params[SkillsDataFieldsMap.value]), params[SkillsDataFieldsMap.name], Integer.parseInt(params[SkillsDataFieldsMap.maximumTargets]));
            case ICE ->
                    new Ice(Integer.parseInt(params[SkillsDataFieldsMap.id]),SkillType.valueOf(params[SkillsDataFieldsMap.type]), Float.parseFloat(params[SkillsDataFieldsMap.value]), params[SkillsDataFieldsMap.name], Integer.parseInt(params[SkillsDataFieldsMap.maximumTargets]));
            case FIRE ->
                    new Fire(Integer.parseInt(params[SkillsDataFieldsMap.id]),SkillType.valueOf(params[SkillsDataFieldsMap.type]), Float.parseFloat(params[SkillsDataFieldsMap.value]), params[SkillsDataFieldsMap.name], Integer.parseInt(params[SkillsDataFieldsMap.maximumTargets]));
            case ELECTRICITY ->
                    new Electricity(Integer.parseInt(params[SkillsDataFieldsMap.id]),SkillType.valueOf(params[SkillsDataFieldsMap.type]), Float.parseFloat(params[SkillsDataFieldsMap.value]), params[SkillsDataFieldsMap.name], Integer.parseInt(params[SkillsDataFieldsMap.maximumTargets]));
            case POISON ->
                    new Poison(Integer.parseInt(params[SkillsDataFieldsMap.id]),SkillType.valueOf(params[SkillsDataFieldsMap.type]), Float.parseFloat(params[SkillsDataFieldsMap.value]), params[SkillsDataFieldsMap.name], Integer.parseInt(params[SkillsDataFieldsMap.maximumTargets]));
            case LIFE ->
                    new Life(Integer.parseInt(params[SkillsDataFieldsMap.id]),SkillType.valueOf(params[SkillsDataFieldsMap.type]), Float.parseFloat(params[SkillsDataFieldsMap.value]), params[SkillsDataFieldsMap.name], Integer.parseInt(params[SkillsDataFieldsMap.maximumTargets]));
            case MEDICINE ->
                    new Medicine(Integer.parseInt(params[SkillsDataFieldsMap.id]),SkillType.valueOf(params[SkillsDataFieldsMap.type]), Float.parseFloat(params[SkillsDataFieldsMap.value]), params[SkillsDataFieldsMap.name], Integer.parseInt(params[SkillsDataFieldsMap.maximumTargets]));
        };
    }
}
