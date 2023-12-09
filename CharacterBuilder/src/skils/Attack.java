package skils;
import enums.DamageType;
import enums.SkillType;

import java.util.StringJoiner;

public class Attack extends Skill {

    @Override
    public String getName() {
        return "Attack";
    }

    @Override
    public DamageType getDamageType() {
        return DamageType.FIRE;
    }

    @Override
    public float getValue() {
        return 8;
    }

    @Override
    public SkillType GetType() {
        return SkillType.BATTLE;
    }
}
