package profession;

import items.Weapon;
import skils.Attack;
import skils.Skill;

public class Warrior implements Profession {

    private Weapon _weapon;

    public Warrior(Weapon weapon) {
        _weapon = weapon;
    }

    public Weapon getWeapon() {
        return _weapon;
    }

    public void setWeapon(Weapon weapon) {
        _weapon = weapon;
    }
    @Override
    public Skill[] GetSkills() {
        return new Skill[] { new Attack() };
    }

    @Override
    public String GetProfessionName() {
        return "Warrior";
    }

    @Override
    public int GetHealthModifier() {
        return 10;
    }

    @Override
    public int GetStrengthModifier() {
        return 10;
    }

    @Override
    public int GetAgilityModifier() {
        return 3;
    }

    @Override
    public int GetIntelligenceModifier() {
        return -5;
    }

    @Override
    public String UseItem() {
        return _weapon.Apply();
    }
}
