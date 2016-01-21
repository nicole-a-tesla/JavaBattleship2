package com.nicolemccabe.javabattleship2.test;

import com.nicolemccabe.javabattleship2.Board;
import com.nicolemccabe.javabattleship2.Game;
import com.nicolemccabe.javabattleship2.State;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static junit.framework.Assert.assertEquals;

public class GameTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayInputStream inContent = new ByteArrayInputStream("0, 0".getBytes());
    private Game game;
    private Board board;

    @Before
    public void setup() {
        System.setOut(new PrintStream(outContent));
        System.setIn(inContent);
        board = new Board(10);
        game = new Game(board);

    }

    @Test
    public void getsUserInput() throws IOException {
        String input = game.getInput();
        assertEquals("0, 0", input);
    }

    @Test
    public void testStrikesBoardAtCoordsAndReturnsState() {
        State resultingState = game.strikeBoardAt(0,0);
        assertEquals(State.MISS, resultingState);
    }

    @Test
    public void testGameSetsShipAndReturnsState() {
        State resultingState = game.setShipAt(0,0);
        assertEquals(State.SHIP, resultingState);
    }

    @Test
    public void testInputMapsToStruckShip() throws IOException {
        game.playersTurn();
        assertEquals(board.getStateAt(0,0), State.MISS);
    }

    @Test
    public void testFeedbackOnTurnResults() {
        System.setOut(new PrintStream(outContent));
        game.strikeBoardAt(0,0);
        assertEquals("MISS", outContent.toString());
    }
}
