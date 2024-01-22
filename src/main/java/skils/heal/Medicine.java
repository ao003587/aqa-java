package skils.heal;

import enums.ImpactType;
import enums.SkillType;
import skils.Skill;

public class Medicine extends Skill {
    public Medicine(int id, SkillType type, float value, String name, int maximumTargets) {
        super(id, type, value, name, maximumTargets);
        impactType = ImpactType.MEDICINE;
    }
}
