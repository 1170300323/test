package P2;

import static org.junit.Assert.*;
import org.junit.Test;


public class FriendshipGraphTest {
	
	// graph is used to test friendshipGraph
	FriendshipGraph graph = new FriendshipGraph();
	
	@Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
	
	// Testing strategy
	// to test adding a new person, and test adding a person existed in the graph
	@Test
	public void addVertexTest(){
		Person p1 = new Person("Rachel");
		assertEquals(true, graph.addVertex(p1));
		Person p2 = new Person("Ross");
		assertEquals(true, graph.addVertex(p2));
		Person p3 = new Person("Ross");
		assertEquals(false, graph.addVertex(p3));
	}
	
	// to test adding an edge between an non-existed person in the graph
	// to test adding again an edge
	@Test
	public void addEdgeTest() {
		Person p1 = new Person("Rachel");
		Person p2 = new Person("Ross");
		Person p3 = new Person("Ben");
		Person p4 = new Person("Kramer");
		graph.addVertex(p1);
		graph.addVertex(p2);
		graph.addVertex(p3);
		assertEquals(true, graph.addEdge(p1, p2));
		assertEquals(true, graph.addEdge(p1, p3));
		assertEquals(false, graph.addEdge(p1, p3));
		assertEquals(false, graph.addEdge(p2, p4));
	}
	
	// to test the distance of a complex graph
	// and the other test like do not exist relation is testing in main function
	@Test
	public void getDistanceTest() {
		Person p1 = new Person("Rachel");
		Person p2 = new Person("Ross");
		Person p3 = new Person("Ben");
		Person p4 = new Person("Kramer");
		Person p5 = new Person("WangDa");
		graph.addVertex(p1);
		graph.addVertex(p2);
		graph.addVertex(p3);
		graph.addVertex(p4);
		graph.addVertex(p5);
		graph.addEdge(p1, p2);
		graph.addEdge(p2, p1);
		graph.addEdge(p2, p4);
		graph.addEdge(p4, p2);
		graph.addEdge(p3, p4);
		graph.addEdge(p4, p3);
		graph.addEdge(p1, p5);
		graph.addEdge(p5, p1);
		graph.addEdge(p3, p5);
		graph.addEdge(p5, p3);
		assertEquals(2, graph.getDistance(p1, p4));
	} 
}
