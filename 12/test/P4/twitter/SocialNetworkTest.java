package P4.twitter;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class SocialNetworkTest {

    /*
     * TODO: your testing strategies for these methods should go here.
     * See the ic03-testing exercise for examples of what a testing strategy comment looks like.
     * Make sure you have partitions.
     */
	
	private static final Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
    private static final Instant d2 = Instant.parse("2016-02-17T11:00:00Z");
    private static final Instant d3 = Instant.parse("2016-02-29T04:27:35Z");
    private static final Instant d4 = Instant.parse("2017-10-02T15:52:40Z");
    private static final Instant d5 = Instant.parse("2018-07-16T23:45:01Z");
    private static final Instant d6 = Instant.parse("2019-03-05T22:18:50Z");
    
    private static final Tweet tweet1 = new Tweet(1, "alyssa", "is it reasonable to talk about rivest so much?", d1);
    private static final Tweet tweet2 = new Tweet(2, "bbitdiddle", "@CoachBourbon rivest talk in 30 minutes #hype", d2);
    private static final Tweet tweet3 = new Tweet(3, "CoachBourbon", "Have fun.@DCiszczon", d3);
    private static final Tweet tweet4 = new Tweet(4, "DCiszczon", "RT @CoachBourbon: For this #Luge demonstration, @NBCOlympics used their patented \\nBall-Cam\\u2122 technology.", d4);
    private static final Tweet tweet5 = new Tweet(5, "Sethersk82", "@Kurt @bbitdiddle @CoachBourbon @DCiszczon What\\u2019s the old saying?", d5);
    private static final Tweet tweet6 = new Tweet(6, "Kurt", "RT @alyssa: I want to put my lab1 on lab1@hit.edu.cn.", d6);
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testGuessFollowsGraphEmpty() {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(new ArrayList<>());
        assertTrue("expected empty graph", followsGraph.isEmpty());
        List<Tweet> tweets = Arrays.asList(tweet1, tweet2, tweet3, tweet4, tweet5, tweet6);
        Set<String> set = new HashSet<String>();
        set.add("Kurt");
        followsGraph.put("alyssa", set);
        set = new HashSet<String>();
        set.add("Sethersk82");
        followsGraph.put("bbitdiddle", set);
        set = new HashSet<String>();
        set.add("Sethersk82");
        set.add("bbitdiddle");
        set.add("DCiszczon");
        followsGraph.put("CoachBourbon", set);
        set = new HashSet<String>();
        set.add("Sethersk82");
        set.add("CoachBourbon");
        followsGraph.put("DCiszczon", set);
        set = new HashSet<String>();
        set.add("Sethersk82");
        followsGraph.put("Kurt", set);
        assertEquals(followsGraph, SocialNetwork.guessFollowsGraph(tweets));
    }
    
    @Test
    public void testInfluencersEmpty() {
        Map<String, Set<String>> followsGraph = new HashMap<>();
        List<String> influencers = SocialNetwork.influencers(followsGraph);
        assertTrue("expected empty list", influencers.isEmpty());
        Set<String> set = new HashSet<String>();
        set.add("Kurt");
        followsGraph.put("alyssa", set);
        set = new HashSet<String>();
        set.add("Sethersk82");
        followsGraph.put("bbitdiddle", set);
        set = new HashSet<String>();
        set.add("Sethersk82");
        set.add("bbitdiddle");
        set.add("DCiszczon");
        followsGraph.put("CoachBourbon", set);
        set = new HashSet<String>();
        set.add("Sethersk82");
        set.add("CoachBourbon");
        followsGraph.put("DCiszczon", set);
        set = new HashSet<String>();
        set.add("Sethersk82");
        followsGraph.put("Kurt", set);
        influencers = SocialNetwork.influencers(followsGraph);
        List<String> list = new ArrayList<String>();
        list.addAll(Arrays.asList("CoachBourbon", "DCiszczon", "bbitdiddle", "alyssa", "Kurt"));
        assertEquals(list, influencers);
    }

    /*
     * Warning: all the tests you write here must be runnable against any
     * SocialNetwork class that follows the spec. It will be run against several
     * staff implementations of SocialNetwork, which will be done by overwriting
     * (temporarily) your version of SocialNetwork with the staff's version.
     * DO NOT strengthen the spec of SocialNetwork or its methods.
     * 
     * In particular, your test cases must not call helper methods of your own
     * that you have put in SocialNetwork, because that means you're testing a
     * stronger spec than SocialNetwork says. If you need such helper methods,
     * define them in a different class. If you only need them in this test
     * class, then keep them in this test class.
     */

}
