package items;

public abstract class Mascot implements Item {

    protected abstract String getHelp();

    @Override
    public String Apply() {
        return "Do %s";
    }

}
