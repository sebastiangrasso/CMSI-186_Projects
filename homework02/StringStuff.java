class StringStuff{

	public static void main (String args[]){

		String s = "REHEARSALS";

		containsVowel(s);
		isPalindrome(s);
		evensOnly(s);
		oddsOnly(s);
		evensOnlyNoDupes(s);
		oddsOnlyNoDupes(s);
		reverse(s);
	}

	public static boolean containsVowel(String s){

		String vowels = "AEIOUaeiou";

		for (int i = 0; i < vowels.length(); i++){
			if (s.contains("" + vowels.charAt(i))==true)
				return true;
		}

		return false;
	}

	public static boolean isPalindrome(String s){

		String reverse = reverse(s);
		s = s.toLowerCase();
		reverse = reverse.toLowerCase();

		return (s.equals(reverse));
	}

	public static String evensOnly(String s){

		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		String evenAlphabet = "";
		String result = "";

		for(int i = 1; i < alphabet.length(); i=i+2){
			evenAlphabet = evenAlphabet + alphabet.charAt(i);
		}

		evenAlphabet = evenAlphabet + evenAlphabet.toUpperCase();

		for(int i = 0; i <s.length(); i++){
			if (evenAlphabet.contains("" + s.charAt(i)))
			result = result + s.charAt(i);
		}

		return result;
	}

	public static String oddsOnly(String s){

		String alphabet = "abcdefghijklmnopqrstuvwxyZ";
		String oddAlphabet = "";
		String result = "";

		for(int i = 0; i < alphabet.length(); i=i+2){
			oddAlphabet = oddAlphabet + alphabet.charAt(i);
		}

		oddAlphabet = oddAlphabet + oddAlphabet.toUpperCase();

		for(int i = 0; i <s.length(); i++){
			if (oddAlphabet.contains("" + s.charAt(i)))
			result = result + s.charAt(i);
		}

		return result;
	}

	public static String evensOnlyNoDupes(String s){
	return removeDupes(evensOnly(s));
	}

	public static String oddsOnlyNoDupes(String s){
	return removeDupes(oddsOnly(s));
	}

	public static String removeDupes(String s){

    String result = "";

    for (int i = 0; i < s.length(); i++) {
        if(!result.contains(String.valueOf(s.charAt(i)))) {
            result += String.valueOf(s.charAt(i));
        }
    }
    return result;
}


	public static String reverse(String s){

		String reversedWord = "";

		for (int i= s.length()-1; i >=0; i--)
			reversedWord = reversedWord + s.charAt(i);

		return reversedWord;
	}
}
