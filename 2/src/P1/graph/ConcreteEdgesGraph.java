package P1.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * An implementation of Graph.
 * 
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteEdgesGraph<L> implements Graph<L> {
    
    private final Set<L> vertices = new HashSet<>();
    private final List<Edge<L>> edges = new ArrayList<>();
    
    // Abstraction function:
    // Each L of vertices represent a vertex in the graph
    // Each Edge<L> of edges represent a edge which is from the source vertex to target vertex
    // Representation invariant:
    // There are not two Edge<L> with same source and target
    // The source and target of Edge<L> should be in the vertices
    // Safety from rep exposure:
    // All fields are private and final
    // vertices is mutable, so vertices() should make a defensive copy
    // edges is immutable
    
    // constructor
    public ConcreteEdgesGraph() {
    	checkRep();
    }
    
    // checkRep
    public void checkRep() {
    	for(Edge<L> edge : edges) {
    		assert vertices.contains(edge.getSource());
    		assert vertices.contains(edge.getTarget());
    		edge.checkRep();
    	}
    }
    
    @Override public boolean add(L vertex) {
        if(vertex == null)
        	return false;
        for(L s : vertices) {
        	if(s.equals(vertex))
        		return false;
        }
        vertices.add(vertex);
        return true;
    }
    
    @Override public int set(L source, L target, int weight) {
    	if(source == null || target == null)
    		return 0;
    	else if(weight < 0 || source.equals(target))
    		return 0;
        for(Edge<L> ed : edges) {
        	if(ed.getSource().equals(source) && ed.getTarget().equals(target)) {
        		int tmp = ed.getWeight();
        		if(weight != 0)	
            		ed.setWeight(weight);
        		else
        			edges.remove(ed);
        		checkRep();
        		return tmp;
        	}
        }
        vertices.add(source);
        vertices.add(target);
        Edge<L> ed0 = new Edge<L>(source, target, weight);
        edges.add(ed0);
        checkRep();
        return 0;
    }
    
    @Override public boolean remove(L vertex) {
    	if(vertex == null || !vertices.contains(vertex))
    		return false;
    	vertices.remove(vertex);
    	for(Edge<L> ed : edges) {
    		if(ed.getSource().equals(vertex) || ed.getTarget().equals(vertex)) {
    			edges.remove(ed);
    			break;
    		}
    	}
    	checkRep();
    	return true;
    }
    
    @Override public Set<L> vertices() {
        return new HashSet<>(vertices);
    }
    
    @Override public Map<L, Integer> sources(L target) {
        Map<L, Integer> map = new HashMap<>();
        for(Edge<L> ed : edges) {
        	if(ed.getTarget().equals(target))
        		map.put(ed.getSource(), ed.getWeight());
        }
		return map;
    }
    
    @Override public Map<L, Integer> targets(L source) {
    	Map<L, Integer> map = new HashMap<>();
        for(Edge<L> ed : edges) {
        	if(ed.getSource().equals(source))
        		map.put(ed.getTarget(), ed.getWeight());
        }
		return map;
    }
    
    // toString()
    @Override public String toString() {
    	String s = "";
    	for(Edge<L> ed : edges) {
    		s += ed.toString();
    	}
    	return s;
    }
}

/**
 * TODO specification
 * Immutable.
 * This class is internal to the rep of ConcreteEdgesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Edge<L> {
    
    // fields
    private L source;// source vertex
    
    private L target;// target vertex 
    
    private int weight;// the weight of the edge
    
    // Abstraction function:
    // Each L of source represents the source vertex
    // Each L of target represents the target vertex
    // weight represents the weight of edge
    // Representation invariant:
    // weight of the edge should over 0
    // the source and target should not be same
    // Safety from rep exposure:
    // All fields are private
    // In this class, all of the variables are immutable
    
    // constructor
    public Edge(L source, L target, int weight) {
		setSource(source);
		setTarget(target);
		setWeight(weight);
		checkRep();
	}
    
    // checkRep
    public void checkRep() {
    	assert source != null;
    	assert target != null;
    	assert weight > 0;
    	assert source != target;
    }
    
    // methods
    
    /**
     * set the source
     * @param source
     */
    public void setSource(L source) {
    	this.source = source;
    }
    
    /**
     * get the source
     * @return source
     */
    public L getSource() {
    	checkRep();
    	return source;
    }
    
    /**
     * set the target
     * @param target
     */
    public void setTarget(L target) {
    	this.target = target;
    }
    
    /**
     * get the target
     * @return target
     */
    public L getTarget() {
    	checkRep();
    	return target;
    }
    
    /**
     * set the weight
     * @param weight
     */
    public void setWeight(int weight) {
    	this.weight = weight;
    }
    
    /**
     * get the weight
     * @return weight
     */
    public int getWeight() {
    	checkRep();
    	return weight;
    }
    
    // toString()
    public String toString() {
    	checkRep();
    	return "起始：" + source + "\n目标：" + target + "\n权值：" + weight + "\n";
    }
    
}
