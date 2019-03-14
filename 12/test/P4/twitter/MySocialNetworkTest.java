package P4.twitter;

import static org.junit.Assert.assertEquals;

import java.time.Instant;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class MySocialNetworkTest {
	private static final Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
    private static final Instant d2 = Instant.parse("2016-02-17T11:00:00Z");
    private static final Instant d3 = Instant.parse("2016-02-29T04:27:35Z");
    private static final Instant d4 = Instant.parse("2017-10-02T15:52:40Z");
    private static final Instant d5 = Instant.parse("2018-07-16T23:45:01Z");
    private static final Instant d6 = Instant.parse("2019-03-05T22:18:50Z");
    
    private static final Tweet tweet1 = new Tweet(1, "alyssa", "is it reasonable to talk about rivest so much? #hype #Luge #QQ", d1);
    private static final Tweet tweet2 = new Tweet(2, "bbitdiddle", "@CoachBourbon rivest talk in 30 minutes #hype #Luge #wechat", d2);
    private static final Tweet tweet3 = new Tweet(3, "CoachBourbon", "Have fun.#Linux @DCiszczon #QQ #Luge", d3);
    private static final Tweet tweet4 = new Tweet(4, "DCiszczon", "RT @CoachBourbon: For this #Luge #QQ demonstration, @NBCOlympics used their patented \\nBall-Cam\\u2122 technology.", d4);
    private static final Tweet tweet5 = new Tweet(5, "Sethersk82", "@Kurt @bbitdiddle @CoachBourbon @DCiszczon #hype What\\u2019s the old saying?#wechat", d5);
    private static final Tweet tweet6 = new Tweet(6, "Kurt", "RT @alyssa: #hype #Luge #QQ I want to put my lab1 on lab1@hit.edu.cn.#Linux", d6);
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testGetMentionedHashTags() {
    	Set<String> set = new HashSet<String>();
    	set.add("hype");
    	set.add("Luge");
    	set.add("QQ");
    	set.add("wechat");
    	set.add("Linux");
    	assertEquals(set, MySocialNetwork.getMetionedHashTags(Arrays.asList(tweet1, tweet2, tweet3, tweet4, tweet5, tweet6)));
    }
    
    @Test
    public void testGuessFollowsGraph() {
    	Map<String, Set<String>> map = new HashMap<String, Set<String>>();
    	Set<String> set = new HashSet<String>();
    	set.add("Kurt");
    	map.put("alyssa", set);
    	set = new HashSet<String>();
    	set.add("Sethersk82");
    	map.put("bbitdiddle", set);
    	set = new HashSet<String>();
    	set.add("Kurt");
    	map.put("CoachBourbon", set);
    	set = new HashSet<String>();
    	set.add("bbitdiddle");
    	map.put("Sethersk82", set);
    	set = new HashSet<String>();
    	set.add("alyssa");
    	set.add("CoachBourbon");
    	map.put("Kurt", set);
    	assertEquals(map, MySocialNetwork.guessFollowsGraph(Arrays.asList(tweet1, tweet2, tweet3, tweet4, tweet5, tweet6)));
    }
}
