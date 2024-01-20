package skils.battle;
import enums.ImpactType;
import enums.SkillType;
import skils.Skill;

public class Physical extends Skill {

        public Physical(int id, SkillType type, float value, String name,int maximumTargets) {
            super(id, type, value, name, maximumTargets);
            impactType = ImpactType.PHYSICAL;
        }
}
