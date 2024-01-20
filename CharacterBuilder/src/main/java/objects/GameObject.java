package objects;

public abstract class GameObject {

    private final String name;

    public GameObject(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
