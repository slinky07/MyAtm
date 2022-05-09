package com.slinky.myatm;

/**
 * Deposit operation for the Strategy.
 * <br>A better name would be Add since it caters to adding transaction into the balance.
 */
public class Deposit implements Strategy {

    @Override
    public int doLogic(int val, int transaction) {
        return val + transaction;
    }
}
