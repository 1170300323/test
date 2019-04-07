package P2;

import java.util.LinkedList;
import java.util.Queue;
import P1.graph.*;

public class FriendshipGraph {
	
	// Abstraction function:
    //   represents the friendshipGraph
    // Representation invariant:
    //   Person should not be null or repeated
    // Safety from rep exposure:
    //   all the fields are private
    //   the boolean and int types are immutable
	
	// the graph to express the friendship
	private final Graph<Person> graph;
	
	// to initialize an empty graph
	public FriendshipGraph() {
		graph = Graph.empty();
	}
	
	/**
	 * add the person to the graph
	 * @param person p
	 * @return true if person p is non-existed, else return false
	 */
	public boolean addVertex(Person p) {
		for(Person person : graph.vertices()) {
			if(person.getName().equalsIgnoreCase(p.getName())) {
				System.out.println("不能再次输入已经存在的人");
				return false;
			}
		}
		graph.add(p);
		return true;
	}
	
	/**
	 *  add the relation between Person a and b
	 * @param Person p1
	 * @param Person p2
	 * @return relation between Person p1 and p2
	 */
	public boolean addEdge(Person p1, Person p2) {
		boolean flag1 = true, flag2 = true;
		for(Person p : graph.vertices()) {
			if(p.equals(p1))
				flag1 = false;
			if(p.equals(p2))
				flag2 = false;
		}
		if(flag1 || flag2) {
			System.out.println("不能与不存在的人建立关系");
			return false;
		}
		for(Person person : graph.targets(p1).keySet()) {
			if(p2.equals(person)) {
				System.out.println("两个人之前已经确立过关系");
				return false;
			}
		}
		graph.set(p1, p2, 1);
		return true;
	}
	
	/**
	 * @param p1
	 * @param p2
	 * @return the lowest distance between two persons
	 */
	public int getDistance(Person p1, Person p2) {
		Queue<Person> queue = new LinkedList<Person>();
		p1.setDep(0);
		queue.add(p1);
		while(queue.peek() != null) {
			Person q = queue.remove();
			q.setVis(true);
			for(Person p : graph.vertices()) {
				if(!p.getVis()) {
					for(Person r : graph.sources(p).keySet()) {
						if(r.getName().equalsIgnoreCase(q.getName())) {
							queue.add(p);
							p.setDep(q.getDep() + 1);
							if(p.getName().equals(p2.getName())) {
								return p2.getDep();
							}
						}
					}
				}
			}
		}
		return p2.getDep();
	}
}
