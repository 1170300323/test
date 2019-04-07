package P1.graph;

import static org.junit.Assert.*;

import java.util.Collections;

import org.junit.Test;

/**
 * Tests for static methods of Graph.
 * 
 * To facilitate testing multiple implementations of Graph, instance methods are
 * tested in GraphInstanceTest.
 */
public class GraphStaticTest {
    
    // Testing strategy
    //   empty()
    //     no inputs, only output is empty graph
    //     observe with vertices()
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testEmptyVerticesEmpty() {
        assertEquals("expected empty() graph to have no vertices",
                Collections.emptySet(), Graph.empty().vertices());
    }
    
    // test other vertex label types in Problem 3.2
    @Test
    public void testEmptyInteger() {
    	Graph<Integer> a = Graph.empty();
    	assertEquals(Collections.emptySet(), a.vertices());
    }
    
    @Test
    public void testEmptyDouble() {
    	Graph<Double> a = Graph.empty();
    	assertEquals(Collections.emptySet(), a.vertices());
    }
    
    @Test
    public void testEmptyFloat() {
    	Graph<Float> a = Graph.empty();
    	assertEquals(Collections.emptySet(), a.vertices());
    }
    
    @Test
    public void testEmptyBoolean() {
    	Graph<Boolean> a = Graph.empty();
    	assertEquals(Collections.emptySet(), a.vertices());
    }
}
