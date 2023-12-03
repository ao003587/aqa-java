package objects;

import java.util.ArrayList;

public class Building extends EnvironmentObject {

    public ArrayList<InteractiveObject> content;

    public Building(int size, String name) {
        super(name, size);
        this.content = new ArrayList<>();
    }

    public int getSize() {
        return size;
    }

    public ArrayList<InteractiveObject> getContent() {
        return content;
    }
}
