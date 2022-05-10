package com.slinky.myatm;

public interface Strategy {
    /**
     * each implementation will implement their own code for the atm.
     * <br> this should be s mathematics operation only.
     * <br> specifically, a click on either Deposit or Withdraw will
     * use both a deposit AND withdraw mathematics operations, just
     * in opposite logic.
     * <br><br> <b>ex: withdraw 100$ from account of 1000$ balance requires:</b> <br>
     * <li>removal of 100$ from account balance</li>
     * <li>adding 100$ to hand (wallet) of client</li>
     *
     * @param val is value that was parsed into integer.
     * @param transaction is transaction amount to process.
     * @return value to send to client
     */
    int doLogic(int val, int transaction);
}
