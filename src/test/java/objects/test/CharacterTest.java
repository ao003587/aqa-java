package objects.test;
import base.TestBase;
import enums.SkillType;
import objects.builders.CharacterBuilder;
import org.testng.Assert;
import org.testng.annotations.*;
import skils.Skill;
import skils.Target;
import skils.battle.Fire;

import java.util.ArrayList;

public class CharacterTest extends TestBase {

    @Test(groups = {"character.builder"})
    public void characterBuilderShouldSetupSkills() {

        var fireWall = new Fire(1, SkillType.MAGIC, 10, "Fire wall", 10);
        var skills = new ArrayList<Skill>();
        skills.add(fireWall);

        var mage = new CharacterBuilder()
                .setSkills(skills)
                .createCharacter();

        Assert.assertListContains(mage.getSkills(),
                skill -> skill.getId() == 1 && skill.getName().equals("Fire wall"),
                "Fire wall skill");
    }

    @Test(groups = {"character.builder"})
    public void characterBuilderShouldSetupName() {
        var name = "Jack";
        var mage = new CharacterBuilder()
                .setName(name)
                .createCharacter();

        Assert.assertEquals(mage.getName(), name);
    }

    @Test(groups = {"character.builder"})
    public void characterBuilderShouldSetupHealth() {
        var health = 100;
        var mage = new CharacterBuilder()
                .setHealth(health)
                .createCharacter();

        Assert.assertEquals(mage.getHealth(), health);
    }

    @Test(groups = {"character.builder"})
    public void characterBuilderShouldSetupStrength() {
        var strength = 10;
        var mage = new CharacterBuilder()
                .setStrength(strength)
                .createCharacter();

        Assert.assertEquals(mage.getStrength(), strength);
    }

    @Test(groups = {"character.builder"})
    public void characterBuilderShouldSetupAgility() {
        var agility = 20;
        var mage = new CharacterBuilder()
                .setAgility(agility)
                .createCharacter();

        Assert.assertEquals(mage.getAgility(), agility);
    }

    @Test(groups = {"character.skills"})
    public void characterShouldRespondCorrectlyWhenNoSkillsOnUseSkill() {

        var skills = new ArrayList<Skill>();
        var mage = new CharacterBuilder()
                .setSkills(skills)
                .createCharacter();

        var output = mage.useSkillOn(1, new Target[0]);

        Assert.assertEquals(output,"No skills to use");
    }

    @Test(groups = {"character.skills"})
    public void characterShouldRespondCorrectlyWhenNoSpecificSkillOnUseSkill() {

        var skills = new ArrayList<Skill>();
        skills.add(new Fire(1, SkillType.MAGIC, 10, "Fire wall", 10));
        var mage = new CharacterBuilder()
                .setSkills(skills)
                .createCharacter();

        var output = mage.useSkillOn(1, new Target[0]);

        Assert.assertEquals(output,"No skill at index 1");
    }
}
