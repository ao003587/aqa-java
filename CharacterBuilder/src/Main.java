import items.Bow;
import profession.Warrior;
import race.WoodElf;

public class Main {
    public static void main(String[] args) {

        Character myCharacter = new Character("Bob", new WoodElf(), new Warrior(new Bow()));

        System.out.printf("%s %s %s", myCharacter.getRace(), myCharacter.getName(), myCharacter.useSkill(0));
        System.out.println();
        System.out.printf(myCharacter.UseItem());
    }
}
