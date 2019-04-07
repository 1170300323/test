package P1.poet;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

/**
 * Tests for GraphPoet.
 */
public class GraphPoetTest {
    
    // Testing strategy
    // to test some kinds of corpus 
	// and the input maybe empty, with no words in corpus, with a two-weight-edge words in corpus
	// with more than a two-weight-edge words in corpus
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    // tests
    @Test
    public void testPoem() throws IOException {
    	File file = new File("test/P1/poet/seven-words.txt");
    	GraphPoet a = new GraphPoet(file);
    	assertEquals("", a.poem(""));
    	assertEquals("You are so smart", a.poem("You are so smart"));
    	assertEquals("Seek to explore strange new life and exciting synergies!", a.poem("Seek to explore new and exciting synergies!"));
    	assertEquals("Seek out new life and strange new worlds and new civilizations", a.poem("Seek new and strange worlds and civilizations"));
    }
}
