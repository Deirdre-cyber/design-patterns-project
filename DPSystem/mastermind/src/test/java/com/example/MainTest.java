package com.example;
import org.junit.jupiter.api.Test;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainTest {

@Test
    public void testChoosePlayerName() {
        Scanner scanner = new Scanner("Alice\n");
        String result = Main.choosePlayerName(scanner);

        assertEquals("Alice", result);
    }

    @Test
    public void testGeneratePlayerName() {
        String result = Main.generatePlayerName();

        assertTrue(result.equals("JOHN") || result.equals("JANE") || result.equals("BOB")
                || result.equals("ALICE") || result.equals("JACK") || result.equals("JILL")
                || result.equals("BILL") || result.equals("BEN") || result.equals("SAM") || result.equals("SALLY"));
    }

}
