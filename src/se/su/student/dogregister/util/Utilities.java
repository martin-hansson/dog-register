/**
 * @author Martin Hansson, maha6445.
 */

package se.su.student.dogregister.util;

/**
 * Different utility methods used for the dog register.
 */
public class Utilities {
    /**
     * Convert a string to title case (uppercase first letter for each word).
     * @param str the string to convert.
     * @return the converted string.
     */
    public static String toTitleCase(String str) {
        String[] words = str.trim().split(" ");

        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].substring(0, 1).toUpperCase().concat(words[i].substring(1).toLowerCase());
        }

        return String.join(" ", words);
    }
}
