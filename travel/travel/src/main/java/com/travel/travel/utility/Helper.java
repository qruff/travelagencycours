package com.travel.travel.utility;

public class Helper {
    public static String getAplphaNumericOrderId(){
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789";
        StringBuilder sb = new StringBuilder(8);
        for (int i=0;i<8;i++){
            int index = (int)(AlphaNumericString.length()*Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }
        return sb.toString().toUpperCase();
    }
}
