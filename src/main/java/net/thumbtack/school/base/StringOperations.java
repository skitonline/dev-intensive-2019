package net.thumbtack.school.base;

import java.text.DecimalFormat;

public class StringOperations {
    public static int getSummaryLength(String[] strings){
        int result = 0;
        for (String el : strings)
            result += el.length();
        return result;
    }

    public static String getFirstAndLastLetterString(String string){
        char[] result = {string.charAt(0), string.charAt(string.length() - 1)};
        return new String(result);
    }

    public static boolean isSameCharAtPosition(String string1, String string2, int index){
        return string1.charAt(index) == string2.charAt(index);
    }

    public static boolean isSameFirstCharPosition(String string1, String string2, char character){
        return string1.indexOf(character) == string2.indexOf(character);
    }

    public static boolean isSameLastCharPosition(String string1, String string2, char character){
        return string1.lastIndexOf(character) == string2.lastIndexOf(character);
    }

    public static boolean isSameFirstStringPosition(String string1, String string2, String str){
        return string1.lastIndexOf(str) == string2.lastIndexOf(str);
    }

    public static boolean isSameLastStringPosition(String string1, String string2, String str){
        return string1.lastIndexOf(str) == string2.lastIndexOf(str);
    }

    public static boolean isEqual(String string1, String string2){
        return string1.equals(string2);
    }

    public static boolean isEqualIgnoreCase(String string1, String string2){
        return string1.equalsIgnoreCase(string2);
    }

    public static boolean isLess(String string1, String string2){
        return string1.compareTo(string2) < 0;
    }

    public static boolean isLessIgnoreCase(String string1, String string2){
        return string1.compareToIgnoreCase(string2) < 0;
    }

    public static String concat(String string1, String string2){
        return string1.concat(string2);
    }

    public static boolean isSamePrefix(String string1, String string2, String prefix){
        return string1.startsWith(prefix) && string2.startsWith(prefix);
    }

    public static boolean isSameSuffix(String string1, String string2, String suffix){
        return string1.endsWith(suffix) && string2.endsWith(suffix);
    }

    public static String getCommonPrefix(String string1, String string2){
        int len = Math.min(string1.length(), string2.length());
        int endIndex;
        for (endIndex = 0; endIndex < len && string1.charAt(endIndex) == string2.charAt(endIndex); endIndex++);
        return string1.substring(0, endIndex);
    }

    public static String reverse(String string){
        return new StringBuilder(string).reverse().toString();
    }

    public static boolean isPalindrome(String string){
        return string.compareTo(reverse(string)) == 0;
    }

    public static boolean isPalindromeIgnoreCase(String string){
        return string.compareToIgnoreCase(reverse(string)) == 0;
    }

    public static String getLongestPalindromeIgnoreCase(String[] strings){
        String result = "";
        for (String el : strings)
            if (el.length() > result.length() && isPalindromeIgnoreCase(el))
                result = el;
        return result;
    }

    public static boolean hasSameSubstring(String string1, String string2, int index, int length){
        if (index + length > string1.length() || index + length > string2.length())
            return false;
        return (string1.substring(index, index + length)).equals(string2.substring(index, index + length));
    }

    public static boolean isEqualAfterReplaceCharacters(String string1, char replaceInStr1,
                                                        char replaceByInStr1, String string2,
                                                        char replaceInStr2, char replaceByInStr2){
        return string1.replace(replaceInStr1, replaceByInStr1).
                equals(string2.replace(replaceInStr2, replaceByInStr2));
    }

    public static boolean isEqualAfterReplaceStrings(String string1, String replaceInStr1,
                                                     String replaceByInStr1, String string2,
                                                     String replaceInStr2, String replaceByInStr2) {
        return string1.replaceAll(replaceInStr1, replaceByInStr1).
                equals(string2.replaceAll(replaceInStr2, replaceByInStr2));
    }

    public static boolean isPalindromeAfterRemovingSpacesIgnoreCase(String string){
        return isPalindromeIgnoreCase(string.replace(" ", ""));
    }

    public static boolean isEqualAfterTrimming(String string1, String string2){
        return string1.trim().equals(string2.trim());
    }

    public static String makeCsvStringFromInts(int[] array){
        String result = "";
        for (int i = 0; i < array.length; i++) {
            result += array[i];
            if (i != array.length - 1) result += ",";
        }
        return result;
    }

    public static String makeCsvStringFromDoubles(double[] array){
        String result = "";
        for (int i = 0; i < array.length; i++) {
            result += new DecimalFormat("#0.00").format(array[i]);
            if (i != array.length - 1) result += ",";
        }
        return result;
    }

    public static StringBuilder makeCsvStringBuilderFromInts(int[] array){
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < array.length; i++){
            result.append(array[i]);
            if (i != array.length - 1) result.append(',');
        }
        return result;
    }

    public static StringBuilder makeCsvStringBuilderFromDoubles(double[] array){
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            result.append(new DecimalFormat("#0.00").format(array[i]));
            if (i != array.length - 1) result.append(',');
        }
        return result;
    }

    public static StringBuilder removeCharacters(String string, int[] positions){
        StringBuilder result = new StringBuilder();
        int iArr = 0;
        for (int i = 0; i < string.length(); i++){
            if (iArr == positions.length || i != positions[iArr]) {
                result.append(string.charAt(i));
            } else {
                iArr++;
            }
        }
        return result;
    }

    public static StringBuilder insertCharacters(String string, int[] positions, char[] characters){
        StringBuilder result = new StringBuilder();
        int iArr = 0;
        for (int i = 0; i < string.length();){
            if (iArr != positions.length && i == positions[iArr]) {
                result.append(characters[iArr]);
                iArr++;
            } else {
                result.append(string.charAt(i));
                i++;
            }
        }
        return result;
    }
}

