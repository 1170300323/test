package P1.graph;

import static org.junit.Assert.*;

//import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
//import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

/**
 * Tests for ConcreteEdgesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteEdgesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteEdgesGraphTest extends GraphInstanceTest {
	
    /*
     * Provide a ConcreteEdgesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteEdgesGraph<String>();
    }
    
    
    /*
     * Testing ConcreteEdgesGraph...
     */
    
    // Testing strategy for ConcreteEdgesGraph.toString()
    // the number of vertices: 0, 1 , > 1
    // the number of edges: 0, 1, > 1
    // test different weights of the edges
    
    // tests for ConcreteEdgesGraph.toString()
    @Test
    public void testToString() {
    	Graph<String> eg = emptyInstance();
    	String s = "";
    	assertEquals(s, eg.toString());
    	eg.add("a");
    	assertEquals(s, eg.toString());
    	eg.add("b");
    	eg.add("c");
    	eg.add("d");
    	eg.add("e");
    	eg.set("a", "b", 8);
    	s += "起始：a\n目标：b\n权值：8\n";
    	assertEquals(s, eg.toString());
    	eg.set("d", "a", 2);
    	eg.set("c", "b", 1);
    	eg.set("b", "e", 23);
    	eg.set("e", "c", 5);
    	s += "起始：d\n目标：a\n权值：2\n";
    	s += "起始：c\n目标：b\n权值：1\n";
    	s += "起始：b\n目标：e\n权值：23\n";
    	s += "起始：e\n目标：c\n权值：5\n";
    	assertEquals(s, eg.toString());
    }
    
    /*
     * Testing Edge...
     */
    
    // Testing strategy for Edge
    // the number of vertices: 0, 1, > 1
    // the number of edges: 0, 1, > 1
    // to test add, set, remove, vertices, sources and targets
    // weight: < 0, 0, > 0
    
    // tests for operations of Edge
    @Test
    @Override public void testAdd() {
    	Graph<String> eg = emptyInstance();
    	assertEquals(false, eg.add(null));
    	assertEquals(true, eg.add("a"));
    	assertEquals(false, eg.add("a"));
    }
    
    @Test
    @Override public void testSet() {
    	Graph<String> eg = emptyInstance();
    	assertEquals(0, eg.set(null, null, 0));
    	assertEquals(0, eg.set("a", "b", -2));
    	assertEquals(0, eg.set("a", "b", 2));
    	assertEquals(2, eg.set("a", "b", 1));
    	assertEquals(1, eg.set("a", "b", 0));
    }
    
    @Test
    @Override public void testRemove() {
    	Graph<String> eg = emptyInstance();
    	assertEquals(false, eg.remove(null));
    	eg.add("a");
    	assertEquals(false, eg.remove("b"));
    	assertEquals(true, eg.remove("a"));
    	eg.set("a", "b", 2);
    	assertEquals(true, eg.remove("a"));
    }
    
    @Test
    @Override public void testVertices() {
    	Set<String> set = new HashSet<>();
    	Graph<String> eg = emptyInstance();
    	assertEquals(set, eg.vertices());
    	eg.add("a");
    	set.add("a");
    	eg.add("b");
    	set.add("b");
    	assertEquals(set, eg.vertices());
    }
    
    @Test
    @Override public void testSources() {
    	Map<String, Integer> map = new HashMap<>();
    	Graph<String> eg = emptyInstance();
    	assertEquals(Collections.emptyMap(), eg.sources(null));
    	eg.add("a");
    	eg.add("b");
    	eg.add("c");
    	eg.add("d");
    	eg.set("b", "a", 8);
    	eg.set("c", "a", 2);
    	eg.set("d", "a", 5);
    	map.put("b", 8);
    	map.put("c", 2);
    	map.put("d", 5);
    	assertEquals(map, eg.sources("a"));
    }
    
    @Test
    @Override public void testTargets() {
    	Map<String, Integer> map = new HashMap<>();
    	Graph<String> eg = emptyInstance();
    	assertEquals(Collections.emptyMap(), eg.targets(null));
    	eg.add("a");
    	eg.add("b");
    	eg.add("c");
    	eg.add("d");
    	eg.set("a", "b", 8);
    	eg.set("a", "c", 2);
    	eg.set("a", "d", 5);
    	map.put("b", 8);
    	map.put("c", 2);
    	map.put("d", 5);
    	assertEquals(map, eg.targets("a"));
    }
    

}
