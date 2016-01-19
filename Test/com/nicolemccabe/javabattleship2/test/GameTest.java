package com.nicolemccabe.javabattleship2.test;

import com.nicolemccabe.javabattleship2.Game;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static junit.framework.Assert.assertEquals;

public class GameTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayInputStream inContent = new ByteArrayInputStream("0".getBytes());
    private Game game;

    @Before
    public void setup() {
        System.setOut(new PrintStream(outContent));
        System.setIn(inContent);
        game = new Game();
    }

    @Test
    public void getsUserInput() throws IOException {
        String input = game.getInput();

        assertEquals("0", input);
    }

}
