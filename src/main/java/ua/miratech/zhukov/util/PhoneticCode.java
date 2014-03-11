/* PhoneticCode.java
 * A class to generate phonetic code using the Metaphone algorithm.
 * reference: Computer Language of Dec. 1990, p 39
 *  "Hanging on the Metaphone" by Lawrence Philips
 *
 * This Java implementation, Copyright 1997, 2004 William B. Brogden
 * 130 Woodland Trail, Leander, TX 78641
 * is hereby released for all uses. I would appreciate hearing about it
 * if you find a good use for the class.  
 *  first version December, 1997
 *  minor mods for servlet  Febuary 2004
 *  wbrogden@bga.com  http://www.wbrogden.com/
 * Note, 2003 - the Apache Commons project is using this or something very close
 * http://jakarta.apache.org/commons/codec - see metaphone
*/

/* Notes: Feb 2004 - rearranging code to create a web service
 * The static method metaPhone converts an input String into a code.
 *   All input is converted to upper case.
 *   Limitations: Input format is expected to be a single ASCII word
 *   with only characters in the A - Z range, no punctuation or numbers.
 * 
 */

package ua.miratech.zhukov.util;

public class PhoneticCode {

	static String vowels = "AEIOU";
	static String frontv = "EIY";
	static String varson = "CSPTG";

	static final int maxCodeLen = 3;

