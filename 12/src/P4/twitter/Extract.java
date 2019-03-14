package P4.twitter;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.*;

/**
 * Extract consists of methods that extract information from a list of tweets.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class Extract {

    /**
     * Get the time period spanned by tweets.
     * 
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     * @return a minimum-length time interval that contains the timestamp of
     *         every tweet in the list.
     */
    public static Timespan getTimespan(List<Tweet> tweets) {
    	Instant start = Instant.parse("1970-01-01T00:00:00Z"), end = start;
    	boolean flag = true;
    	for(Tweet tw : tweets) {
    		if(flag) {
    			start = tw.getTimestamp();
    			flag = false;
    		}
    		end = tw.getTimestamp();
    	}
        Timespan timespan = new Timespan(start, end);
        return timespan;
    }

    /**
     * Get usernames mentioned in a list of tweets.
     * 
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     * @return the set of usernames who are mentioned in the text of the tweets.
     *         A username-mention is "@" followed by a Twitter username (as
     *         defined by Tweet.getAuthor()'s spec).
     *         The username-mention cannot be immediately preceded or followed by any
     *         character valid in a Twitter username.
     *         For this reason, an email address like bitdiddle@mit.edu does NOT 
     *         contain a mention of the username mit.
     *         Twitter usernames are case-insensitive, and the returned set may
     *         include a username at most once.
     */
    public static Set<String> getMentionedUsers(List<Tweet> tweets) {
        Set<String> set = new HashSet<String>();
        for(Tweet tw : tweets) {
        	String pattern = "@([\\w-]+)";
        	String pattern2 = "([\\w-]+)@([\\w-]+)";
        	Pattern r = Pattern.compile(pattern);
        	Matcher m = r.matcher(tw.getText());
        	Pattern r2 = Pattern.compile(pattern2);
        	Matcher m2 = r2.matcher(tw.getText());
        	while(m.find()) {
        		set.add(m.group(1));
        	}
        	while(m2.find()) {
        		set.remove(m2.group(2));
        	}
        }
        return set;
    }
    
}
