package objects.test;

import base.TestBase;
import enums.ImpactType;
import objects.Building;
import objects.builders.MonsterBuilder;
import objects.builders.NPCBuilder;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class BuildingTest extends TestBase {

    @Test(groups = {"building.constructor"})
    public void buildingShouldHaveCorrectName() {
        var house = new Building(3, "House");
        Assert.assertEquals(house.getName(), "House");
    }

    @Test(groups = {"building.constructor"})
    public void buildingShouldHaveCorrectSize() {
        var house = new Building(3, "House");
        Assert.assertEquals(house.getSize(), 3);
    }

    @Test(groups = {"building.content"})
    public void buildingShouldHaveContent() {

        var contentName = "Someone";
        var someone = new NPCBuilder()
                .setName(contentName)
                .createNPC();

        var house = new Building(1, "House");
        house.addObject(someone);

        Assert.assertListContains(house.getContent(),
                interactiveObject -> interactiveObject.getName().equals(contentName),
                contentName);
    }

    @Test(groups = {"building.content"})
    public void buildingShouldThrowsIllegalStateExceptionOnContentExceedingBuildingSize() {
        var npcOne = new NPCBuilder()
                .createNPC();
        var npcTwo = new NPCBuilder()
                .createNPC();
        var house = new Building(1, "House");
        house.addObject(npcOne);
        Assert.expectThrows(IllegalStateException.class, () ->  house.addObject(npcTwo));
    }

    @Test(groups = {"building.output"})
    public void buildingShouldCorrectlyFormatTextPresentation() {
        var expected = """
                Building:\s
                \t- name: House
                \t- size: 1
                \t- content:\s
                \t\tMonster{ name=Skeleton, resistance=[], skills=[], experience=10 }""";

        var npcOne = new MonsterBuilder()
                .setName("Skeleton")
                .setExperience(10)
                .setSkills(new ArrayList<>())
                .setResistance(new ImpactType[0])
                .createMonster();
        var house = new Building(1, "House");
        house.addObject(npcOne);

        Assert.assertEquals(house.toString(), expected);
    }
}
