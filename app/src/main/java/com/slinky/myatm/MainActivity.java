package com.slinky.myatm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * created by Francis Sauve for CCCS-325-764: Assignment 1
 */
public class MainActivity extends AppCompatActivity {
    private Button withdraw_btn;          //btn
    private Button deposit_btn;
    private TextView moneyInHand;             //TV
    private TextView account;
    private Spinner spin;                 //spinner
    private int transaction = 1000;       //transaction being the requested amount when clicked

    Strategy d = new Deposit(), w = new Withdraw();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initIDs();                 // init IDs for buttons and values
        initSpinner();
        setClickListeners();
        setSelectListener();
    }
    /*=============================== INITIALIZATION METHODS =================================*/

    /**
     * this method initialize the IDs.<br> Refactored out to keep onCreate clean.
     */
    protected void initIDs() {
        withdraw_btn = findViewById(R.id.withdraw);           //buttons ids
        deposit_btn = findViewById(R.id.deposit);
        moneyInHand = findViewById(R.id.moneyInHandID);               // money IDs
        account = findViewById(R.id.accountID);
    }

    /**
     * this method initialize the spinner.<br> Refactored out to keep onCreate clean.
     * <br><br>
     * code taken from powerpoint of the class.
     */
    protected void initSpinner() {
        spin = findViewById(R.id.StackSelector);

        ArrayAdapter<CharSequence> stackAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.stackSelector,
                android.R.layout.simple_spinner_item
        );

        stackAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(stackAdapter);

//        stackSpinner.getSelectedItem();
//        stackSpinner.setSelection(0);
    }


    /*================================== LISTENER METHODS =====================================*/

    /**
     * set up click/tap listeners
     */
    private void setClickListeners() {
        deposit_btn.setOnClickListener(v -> onClickDeposit());
        withdraw_btn.setOnClickListener(v -> onClickingWithdraw());
    }

    /**
     * set up select listeners
     * <br> setOnSelectedListener inspired from in-class lecture.
     */
    private void setSelectListener() {
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Spinner MyApp", spin.getItemAtPosition(position).toString()); //test log

                String selected = spin.getItemAtPosition(position).toString();
                transaction = Integer.parseInt(selected);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //do nothing
            }
        });
    }

    /*================================== UTILITY METHODS =====================================*/

    /**
     * The code to be run when <b>DEPOSIT</b> is tapped.
     * <br> the logic here is simply reversed from withdraw
     */
    public void onClickDeposit() {
        int handVal = getIntFromTV(moneyInHand), accountVal = getIntFromTV(account);

        if (handVal >= transaction) {
            moneyInHand.setText(getStrFromLogic(w, handVal));
            account.setText(getStrFromLogic(d, accountVal));
        } else {
            //error message
            Toast.makeText(this, "Not enough money! Get a job to deposit more!", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * The code to be run when <b>WITHDRAW</b> is tapped.
     * <br> the logic here is simply reversed from deposits
     */
    public void onClickingWithdraw() {
        int accountVal = getIntFromTV(account), handVal = getIntFromTV(moneyInHand);

        if (accountVal >= transaction) {
            account.setText(getStrFromLogic(w, accountVal));
            moneyInHand.setText(getStrFromLogic(d, handVal));
        } else {
            // error message
            Toast.makeText(this, "Not enough money! Don't steal from us!", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     *
     * @param tv is TextView being the balance of either hand or account TextView string values.
     * @return integer value of the balance of either hand or account TextView string values.
     */
    private int getIntFromTV(TextView tv) {
        return Integer.parseInt(tv.getText().toString());
    }

    /**
     *
     * @param s being Strategy Pattern instance needed for transaction being processed.
     * @param val   being account balance
     * @return after performing respective math operation, a string to set on respective TextView item.
     */
    private String getStrFromLogic(Strategy s, int val) {
        return s.doLogic(val, transaction) + "";
    }


    /*============================ OBSOLETE CODE AFTER REFACTORING =========================*/


//    private String getStrFromWithdraw(Withdraw w, int i) {
//        return w.doLogic(i, threshold) + "";
//    }


//    public void onClickingWithdraw() {
//        Withdraw w = new Withdraw();
//        Deposit d = new Deposit();
////        String s = val_bankAmt.getText().toString();
////
//        int i = Integer.parseInt(s);
////        String s2 = val_withdrawn.getText().toString();
////
////        int i2 = Integer.parseInt(s2);
//        if (i>=threshold) {
////
////            int result = w.doLogic(i, threshold);
////            String resultS = result + "";
////            val_bankAmt.setText(resultS);
////
////            int result2 = i2 + threshold;
////            String resultS2 = result2 + "";
////            val_withdrawn.setText(resultS2);
//            w = null;
//
//        }
//    }




//    public void onClickDeposit() {
//        Deposit d = new Deposit();
//        String s = val_withdrawn.getText().toString();
//        int i = Integer.parseInt(s);
//        String s2 = val_bankAmt.getText().toString();
//        int i2 = Integer.parseInt(s2);
//        if (i>= threshold) {
//            int result = d.doLogic(i, threshold);
//            String resultS = result + "";
//            val_withdrawn.setText(resultS);
//            int result2 = i2 + threshold;
//            String resultS2 = result2 + "";
//            val_bankAmt.setText(resultS2);
//            d = null;
////            val_withdrawn.setText(depositing.doLogic(i, threshold));
////            String s2 = val_bankAmt.getText().toString();
////            int i2 = Integer.parseInt(s2);
////            val_bankAmt.setText(i2 + threshold);
//        }
//    }





//protected void initText() {
//    withdraw_btn = findViewById(R.id.withdraw);                 //butns ids
//    deposit_btn = findViewById(R.id.deposit);
////        tv_account = findViewById(R.id.account_tv);                 //textView ids
////        tv_min = findViewById(R.id.min_tv);
//    val_withdrawn = findViewById(R.id.min_digit);               //default amts ids
//    val_bankAmt = findViewById(R.id.amt_digit);
////
////        tv_account.setText(R.string.account);                       //textviewStatics
////        tv_min.setText(R.string.moneyHand);
////        deposit_btn.setText(R.string.depositBtn);
////        withdraw_btn.setText(R.string.withdrawBtn);
//
////        val_bankAmt.setText(R.string.bankAmt);                    //defaults amts
////        val_withdrawn.setText(R.string.handAmt);
//
//}
}