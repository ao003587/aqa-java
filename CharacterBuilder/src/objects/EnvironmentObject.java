package objects;

public abstract class EnvironmentObject extends GameObject {
    public int size;
    public EnvironmentObject(String name, int size) {
        super(name);
        this.size = size;
    }
}
