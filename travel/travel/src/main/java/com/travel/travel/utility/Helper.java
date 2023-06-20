package com.travel.travel.utility;

public class Helper {
    public static String getAplphaNumericOrderId(){
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz"
                + "АБВГДЕЁЖЗИЙКЛМНОПРСТФХУЦЧШЩЫЬЪЭЮЯ"
                + "абвгдеёжзийклмнопрстуфхцчшщыьъэюя";
        StringBuilder sb = new StringBuilder(10);
        for (int i=0;i<10;i++){
            int index = (int)(AlphaNumericString.length()*Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }
        return sb.toString().toUpperCase();
    }
}
