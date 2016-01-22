package com.nicolemccabe.javabattleship2.test;

import com.nicolemccabe.javabattleship2.ConsolePrinter;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static junit.framework.Assert.assertEquals;

public class ConsolePrinterTest {
    @Test
    public void testPrintsToConsole() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        ConsolePrinter printer = new ConsolePrinter();
        String str = "PRINTED!";
        printer.print(str);
        assertEquals(str, outContent.toString());

        System.setOut(null);

    }
}
