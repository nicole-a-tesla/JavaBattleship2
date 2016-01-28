package test.java;

import main.java.ConsolePrinter;
import main.java.WelcomeSequence;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class WelcomeSequenceTest {
    private WelcomeSequence seq;

    @Before
    public void setup() {
        seq = new WelcomeSequence(new ConsolePrinter());
    }

    @Test
    public void doesntReplaceNewlines() {
        String newLine = "\n";
        assertFalse(seq.isNotPartOfNewline(newLine));
    }

    @Test
    public void replacesNonNewlines() {
        String notNewline = " ";
        assertTrue(seq.isNotPartOfNewline(notNewline));

    }
}
