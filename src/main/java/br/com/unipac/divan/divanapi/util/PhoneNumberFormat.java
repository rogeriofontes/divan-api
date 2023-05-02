package br.com.unipac.divan.divanapi.util;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberFormat {

    public static void main(String[] args) {
        //String REGEX = "\\(\\d{2,}\\) \\d{4,}\\-\\d{4}";
        //String REGEX = "^\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}$";
        //String REGEX = "^[A-Z]{2}$";

        //String REGEX = "^(\\d{2}\\x2E\\d{3}\\x2E\\d{3}[-]\\d{1})$|^(\\d{2}\\x2E\\d{3}\\x2E\\d{3})$";

        String REGEX = "^\\d{2}\\.\\d{3}\\.\\d{3}\\/\\d{4}\\-\\d{2}$";

        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher("16.628.602/0001-28");
        //Matcher matcher = pattern.matcher("12.123.123");
        //Matcher matcher = pattern.matcher("12.123.123-1");
        //Matcher matcher = pattern.matcher("MG");
        //Matcher matcher = pattern.matcher("603.548.600-20");
        //Matcher matcher = pattern.matcher("(34) 99203-1938");

        boolean matchFound = matcher.find();
        if(matchFound) {
            System.out.println("Match found");
        } else {
            System.out.println("Match not found");
        }
    }
}
