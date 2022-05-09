package com.slinky.myatm;

/**
 * Withdraw  operation for the Strategy.
 * <br>A better name would be Remove since it caters to removal of transaction from the balance.
 */
public class Withdraw implements Strategy {
    @Override
    public int doLogic(int val, int transaction) {
        return val - transaction;
    }

//    public static String addVal200(CharSequence c) {
//        String result = "";
//
//        result += Integer.parseInt(c.toString()) + 200;
//        return result;
//    }
}
