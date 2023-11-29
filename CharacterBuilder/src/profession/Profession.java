package profession;
import skils.Skill;

public interface Profession {
    Skill[] GetSkills();
    String GetProfessionName();
    int GetHealthModifier();
    int GetStrengthModifier();
    int GetAgilityModifier();
    int GetIntelligenceModifier();
    String UseItem();
}