	static public String metaPhone(String txt) {
		int mtsz = 0;
		boolean hard = false;
		if ((txt == null) ||
				(txt.length() == 0)) return "";
		// single character is itself
		if (txt.length() == 1) return txt.toUpperCase();
		//
		char[] inwd = txt.toUpperCase().toCharArray();
		//
		String tmpS;
		StringBuffer local = new StringBuffer(40); // manipulate
		StringBuffer code = new StringBuffer(10); //   output
		// handle initial 2 characters exceptions
		switch (inwd[0]) {
			case 'K':
			case 'G':
			case 'P': /* looking for KN, etc*/
				if (inwd[1] == 'N') local.append(inwd, 1, inwd.length - 1);
				else local.append(inwd);
				break;
			case 'A': /* looking for AE */
				if (inwd[1] == 'E') local.append(inwd, 1, inwd.length - 1);
				else local.append(inwd);
				break;
			case 'W': /* looking for WR or WH */
				if (inwd[1] == 'R') {   // WR -> R
					local.append(inwd, 1, inwd.length - 1);
					break;
				}
				if (inwd[1] == 'H') {
					local.append(inwd, 1, inwd.length - 1);
					local.setCharAt(0, 'W'); // WH -> W
				} else local.append(inwd);
				break;
			case 'X': /* initial X becomes S */
				inwd[0] = 'S';
				local.append(inwd);
				break;
			default:
				local.append(inwd);
		} // now local has working string with initials fixed
		int wdsz = local.length();
		int n = 0;
		while ((mtsz < maxCodeLen) && // max code size of 4 works well
				(n < wdsz)) {
			char symb = local.charAt(n);
			// remove duplicate letters except C
			if ((symb != 'C') &&
					(n > 0) && (local.charAt(n - 1) == symb)) n++;
			else { // not dup
				switch (symb) {
					case 'A':
					case 'E':
					case 'I':
					case 'O':
					case 'U':
						if (n == 0) {
							code.append(symb);
							mtsz++;
						}
						break; // only use vowel if leading char
					case 'B':
						if (!(n > 0 && local.charAt(n - 1) == 'M' &&
								(n + 1 == wdsz ||
										(n + 2 == wdsz && local.charAt(n + 1) == 'E')))) {
							// not MB or MBE at end of word
							code.append(symb);
							mtsz++;
						}
						break;
					case 'C': // lots of C special cases
			  /* discard if SCI, SCE or SCY */
						if ((n > 0) &&
								(local.charAt(n - 1) == 'S') &&
								(n + 1 < wdsz) &&
								(frontv.indexOf(local.charAt(n + 1)) >= 0)) {
							break;
						}
						tmpS = local.toString();
						if (tmpS.indexOf("CIA", n) == n) { // "CIA" -> X
							code.append('X');
							mtsz++;
							break;
						}
						if ((n + 1 < wdsz) &&
								(frontv.indexOf(local.charAt(n + 1)) >= 0)) {
							code.append('S');
							mtsz++;
							break; // CI,CE,CY -> S
						}
						if ((n > 0) &&
								(tmpS.indexOf("SCH", n - 1) == n - 1)) { // SCH->sk
							code.append('K');
							mtsz++;
							break;
						}
						if (tmpS.indexOf("CH", n) == n) { // detect CH
							if ((n == 0) &&
									(wdsz >= 3) &&    // CH consonant -> K consonant
									(vowels.indexOf(local.charAt(2)) < 0)) {
								code.append('K');
							} else {
								code.append('X'); // CHvowel -> X
							}
							mtsz++;
						} else {
							code.append('K');
							mtsz++;
						}
						break;
					case 'D':
						if ((n + 2 < wdsz) &&  // DGE DGI DGY -> J
								(local.charAt(n + 1) == 'G') &&
								(frontv.indexOf(local.charAt(n + 2)) >= 0)) {
							code.append('J');
							n += 2;
						} else {
							code.append('T');
						}
						mtsz++;
						break;
					case 'G': // GH silent at end or before consonant
						if ((n + 2 == wdsz) &&
								(local.charAt(n + 1) == 'H')) break;
						if ((n + 2 < wdsz) &&
								(local.charAt(n + 1) == 'H') &&
								(vowels.indexOf(local.charAt(n + 2)) < 0)) break;
						tmpS = local.toString();
						if ((n > 0) &&
								(tmpS.indexOf("GN", n) == n) ||
								(tmpS.indexOf("GNED", n) == n)) break; // silent G
						if ((n > 0) &&
								(local.charAt(n - 1) == 'G')) hard = true;
						else hard = false;
						if ((n + 1 < wdsz) &&
								(frontv.indexOf(local.charAt(n + 1)) >= 0) &&
								(!hard)) code.append('J');
						else code.append('K');
						mtsz++;
						break;
					case 'H':
						if (n + 1 == wdsz) break; // terminal H
						if ((n > 0) &&
								(varson.indexOf(local.charAt(n - 1)) >= 0)) break;
						if (vowels.indexOf(local.charAt(n + 1)) >= 0) {
							code.append('H');
							mtsz++;// Hvowel
						}
						break;
					case 'F':
					case 'J':
					case 'L':
					case 'M':
					case 'N':
					case 'R':
						code.append(symb);
						mtsz++;
						break;
					case 'K':
						if (n > 0) { // not initial
							if (local.charAt(n - 1) != 'C') {
								code.append(symb);
								mtsz++;
							}
						} else {
							code.append(symb); // initial K
							mtsz++;
						}
						break;
					case 'P':
						if ((n + 1 < wdsz) &&  // PH -> F
								(local.charAt(n + 1) == 'H')) code.append('F');
						else code.append(symb);
						mtsz++;
						break;
					case 'Q':
						code.append('K');
						mtsz++;
						break;
					case 'S':
						tmpS = local.toString();
						if ((tmpS.indexOf("SH", n) == n) ||
								(tmpS.indexOf("SIO", n) == n) ||
								(tmpS.indexOf("SIA", n) == n)) code.append('X');
						else code.append('S');
						mtsz++;
						break;
					case 'T':
						tmpS = local.toString(); // TIA TIO -> X
						if ((tmpS.indexOf("TIA", n) == n) || (tmpS.indexOf("TIO", n) == n)) {
							code.append('X');
							mtsz++;
							break;
						}
						if (tmpS.indexOf("TCH", n) == n) break;
						// substitute numeral 0 for TH (resembles theta after all)
						if (tmpS.indexOf("TH", n) == n) code.append('0');
						else code.append('T');
						mtsz++;
						break;
					case 'V':
						code.append('F');
						mtsz++;
						break;
					case 'W':
					case 'Y': // silent if not followed by vowel
						if ((n + 1 < wdsz) && (vowels.indexOf(local.charAt(n + 1)) >= 0)) {
							code.append(symb);
							mtsz++;
						}
						break;
					case 'X':
						code.append('K');
						code.append('S');
						mtsz += 2;
						break;
					case 'Z':
						code.append('S');
						mtsz++;
						break;
				} // end switch
				n++;
			} // end else from symb != 'C'
			if (mtsz > 4) code.setLength(4);
		}
		return code.toString();
	} // end static method metaPhone()

	public static String soundex(String word) {
		char[] chs = word.toCharArray();
		String soundex = "" + chs[0];
		for (int i = 1; i < 7; i++) {
			if (i > (chs.length - 1)) {
				break;
			}
			char newChar;
			switch (chs[i]) {
				case 'b':
				case 'p':
					newChar = '1';
					break;
				case 'f':
				case 'v':
					newChar = '2';
					break;
				case 'c':
				case 'k':
				case 's':
					newChar = '3';
					break;
				case 'g':
				case 'j':
					newChar = '4';
					break;
				case 'q':
				case 'x':
				case 'z':
					newChar = '5';
					break;
				case 'd':
				case 't':
					newChar = '6';
					break;
				case 'l':
					newChar = '7';
					break;
				case 'm':
				case 'n':
					newChar = '8';
					break;
				case 'r':
					newChar = '9';
					break;
				default:
					newChar = '0';
			}
			soundex += newChar;
		}
		return soundex;
	}

}