package profession;

import items.Masking;
import skils.Hide;
import skils.Skill;

public class Thief implements Profession {

    private Masking _disguise;

    public Thief(Masking disguise) {
        _disguise = disguise;
    }

    public Masking getDisguise() {
        return _disguise;
    }

    public void setDisguise(Masking disguise) {
        _disguise = disguise;
    }

    public Skill[] GetSkills() {
        return new Skill[] {
                new Hide()
        };
    }

    public String GetProfessionName() {
        return "Thief";
    }

    public int GetHealthModifier() {
        return 7;
    }

    public int GetStrengthModifier() {
        return 0;
    }

    public int GetAgilityModifier() {
        return 10;
    }

    public int GetIntelligenceModifier() {
        return 2;
    }

    public String UseItem() {
        return _disguise.Apply();
    }
}
