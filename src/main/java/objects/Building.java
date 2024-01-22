package objects;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Building extends EnvironmentObject {

    private final ArrayList<InteractiveObject> content;

    public Building(int size, String name) {
        super(name, size);
        this.content = new ArrayList<>(size);
    }

    public int getSize() {
        return size;
    }

    public List<InteractiveObject> getContent() {
        return content;
    }

    public void addObject(InteractiveObject object) {
        if(content.size() == size)
            throw new IllegalStateException("Building is full");
        content.add(object);
    }

    @Override
    public String toString() {
        return new StringBuilder("Building: ")
                .append("\n\t- name: ").append(getName())
                .append("\n\t- size: ").append(size)
                .append("\n\t- content: ")
                .append("\n\t\t")
                .append(content.stream().map(Objects::toString).collect(Collectors.joining("\n\t\t")))
                .toString();
    }
}
