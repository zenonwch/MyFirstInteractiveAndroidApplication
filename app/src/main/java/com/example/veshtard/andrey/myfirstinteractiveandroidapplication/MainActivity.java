package com.example.veshtard.andrey.myfirstinteractiveandroidapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.Locale;

import static java.lang.String.format;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    private int quantity;
    private int price;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(final int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(final int price) {
        this.price = price;
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setQuantity(1);
        setPrice(5);
        setContentView(R.layout.activity_main);
        display(quantity);
        displayMessage(format(Locale.getDefault(), "$%d", price));
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(final View view) {
        display(getQuantity());
        final CheckBox cbWhippedCream = (CheckBox) findViewById(R.id.whipped_cream_check_box);
        final CheckBox cbChocolate = (CheckBox) findViewById(R.id.chocolate_check_box);
        final int total = getQuantity() * getPrice();
        final StringBuilder sb = new StringBuilder("");
        final String lineSeparator = System.getProperty("line.separator");

        if (cbWhippedCream.isChecked()) sb.append(format("Add whipped cream%s", lineSeparator));
        if (cbChocolate.isChecked()) sb.append(format("Add chocolate%s", lineSeparator));

        sb.append(format(Locale.getDefault(), "Quantity: %d%s", quantity, lineSeparator));
        sb.append(format(Locale.getDefault(), "Total: $%d%s", total, lineSeparator));
        sb.append("Thank You!" );

        displayMessage(sb.toString());
    }

    public void increment(final View view) {
        setQuantity(getQuantity() + 1);
        display(getQuantity());
    }

    public void decrement(final View view) {
        if (getQuantity() > 0) {
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