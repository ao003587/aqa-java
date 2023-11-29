package profession;

import skils.Attack;
import skils.FireBall;
import skils.Skill;

public class BattleMage implements Profession {

    public Skill[] GetSkills() {
        return new Skill[]{ new Attack(), new FireBall() };
    }

    public String GetProfessionName() {
        return "Battle mage";
    }

    public int GetHealthModifier() {
        return 8;
    }

    public int GetStrengthModifier() {
        return 5;
    }

    public int GetAgilityModifier() {
        return -2;
    }

    public int GetIntelligenceModifier() {
        return 5;
    }

    @Override
    public String UseItem() {
        return "Empty hands";
    }
}
