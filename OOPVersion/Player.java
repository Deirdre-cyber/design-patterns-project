package OOPVersion;

import javax.swing.*;

public class Player {

    public String name;

    public Player(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Player name: " + getName();
    }

//methods for Player Objects

}
