package P2;

public class Person {
	
	// Abstraction function:
    //   represents the person
    // Representation invariant:
    //   the person's name should not be null
    // Safety from rep exposure:
    //   all the fields are private
    //   all the types are immutable
	
	// the person's name
	private String name;
	
	// in the bfs, using dep to express the depth to another target
	private int dep;
	
	// in the bfs, using vis to express if the vertex is visited or not
	private boolean vis;
	
	// constructor: to initialize a person.
	public Person(String name) {
		this.name = name;
		this.dep = -1;
		this.vis = false;
	}
	
	/**
	 * get the name
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * set the dep
	 * @param dep
	 */
	public void setDep(int dep) {
		this.dep = dep;
	}
	
	/**
	 * get the dep
	 * @return dep
	 */
	public int getDep() {
		return dep;
	}
	
	/**
	 * set the vis
	 * @param vis
	 */
	public void setVis(boolean vis) {
		this.vis = vis;
	}
	
	/**
	 * get the vis
	 * @return vis
	 */
	public boolean getVis() {
		return vis;
	}
	
}
