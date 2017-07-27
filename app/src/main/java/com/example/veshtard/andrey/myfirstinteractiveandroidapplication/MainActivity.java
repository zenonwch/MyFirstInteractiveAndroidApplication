package com.example.veshtard.andrey.myfirstinteractiveandroidapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;
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
        displayPrice(price);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(final View view) {
        display(getQuantity());
        final int total = getQuantity() * getPrice();
        displayMessage("Total: " + NumberFormat.getCurrencyInstance().format(total));
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
     * This method displays the given price on the screen.
     */
    private void displayPrice(final int number) {
        final TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(final int number) {
        final TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText(format(Locale.getDefault(), "%d", number));
    }

    /**
     * This method
     */
    private void displayMessage(final String message) {
        final TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }

}