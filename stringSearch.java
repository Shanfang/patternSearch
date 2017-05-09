import java.io.*;

public class stringSearch {

// the brute force approach to search a substring in a string
	public boolean hasString(String text, String pattern) {
		int i = 0;
		int j = 0;
		while(i < text.length() && j < pattern.length()) {
			if(text.charAt(i) == pattern.charAt(j)) {
				i++;
				j++;
			}
			else {
				j = 0;
				i++;
			}
		}
		if(j == pattern.length()) {
			return true;
		}
		return false;
	}



	public static void main(String args[]) {
		String text = "abcd";
		String pattern = "abcde";
		stringSearch search = new stringSearch();
		System.out.println(search.hasString(text, pattern));

	}
}