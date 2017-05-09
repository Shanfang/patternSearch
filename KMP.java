/*
implement the KMP algorithm to do pattern search
time complexity is O(m+n), where m is the length of pattern
n is the length of text to be searched
*/
class KMP {
	void KMPSearch(String pattern, String text) {
		int M = pattern.length();
		int N = text.length();

		// first, buid array of lps, which is the longest proper prefix and
		// at the same time it is also a suffix, this takes O(m)
		int lps[] = new int[M];
		int j = 0 ;//index for pattern[]
		computeLps(pattern, M, lps);


		// second, find the pattern in text, 
		// time complexity is reduced to O(m+n) with lps

		int i = 0;//index for text[]
		while(i < N) {
			if(pattern.charAt(j)== text.charAt(i)) {
				i++;
				j++;
			}
			if(j == M) {
				System.out.println("Pattern is foud at index " + (i - j));
				j = lps[j - 1];
			}
			//there is a mismatch after j matches
			else if(i < N && pattern.charAt(j) != text.charAt(i)) {
				if(j != 0) {
					j = lps[j-1];
				}
				else {
					i++;
				}
		}
	}
}



	// this is where how lps is computed
	void computeLps(String pattern, int M, int lps[]) {
		int preLen = 0;// length of previous longest prefix suffix
		int i = 1; 
		lps[0] = 0;

		//compute lps[] for i = 1 to M-1
		while(i < M) {
			if(pattern.charAt(i) == pattern.charAt(preLen)) {
				preLen++;
				lps[i] = preLen;
				i++;
			}
			else {
				if(preLen != 0) {
					preLen = lps[preLen -1];
				}
				else {
					lps[i] = preLen;
					i++;
				}
			}
		}

	} 


	// driver program
	public static void main(String args[]) {
		String text = "ABABDABACDABABCABAB";
		String pattern = "ABABCABAB";
		KMP myKMP = new KMP();
		myKMP.KMPSearch(pattern, text);
	}

}




