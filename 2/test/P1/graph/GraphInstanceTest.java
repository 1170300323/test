package P1.graph;

import static org.junit.Assert.*;

import java.util.Collections;

import org.junit.Test;

/**
 * Tests for instance methods of Graph.
 * 
 * <p>PS2 instructions: you MUST NOT add constructors, fields, or non-@Test
 * methods to this class, or change the spec of {@link #emptyInstance()}.
 * Your tests MUST only obtain Graph instances by calling emptyInstance().
 * Your tests MUST NOT refer to specific concrete implementations.
 */
public abstract class GraphInstanceTest {
    
    // Testing strategy
    // this class is used to be extended by other classes.
	// here we only test the situation that the graph is empty
    
    /**
     * Overridden by implementation-specific test classes.
     * 
     * @return a new empty graph of the particular implementation being tested
     */
    public abstract Graph<String> emptyInstance();
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testVertices() {
        // TODO you may use, change, or remove this test
        assertEquals("expected new graph to have no vertices",
                Collections.emptySet(), emptyInstance().vertices());
    }
    
    // TODO other tests for instance methods of Graph 
    @Test
    public void testAdd() {
    	assertEquals(false, emptyInstance().add(null));
    }
    
    @Test
    public void testSet() {
    	assertEquals(0, emptyInstance().set(null, null, 0));
    }
    
    @Test
    public void testRemove() {
    	assertEquals(false, emptyInstance().remove(null));
    }
    
    @Test
    public void testSources() {
    	assertEquals(Collections.emptyMap(), emptyInstance().sources(null));
    }
    
    @Test
    public void testTargets() {
    	assertEquals(Collections.emptyMap(), emptyInstance().targets(null));
    }
    
}
