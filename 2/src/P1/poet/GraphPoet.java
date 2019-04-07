package P1.poet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import P1.graph.ConcreteEdgesGraph;
import P1.graph.Graph;

/**
 * A graph-based poetry generator.
 * 
 * <p>GraphPoet is initialized with a corpus of text, which it uses to derive a
 * word affinity graph.
 * Vertices in the graph are words. Words are defined as non-empty
 * case-insensitive strings of non-space non-newline characters. They are
 * delimited in the corpus by spaces, newlines, or the ends of the file.
 * Edges in the graph count adjacencies: the number of times "w1" is followed by
 * "w2" in the corpus is the weight of the edge from w1 to w2.
 * 
 * <p>For example, given this corpus:
 * <pre>    Hello, HELLO, hello, goodbye!    </pre>
 * <p>the graph would contain two edges:
 * <ul><li> ("hello,") -> ("hello,")   with weight 2
 *     <li> ("hello,") -> ("goodbye!") with weight 1 </ul>
 * <p>where the vertices represent case-insensitive {@code "hello,"} and
 * {@code "goodbye!"}.
 * 
 * <p>Given an input string, GraphPoet generates a poem by attempting to
 * insert a bridge word between every adjacent pair of words in the input.
 * The bridge word between input words "w1" and "w2" will be some "b" such that
 * w1 -> b -> w2 is a two-edge-long path with maximum-weight weight among all
 * the two-edge-long paths from w1 to w2 in the affinity graph.
 * If there are no such paths, no bridge word is inserted.
 * In the output poem, input words retain their original case, while bridge
 * words are lower case. The whitespace between every word in the poem is a
 * single space.
 * 
 * <p>For example, given this corpus:
 * <pre>    This is a test of the Mugar Omni Theater sound system.    </pre>
 * <p>on this input:
 * <pre>    Test the system.    </pre>
 * <p>the output poem would be:
 * <pre>    Test of the system.    </pre>
 * 
 * <p>PS2 instructions: this is a required ADT class, and you MUST NOT weaken
 * the required specifications. However, you MAY strengthen the specifications
 * and you MAY add additional methods.
 * You MUST use Graph in your rep, but otherwise the implementation of this
 * class is up to you.
 */
public class GraphPoet {
    
    private final Graph<String> graph = Graph.empty();
    
    // Abstraction function:
    // graph represents the graph poet of corpus
    // Representation invariant:
    // the vertices of graph should be String type
    // Safety from rep exposure:
    // graph is private and final
    // the String type is immutable
    
    /**
     * Create a new poet with the graph from corpus (as described above).
     * 
     * @param corpus text file from which to derive the poet's affinity graph
     * @throws IOException if the corpus file cannot be found or read
     */
    public GraphPoet(File corpus) throws IOException {
        FileReader fd = new FileReader(corpus);
		BufferedReader bd = new BufferedReader(fd);
        String tmp;
        while((tmp = bd.readLine()) != null) {
        	String[] a = tmp.split(" ");
        	for(int i = 0; i < a.length - 1; i++) {
        		graph.set(a[i].toLowerCase(), a[i + 1].toLowerCase(), 1);
        	}
        }
    }
    
    // checkRep
    public void checkRep() { 
    	ConcreteEdgesGraph<String> eg = (ConcreteEdgesGraph<String>)graph;
    	eg.checkRep();
    }
    
    /**
     * Generate a poem.
     * 
     * @param input string from which to create the poem
     * @return poem (as described above)
     */
    public String poem(String input) {
        String[] a =input.split(" ");
        boolean flag = false;
        String str = a[0];
        for(int i = 1; i < a.length; i++) {
        	flag = false;
    		for(String s : graph.targets(a[i - 1].toLowerCase()).keySet()) {
    			for(String t : graph.sources(a[i].toLowerCase()).keySet()) {
    				if(s.equalsIgnoreCase(t) && !s.equalsIgnoreCase(a[i])) {
    					str += " " + s.toLowerCase();
    					flag = true;
    					break;
    				}
    			}
    			if(flag)
    				break;
    		}
    		str += " " + a[i];
    	}
        return str;
    }

    // toString()
    public String toString() {
    	return graph.toString();
    }
    
}
