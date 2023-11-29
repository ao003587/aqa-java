package profession;
import items.Mascot;
import skils.FireBall;
import skils.Light;
import skils.Skill;

public class Mage implements Profession {

    private Mascot _mascot;

    public Mage(Mascot mascot) {
        _mascot = mascot;
    }

    public Mascot getMascot() {
        return _mascot;
    }

    public void setMascot(Mascot _mascot) {
        this._mascot = _mascot;
    }

    public Skill[] GetSkills() {
        return new Skill[]{ new FireBall(), new Light() };
    }

    public String GetProfessionName() {
        return "Mage";
    }

    public int GetHealthModifier() {
        return 5;
    }

    public int GetStrengthModifier() {
        return -2;
    }

    public int GetAgilityModifier() {
        return 0;
    }

    public int GetIntelligenceModifier() {
        return 10;
    }

    public String UseItem() {
        return _mascot.Apply();
    }
}
