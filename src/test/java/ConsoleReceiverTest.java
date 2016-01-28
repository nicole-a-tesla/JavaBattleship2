package test.java;

import com.nicolemccabe.javabattleship2.ConsoleReceiver;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static junit.framework.Assert.assertEquals;

public class ConsoleReceiverTest {
    @Test
    public void testReceivesFromConsole() throws IOException {
        String test = "test";
        ByteArrayInputStream inContent = new ByteArrayInputStream(test.getBytes());
        System.setIn(inContent);

        ConsoleReceiver receiver = new ConsoleReceiver();
        String input = receiver.getUserInput();

        assertEquals(test, input);
    }
}
