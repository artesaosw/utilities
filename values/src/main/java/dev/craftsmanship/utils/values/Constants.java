package dev.craftsmanship.utils.values;

public class Constants {

    private static final char FIRST_UTF16_CHARACTER = (char) 0x000000;
    private static final char LAST_UTF16_CHARACTER = (char) 0x2F9F04;

    public static final String MIN_STRING = String.valueOf(FIRST_UTF16_CHARACTER);
    public static final String MAX_STRING = String.valueOf(LAST_UTF16_CHARACTER).repeat(5000);
}
