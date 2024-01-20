package skills.test;

import base.TestBase;
import enums.SkillType;
import objects.builders.MonsterBuilder;
import org.testng.Assert;
import org.testng.annotations.*;
import skils.Target;
import skils.battle.Fire;

public class SkillTest extends TestBase {

    @Test(groups = {"skill.apply"})
    public void skillShouldReturnCorrectTextOnApplicationToZeroTargets() {

        var skill = new Fire(1, SkillType.MAGIC, 10, "Fire wall", 10);
        var result = skill.ApplyOn(new Target[0], 0);
        Assert.assertEquals(result, "No targets");
    }

    @Test(groups = {"skill.apply"})
    public void skillShouldReturnCorrectTextOnApplicationToTooManyTargets() {

        var monsterOne = new MonsterBuilder().createMonster();
        var monsterTwo = new MonsterBuilder().createMonster();
        var skill = new Fire(1, SkillType.MAGIC, 10, "Fire wall", 1);
        var result = skill.ApplyOn(new Target[]{monsterOne, monsterTwo}, 0);
        Assert.assertEquals(result, "Too many targets");
    }

    @Test(groups = {"skill.constructor"})
    public void skillShouldReturnCorrectName() {
        var skill = new Fire(1, SkillType.MAGIC, 10, "Fire wall", 1);
        Assert.assertEquals(skill.getName(), "Fire wall");
    }

    @Test(groups = {"skill.constructor"})
    public void skillShouldReturnCorrectType() {
        var skill = new Fire(1, SkillType.MAGIC, 10, "Fire wall", 1);
        Assert.assertEquals(skill.getType(),  SkillType.MAGIC);
    }

    @Test(groups = {"skill.constructor"})
    public void skillShouldReturnCorrectValue() {
        var skill = new Fire(1, SkillType.MAGIC, 10, "Fire wall", 1);
        Assert.assertEquals(skill.getValue(),  10);
    }
}
