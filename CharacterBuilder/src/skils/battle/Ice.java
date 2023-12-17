package skils.battle;
import enums.ImpactType;
import enums.SkillType;
import skils.Skill;

public class Ice extends Skill {

        public Ice(SkillType type, float value, String name, int maximumTargets) {
            super(type, value, name, maximumTargets);
            impactType = ImpactType.ICE;
        }
}
