/*
implementing Rabin-Karp algorithm for pattern match
*/
class RabinKarp {
	public static int NUM_INPUT = 256;//number of characters in input alphabet

	void RabinKarpSearch(String pattern, String text, int prime) {
		int M = pattern.length();
		int N = text.length();
		int i, j;
		int hashPattern = 0; //hash value for pattern
		int hashText = 0; //hash value for text
		int hash = 1;


		// the value of hash would be "pow(NUM_INPUT, M-1)% prime"
		for(i = 0; i < M-1; i++ ) {
			hash = (hash * NUM_INPUT) % prime;
		}
		//System.out.println("hash is " + hash);

		//compute the hash value of pattern and first window of text
		for(i = 0; i < M; i++) {
			hashPattern = (NUM_INPUT * hashPattern + pattern.charAt(i)) % prime;
			hashText = (NUM_INPUT * hashText + text.charAt(i)) % prime;
	}
		//System.out.println("hashPattern is " + hashPattern);
		//System.out.println("hashText is " + hashText);


		// slide the pattern over the text one by one
		for(i = 0; i <= N - M; i++) {
			//check the hash value of current window of text and
			//pattern, if hash value is the same, check if all the characters match
			if(hashPattern == hashText) {
				//System.out.println("There is a hash value match here!");
				for(j = 0; j < M; j++) {
					if(text.charAt(i + j) != pattern.charAt(j)) {
						// stop checking the remaining once there is a mismatch
						//System.out.println("Oops, the characters could not fully match...");
						break;
					}
				}

				// a match is found if there is no mismatch till the end of pattern
				if(j == M) {
					System.out.println("A match is found at index " + i);
				}
			}

			// compute hash value for next window of text
			// remove the first digit and append a digit
			//System.out.println("Iteration " + i + ", hashText is " + hashText);
			if(i < N-M) {
				hashText = (NUM_INPUT * (hashText - text.charAt(i) * hash) + text.charAt(i + M))% prime;

				// handle the negative integer scenario
				if(hashText < 0) {
					hashText = hashText + prime;
				}
			}

		}
	}

	// driver program
	public static void main(String args[]) {
		String text = "ABABDABACDABABCABAB";
		String pattern = "ABABCABAB";
		int prime = 101;
		RabinKarp rabinKarp = new RabinKarp();
		rabinKarp.RabinKarpSearch(pattern, text, prime);
	}
}
