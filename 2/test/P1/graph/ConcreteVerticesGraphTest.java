package P1.graph;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

/**
 * Tests for ConcreteVerticesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteVerticesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteVerticesGraphTest extends GraphInstanceTest {
	
    /*
     * Provide a ConcreteVerticesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteVerticesGraph<String>();
    }
    
    /*
     * Testing ConcreteVerticesGraph...
     */
    
    // Testing strategy for ConcreteVerticesGraph.toString()
    // the number of vertices: 0, 1 , > 1
    // the number of edges: 0, 1, > 1
    // test different weights of the edges
    
    // tests for ConcreteVerticesGraph.toString()
    @Test
    public void testToString() {
    	Graph<String> cvg = emptyInstance();
    	cvg.add("a");
    	cvg.add("b");
    	cvg.add("c");
    	cvg.add("d");
    	cvg.add("e");
    	cvg.set("a", "b", 8);
    	cvg.set("b", "c", 3);
    	cvg.set("b", "e", 23);
    	cvg.set("c", "b", 1);
    	cvg.set("d", "a", 2);
    	cvg.set("d", "e", 12);
    	cvg.set("e", "c", 5);
    	String s = "";
    	s += "顶点：a\n该点作为目标点时的边（起始点 权重）：\nd 2\n该点作为起始点时的边（权重 目标点）：\n8 b\n";
    	s += "顶点：b\n该点作为目标点时的边（起始点 权重）：\na 8\nc 1\n该点作为起始点时的边（权重 目标点）：\n3 c\n23 e\n";
    	s += "顶点：c\n该点作为目标点时的边（起始点 权重）：\nb 3\ne 5\n该点作为起始点时的边（权重 目标点）：\n1 b\n";
    	s += "顶点：d\n该点作为目标点时的边（起始点 权重）：\n该点作为起始点时的边（权重 目标点）：\n2 a\n12 e\n";
    	s += "顶点：e\n该点作为目标点时的边（起始点 权重）：\nb 23\nd 12\n该点作为起始点时的边（权重 目标点）：\n5 c\n";
    	assertEquals(s, cvg.toString());
    }
    
    /*
     * Testing Vertex...
     */
    
    // Testing strategy for Vertex
    // the number of vertices: 0, 1, > 1
    // the number of edges: 0, 1, > 1
    // to test add, set, remove, vertices, sources and targets
    // weight: < 0, 0, > 0
    
    // tests for operations of Vertex
    
    @Test
    @Override public void testAdd() {
    	Graph<String> cvg = emptyInstance();
    	assertEquals(false, cvg.add(null));
    	assertEquals(true, cvg.add("a"));
    	assertEquals(false, cvg.add("a"));
    }
    
    @Test
    @Override public void testSet() {
    	Graph<String> cvg = emptyInstance();
    	assertEquals(0, cvg.set(null, null, 0));
    	assertEquals(0, cvg.set("a", "b", -2));
    	assertEquals(0, cvg.set("a", "b", 2));
    	assertEquals(2, cvg.set("a", "b", 1));
    	assertEquals(1, cvg.set("a", "b", 0));
    }
    
    @Test
    @Override public void testRemove() {
    	Graph<String> cvg = emptyInstance();
    	assertEquals(false, cvg.remove(null));
    	cvg.add("a");
    	assertEquals(false, cvg.remove("b"));
    	assertEquals(true, cvg.remove("a"));
    }
    
    @Test
    @Override public void testVertices() {
    	Set<String> set = new HashSet<>();
    	Graph<String> cvg = emptyInstance();
    	assertEquals(set, cvg.vertices());
    	cvg.add("a");
    	set.add("a");
    	cvg.add("b");
    	set.add("b");
    	assertEquals(set, cvg.vertices());
    }
    
    @Test
    @Override public void testSources() {
    	Map<String, Integer> map = new HashMap<>();
    	Graph<String> cvg = emptyInstance();
    	assertEquals(Collections.emptyMap(), cvg.sources(null));
    	cvg.add("a");
    	cvg.add("b");
    	cvg.add("c");
    	cvg.add("d");
    	cvg.set("b", "a", 8);
    	cvg.set("c", "a", 2);
    	cvg.set("d", "a", 5);
    	map.put("b", 8);
    	map.put("c", 2);
    	map.put("d", 5);
    	assertEquals(map, cvg.sources("a"));
    }
    
    @Test
    @Override public void testTargets() {
    	Map<String, Integer> map = new HashMap<>();
    	Graph<String> cvg = emptyInstance();
    	assertEquals(Collections.emptyMap(), cvg.targets(null));
    	cvg.add("a");
    	cvg.add("b");
    	cvg.add("c");
    	cvg.add("d");
    	cvg.set("a", "b", 8);
    	cvg.set("a", "c", 2);
    	cvg.set("a", "d", 5);
    	map.put("b", 8);
    	map.put("c", 2);
    	map.put("d", 5);
    	assertEquals(map, cvg.targets("a"));
    }
}
