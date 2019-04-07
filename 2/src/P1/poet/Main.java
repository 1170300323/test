package P1.poet;

import java.io.File;
import java.io.IOException;

/**
 * Example program using GraphPoet.
 * 
 * <p>PS2 instructions: you are free to change this example class.
 */
public class Main {
    
    /**
     * Generate example poetry.
     * 
     * @param args unused
     * @throws IOException if a poet corpus file cannot be found or read
     */
    public static void main(String[] args) throws IOException {
        final GraphPoet nimoy = new GraphPoet(new File("src/P1/poet/mugar-omni-theater.txt"));
        final String input = "Test the system.";
        System.out.println(input + "\n>>>\n" + nimoy.poem(input));
    }
    
}
