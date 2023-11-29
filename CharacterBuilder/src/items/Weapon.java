package items;

public abstract class Weapon implements Item {

    public abstract float getDamage();

    @Override
    public String Apply() {
        return String.format("Damage %f", getDamage());
    }
}
