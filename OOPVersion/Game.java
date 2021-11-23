package OOPVersion;


import java.util.Arrays;

public abstract class Game {        //change to interface with three methods ???

    private String version;
    public char[] guess = {'_','_','_','_'};
    public boolean[] hints  = {false, false, false, false};
    private char[] colours = {'w', 'y', 'o', 'r', 'p', 'b', 'g', 'v'};

    public Game(String version) {
        setVersion(version);
    }

    public char[] getGuess() {
        return Arrays.copyOf(guess, guess.length);
    }
    public void setGuess(char[] guess) {
        if(guess!=null)
            this.guess = Arrays.copyOf(guess, guess.length);
    }

    public boolean[] getHints() {
        return Arrays.copyOf(hints, hints.length);
    }
    public void setHints(boolean[] hints) {
        if(hints!=null)
            this.hints = Arrays.copyOf(hints, hints.length);
    }

    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    }

    public void setColours(char[] colours){
        if(colours!=null)
            this.colours = Arrays.copyOf(colours, colours.length);
    }
    public char[] getColours(){
        return Arrays.copyOf(colours, colours.length);
    }

    public abstract char[] createCode(String play);
    public abstract boolean validateColour(char c);
    public abstract int[] compareCode(char[] g, char[] s);
}
