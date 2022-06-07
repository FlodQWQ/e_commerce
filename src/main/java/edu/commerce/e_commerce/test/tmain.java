package edu.commerce.e_commerce.test;

import java.util.ArrayList;
import java.util.List;

public class tmain {
    public static void main(String[] args) {
        String comma="";
        StringBuilder allGenres = new StringBuilder();
        List<String> genre = new ArrayList<>();
        for (String g: genre) {
            allGenres.append(comma);
            allGenres.append(g);
            comma = ",";
        }
        String tmp = allGenres.toString();
        System.out.println(tmp);

    }
}
