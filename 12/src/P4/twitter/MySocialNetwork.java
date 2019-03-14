package P4.twitter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MySocialNetwork {
	public static Set<String> getMetionedHashTags(List<Tweet> tweets) {
		Set<String> set = new HashSet<String>();
        for(Tweet tw : tweets) {
        	String pattern = "#([\\w]*)";
        	Pattern r = Pattern.compile(pattern);
        	Matcher m = r.matcher(tw.getText());
        	while(m.find()) {
        		set.add(m.group(1));
        	}
        }
        return set;
	}
	
	/* 
	  * 如果两个人有很多共有的hash tag或者共享有一个热度很低的hash tag那么任务两个人会互相影响即互相follow
	 */
	public static Map<String, Set<String>> guessFollowsGraph(List<Tweet> tweets) {
		Map<String, Set<String>> map = new HashMap<String, Set<String>>();
		Map<String, Set<String>> ms = new HashMap<String, Set<String>>();//hashtag，对应人的集合
		Map<String, Set<String>> sm = new HashMap<String, Set<String>>();//人，对应的hashtags
		for(Tweet tw : tweets) {
			for(String s : getMetionedHashTags(Arrays.asList(tw))) {
				Set<String> set = new HashSet<String>();
				set.add(tw.getAuthor());
				for(String key : ms.keySet()) {
					if(key.equalsIgnoreCase(s)) {
        				if(!key.equals(s))
        					s = key;
        				ms.get(key).add(tw.getAuthor());
        				set = ms.get(key);
        				break;
        			}
				}
				ms.put(s, set);
			}
			Set<String> set2 = new HashSet<String>();
			set2.addAll(getMetionedHashTags(Arrays.asList(tw)));
			for(String key : sm.keySet()) {
				if(key.equalsIgnoreCase(tw.getAuthor())) {
    				sm.get(key).addAll(getMetionedHashTags(Arrays.asList(tw)));
    				set2 = sm.get(key);
    				break;
    			}
			}
			sm.put(tw.getAuthor(), set2);
		}
		for (Map.Entry<String, Set<String>> entry : ms.entrySet()) {
		    if(entry.getValue().size() <= 3) {
		    	for(String key : entry.getValue()) {
		    		Set<String> set = new HashSet<String>();
		    		for(String key2 : entry.getValue()) {
		    			if(key2 == key)
		    				continue;
		    			set.add(key2);
		    		}
		    		map.put(key, set);
		    	}
		    }
		}
		for (String key : sm.keySet()) {
			for(String key2 : sm.keySet()) {
				if(key.equalsIgnoreCase(key2))
					continue;
				int t = 0;
				for(String s1 : sm.get(key)) {
					for(String s2 : sm.get(key2)) {
						if(s1.equalsIgnoreCase(s2))
							t++;
					}
				}
				Set<String> set = new HashSet<String>();
				Set<String> set2 = new HashSet<String>();
				if(t >= 3) {
					set.add(key2);
					set2.add(key);
					for(String k : map.keySet()) {
						if(k == key) {
							set = map.get(key);
							set.add(key2);
						}
						if(k == key2) {
							set2 = map.get(key2);
							set.add(key);
						}
					}
					if(set.contains(key)) {
						set.remove(key);
					}
					if(set2.contains(key2)) {
						set2.remove(key2);
					}
					map.put(key, set);
					map.put(key2, set2);
				}
			}
		}
		return map;
	}
}
