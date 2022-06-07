package edu.commerce.e_commerce.utils;

import edu.commerce.e_commerce.bean.Commodity;

import static edu.commerce.e_commerce.utils.ConvertUtils.ListtoString;

public class EqualUtils {
    public static boolean isCommodityEqual(Commodity commodity1,Commodity commodity2){
        return commodity1.getCommodity_name().equals(commodity2.getCommodity_name()) &&
                commodity1.getCommodity_price() == commodity2.getCommodity_price() &&
                ListtoString(commodity1.getCommodity_sellers()).equals(ListtoString(commodity2.getCommodity_sellers()));
    }
}
