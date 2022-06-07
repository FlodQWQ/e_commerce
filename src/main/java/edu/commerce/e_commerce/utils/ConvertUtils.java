package edu.commerce.e_commerce.utils;

import java.util.ArrayList;
import java.util.List;

public class ConvertUtils {
    public static List<Integer> StringtoList(String strs) {
        List<Integer> list = new ArrayList<>();
        if(strs.equals("")){
            return list;
        }
        String[] results = strs.split(",");
        for (String str : results) {
            list.add(Integer.parseInt(str));
        }
        return list;
    }
    public static String ListtoString(List<Integer> list){
        if(list.size()==0){
            return "";
        }
        String comma="";
        StringBuilder allGenres = new StringBuilder();
        for(int num:list){
            allGenres.append(comma);
            allGenres.append(Integer.toString(num));
            comma = ",";
        }
        return allGenres.toString();
    }

    public static String htmlStringtoString(String str){
        str = str.replace("[","");
        str = str.replace("]","");
        return str;
    }

    public static String delPoundSign(String str){
        str = str.replace("#","");
        return str;
    }
}
