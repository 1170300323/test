package P1.graph;

import java.util.ArrayList;
import java.util.Collections;
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
public class ConcreteVerticesGraph<L> implements Graph<L> {
    
    private final List<Vertex<L>> vertices = new ArrayList<>();
    
    // Abstraction function:
    // Each Edge<L> of vertices represents a vertex and its relative vertices
    // Representation invariant:
    // the vertex in vertices should not be same
    // Safety from rep exposure:
    // All fields are private
    // vertices is mutable, so vertices() makes a defensive copy
    
    // constructor
    public ConcreteVerticesGraph() {
    	checkRep();
    }
    
    // checkRep
    public void checkRep() {
    	for(Vertex<L> vt : vertices) {
    		vt.checkRep();
    	}
    }
    
    @Override public boolean add(L vertex) {
        if(vertex == null)
        	return false;
        for(Vertex<L> vt : vertices) {
        	if(vt.getVertex().equals(vertex))
        		return false;
        }
        Map<L, Integer> ms = new HashMap<>();
        Map<L, Integer> mt = new HashMap<>();
        Vertex<L> a = new Vertex<L>(vertex, ms, mt);
        vertices.add(a);
        checkRep();
        return true;
    }
    
    @Override public int set(L source, L target, int weight) {
    	if(source == null || target == null)
    		return 0;
    	if(weight < 0 || source.equals(target))
        	return 0;
        boolean flag1 = false, flag2 = false;
    	for(Vertex<L> v : vertices) {
        	if(source.equals(v.getVertex()))
        		flag1 = true;
        	if(target.equals(v.getVertex()))
        		flag2 = true;
        }
    	if(!flag1)
    		add(source);
    	if(!flag2)
    		add(target);
        int tmp = 0;
        for(Vertex<L> vt : vertices) {
        	if(vt.getVertex().equals(target)) {
        		if(weight == 0)
        			vt.removeSource(source);
        		else
        			vt.setSource(source, weight);
        	}
        	if(vt.getVertex().equals(source)) {
        		if(vt.getTarget().containsKey(target))
        			tmp = vt.getTarget().get(target);
        		if(weight == 0)
        			vt.removeTarget(target);
        		else
        			vt.setTarget(weight, target);
        	}
        }
        checkRep();
        return tmp;
    }
    
    @Override public boolean remove(L vertex) {
        for(Vertex<L> vt : vertices) {
        	if(vt.getVertex().equals(vertex)) {
        		vt.removeSource(vertex);
        		vt.removeTarget(vertex);
        		vertices.remove(vt);
        		checkRep();
        		return true;
        	}
        }
        checkRep();
        return false;
    }
    
    @Override public Set<L> vertices() {
        Set<L> set = new HashSet<>();
    	for(Vertex<L> vt : vertices) {
        	set.add(vt.getVertex());
        }
    	return set;
    }
    
    @Override public Map<L, Integer> sources(L target) {
        for(Vertex<L> vt : vertices) {
        	if(vt.getVertex().equals(target))
        		return vt.getSource();
        }
        return Collections.emptyMap();
    }
    
    @Override public Map<L, Integer> targets(L source) {
    	for(Vertex<L> vt : vertices) {
        	if(vt.getVertex().equals(source))
        		return vt.getTarget();
        }
        return Collections.emptyMap();
    }
    
    // toString()
    public String toString() {
    	String s = "";
    	for(Vertex<L> vt : vertices) {
    		s += vt.toString();
    	}
    	return s;
    }
}

/**
 * TODO specification
 * Mutable.
 * This class is internal to the rep of ConcreteVerticesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Vertex<L> {
    
    // fields
    private L vertex;//顶点
    
    private Map<L, Integer> sources = new HashMap<>();//所有以vertex为目标结点的边
    
    private Map<L, Integer> targets = new HashMap<>();//所有以vertex为起始结点的边
	
    // Abstraction function:
    // vertex represents the vertex of graphs
    // sources represents the edges with the target of vertex
    // targets represents the edges with the source of vertex
    // Representation invariant:
    // the vertex should not be same
    // Safety from rep exposure:
    // all fields are private
    // sources and targets are mutable, so should make defensive copies on them 
    
    // constructor
    public Vertex(L vertex, Map<L, Integer> sources, Map<L, Integer> targets) {
    	this.vertex = vertex;
    	this.sources = sources;
    	this.targets = targets;
    	checkRep();
    }
    // checkRep
    public void checkRep() {
    	assert vertex != null;
    	for(L s : sources.keySet()) {
    		assert s != null;
    		assert sources.get(s) >= 0;
    		if(sources.get(s) == 0)
    			sources.remove(s);
    	}
    	for(L s : targets.keySet()) {
    		assert s != null;
    		assert targets.get(s) >= 0;
    		if(targets.get(s) == 0)
    			targets.remove(s);
    	}
    }
    
    // methods
    
    /**
     * get the vertex
     * @return vertex
     */
    public L getVertex() {
    	return vertex;
    }
    
    /**
     * get the source
     * @return source
     */
    public Map<L, Integer> getSource() {
    	return new HashMap<>(sources);
    }
    
    /**
     * set the source and weight
     * @param source
     * @param weight
     */
    public void setSource(L source, int weight) {
    	if(source != null && weight > 0)
    		sources.put(source, weight);
    	checkRep();
    }
    
    /**
     * remove the source
     * @param source
     */
    public void removeSource(L source) {
    	if(source != null)
    		sources.remove(source);
    	checkRep();
    }
    
    /**
     * get the target
     * @return target
     */
    public Map<L, Integer> getTarget(){
    	return new HashMap<>(targets);
    }
    
    /**
     * set the weight and target
     * @param weight
     * @param target
     */
    public void setTarget(int weight, L target) {
    	if(target != null && weight > 0)
    		targets.put(target, weight);
    	checkRep();
    }
    
    /**
     * remove the target
     * @param target
     */
    public void removeTarget(L target) {
    	if(target != null)
    		targets.remove(target);
    	checkRep();
    }
    
    // toString()
    public String toString() {
    	String s = "顶点：" + vertex + "\n该点作为目标点时的边（起始点 权重）：\n";
    	for(L st : sources.keySet()) {
    		s += st + " " + sources.get(st) + "\n";
    	}
    	s += "该点作为起始点时的边（权重 目标点）：\n";
    	for(L st : targets.keySet()) {
    		s += targets.get(st) + " " + st + "\n";
    	}
    	return s;
    }
}
