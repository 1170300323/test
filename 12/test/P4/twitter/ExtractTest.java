package P4.twitter;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class ExtractTest {

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
    private static final Tweet tweet2 = new Tweet(2, "bbitdiddle", "rivest talk in 30 minutes #hype", d2);
    private static final Tweet tweet3 = new Tweet(3, "CoachBourbon", "RT @ThatBoysGood: The Winter Olympics are like hockey and soccer if hockey and soccer fans didn\\u2019t try to convince you they\\u2019re fun.", d3);
    private static final Tweet tweet4 = new Tweet(4, "DCiszczon", "For this #Luge demonstration, @NBCOlympics used their patented \\nBall-Cam\\u2122 technology.", d4);
    private static final Tweet tweet5 = new Tweet(5, "Sethersk82", "@BenSuttonISP @TeamUSA @Olympics @pyeongchang2018 What\\u2019s the old saying?", d5);
    private static final Tweet tweet6 = new Tweet(6, "Kurt", "I want to put my lab1 on lab1@hit.edu.cn.", d6);
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testGetTimespanTwoTweets() {
        Timespan timespan = Extract.getTimespan(Arrays.asList(tweet1, tweet2));
        assertEquals("expected start", d1, timespan.getStart());
        assertEquals("expected end", d2, timespan.getEnd());
        timespan = Extract.getTimespan(Arrays.asList(tweet3, tweet4, tweet5));
        assertEquals("expected start", d3, timespan.getStart());
        assertEquals("expected end", d5, timespan.getEnd());
        timespan = Extract.getTimespan(Arrays.asList(tweet1, tweet2, tweet3, tweet4, tweet5, tweet6));
        assertEquals("expected start", d1, timespan.getStart());
        assertEquals("expected end", d6, timespan.getEnd());
    }
    
    @Test
    public void testGetMentionedUsersNoMention() {
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet1));
        assertTrue("expected empty set", mentionedUsers.isEmpty());
        Set<String> tp = new HashSet<String>();
        mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet1, tweet2));
        assertTrue("expected empty set", mentionedUsers.isEmpty());
        mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet1, tweet2, tweet3));
        tp.add("ThatBoysGood");
        assertEquals(tp, mentionedUsers);
        mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet1, tweet2, tweet3, tweet4));
        tp.add("NBCOlympics");
        assertEquals(tp, mentionedUsers);
        mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet1, tweet2, tweet3, tweet4, tweet5));
        tp.add("BenSuttonISP");
        tp.add("TeamUSA");
        tp.add("Olympics");
        tp.add("pyeongchang2018");
        assertEquals(tp, mentionedUsers);
        mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet6));
        assertTrue("expected empty set", mentionedUsers.isEmpty());
    }

    /*
     * Warning: all the tests you write here must be runnable against any
     * Extract class that follows the spec. It will be run against several staff
     * implementations of Extract, which will be done by overwriting
     * (temporarily) your version of Extract with the staff's version.
     * DO NOT strengthen the spec of Extract or its methods.
     * 
     * In particular, your test cases must not call helper methods of your own
     * that you have put in Extract, because that means you're testing a
     * stronger spec than Extract says. If you need such helper methods, define
     * them in a different class. If you only need them in this test class, then
     * keep them in this test class.
     */

}
