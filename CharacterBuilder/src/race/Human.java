package race;

import skils.Skill;

public class Human implements Race  {
    public Skill[] GetSkills() {
        return new Skill[0];
    }

    @Override
    public String getName() {
        return "Human";
    }
}
