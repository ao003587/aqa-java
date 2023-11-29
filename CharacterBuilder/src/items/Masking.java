package items;

public abstract class Masking implements Item {

    protected abstract float getChance();

    @Override
    public String Apply() {
        return String.format("Fraud chance %f", getChance());
    }
}
