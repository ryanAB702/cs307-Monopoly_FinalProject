package Controller;

@Deprecated
public class Token {
    int location;

    public Token(int location) {
        this.location = location;
    }

    public int move(int numSpaces) {
        location += numSpaces;
        return location;
    }

    public int moveTo(int newSpace) {
        location = newSpace;
        return newSpace;
    }

    public int getCurrentLocation() {
        return location;
    }

    public void setCurrentLocation(int newLocation) {
        location = newLocation;
    }

}