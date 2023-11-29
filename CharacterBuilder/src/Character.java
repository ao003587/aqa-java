import profession.Profession;
import race.Race;
import skils.Skill;

public class Character {
    private final String _name;
    private float _health;
    private int _strength;
    private int _agility;
    private int _intelligence;
    private final Race _race;
    private final Profession _profession;
    private Skill[] _skills;

    public Character(String _name, Race _race, Profession _profession) {
        this._name = _name;
        this._race = _race;
        this._profession = _profession;
        InitiateSkills();
        ApplyRaceModifiers();
        ApplyProfessionModifiers();
    }

    public String getName() {
        return _name;
    }

    public float getHealth() {
        return _health;
    }

    public int getStrength() {
        return _strength;
    }

    public int getAgility() {
        return _agility;
    }

    public int getIntelligence() {
        return _intelligence;
    }

    public String getRace() {
        return _race.getName();
    }

    public String getProfession() {
        return _profession.GetProfessionName();
    }

    public Skill[] getSkills() {
        return _skills;
    }
    private void ApplyProfessionModifiers() {
        _health = 10 + _profession.GetHealthModifier();
        _strength = 10 + _profession.GetStrengthModifier();
        _agility = 10 + _profession.GetAgilityModifier();
        _intelligence = 10 + _profession.GetIntelligenceModifier();
    }

    private void ApplyRaceModifiers() {

    }

    private void InitiateSkills() {

        Skill[] profSkills = _profession.GetSkills();
        Skill[] raceSkills = _race.GetSkills();

        _skills = new Skill[profSkills.length + raceSkills.length];
        int currentSkillIndex = 0;
        for (Skill profSkill : profSkills) {
            _skills[currentSkillIndex] = profSkill;
            currentSkillIndex++;
        }

        for (Skill raceSkill : raceSkills) {
            _skills[currentSkillIndex] = raceSkill;
            currentSkillIndex++;
        }
    }

    public String UseItem() {
        return _profession.UseItem();
    }

    public String useSkill(int idx) {
        return _skills[idx].Apply();
    }
}
