package OOPVersion;

import java.util.Arrays;

public class TestGame {

    public static void main(String[] args) {


        KidsVersion kidsGame = new KidsVersion();

        ClassicVersion classicVersion = new ClassicVersion();

        ExpertVersion expertVersion = new ExpertVersion();


        System.out.println(kidsGame + "\n" + Arrays.toString(kidsGame.getColours()));
        System.out.println(classicVersion + "\n" + Arrays.toString(classicVersion.getColours()));
        System.out.println(expertVersion + "\n" + Arrays.toString(expertVersion.getColours()));

    }
}
