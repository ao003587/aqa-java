package skils.battle;

import enums.ImpactType;
import enums.SkillType;
import skils.Skill;

public class Poison extends Skill {
    public Poison(SkillType type, float value, String name, int maximumTargets) {
        super(type, value, name, maximumTargets);
        impactType = ImpactType.POISON;
    }
}
