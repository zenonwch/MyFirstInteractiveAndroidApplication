package com.example.veshtard.andrey.myfirstinteractiveandroidapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import static java.lang.String.format;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    private int quantity;
    private int priceCoffee;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(final int quantity) {
        this.quantity = quantity;
    }

    public int getPriceCoffee() {
        return priceCoffee;
    }

    public void setPriceCoffee(final int priceCoffee) {
        this.priceCoffee = priceCoffee;
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setQuantity(1);
        setPriceCoffee(5);
        setContentView(R.layout.activity_main);
        display(quantity);
        displayMessage(format(Locale.getDefault(), "$%d", priceCoffee));
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(final View view) {
        display(getQuantity());
        final CheckBox cbWhippedCream = (CheckBox) findViewById(R.id.whipped_cream_check_box);
        final CheckBox cbChocolate = (CheckBox) findViewById(R.id.chocolate_check_box);
        final StringBuilder sb = new StringBuilder("");
        final String lineSeparator = System.getProperty("line.separator");

        final EditText editTextUserName = (EditText) findViewById(R.id.edit_user_name);

        if (editTextUserName != null) {
            final String userName = editTextUserName.getText().toString().trim();

            if (userName.isEmpty()) {
                Toast.makeText(this, "Please fill in your name", Toast.LENGTH_SHORT).show();
                return;
            }

            sb.append(format("Order for %s%s", userName, lineSeparator));
        }

        sb.append(format(Locale.getDefault(), "Quantity: %d%s", quantity, lineSeparator));

        int priceToppings = 0;
        if (cbWhippedCream.isChecked()) {
            priceToppings += 1;
            sb.append(format("with whipped cream%s", lineSeparator));
        }
        if (cbChocolate.isChecked()) {
            priceToppings += 2;
            sb.append(format("with chocolate%s", lineSeparator));
        }

        sb.append(lineSeparator);

        final int total = quantity * (priceCoffee + priceToppings);
        sb.append(format(Locale.getDefault(), "Total: $%d%s", total, lineSeparator));
        sb.append("Thank You!");

        displayMessage(sb.toString());
    }

    public void increment(final View view) {
        if (getQuantity() < 10) {
            setQuantity(getQuantity() + 1);
        } else {
            Toast.makeText(this, "Max available cups for one order is 10", Toast.LENGTH_SHORT).show();
        }
        display(getQuantity());
    }

    public void decrement(final View view) {
        if (getQuantity() > 1) {
            setQuantity(getQuantity() - 1);
        }
        display(getQuantity());
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(final int number) {
        final TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText(format(Locale.getDefault(), "%d", number));
    }

    /**
     * This method displays messages
     */
    private void displayMessage(final String message) {
        final TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

}