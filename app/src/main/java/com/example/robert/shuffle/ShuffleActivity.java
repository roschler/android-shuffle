package com.example.robert.shuffle;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ShuffleActivity extends Activity {

    // --------------------------- CLASS DATA MEMBERS ------------------------------

    // Deck of playing cards.
    DeckOfCards deckOfCards = new DeckOfCards();

    // --------------------------- METHODS ----------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shuffle);

        this.updateCardCount();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.shuffle, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Pops up a message box with the given message and an OK button to continue.
    private void easyMessageBox(String msg)
    {
        final AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);

        // Build the alert dialog.
        dlgAlert.setMessage(msg);
        dlgAlert.setTitle("Info");
        // OK button with event handler.
        dlgAlert.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Dismiss the dialog.
                        dialog.dismiss();
                    }
                }
        );
        dlgAlert.setCancelable(true);

        // Show it.
        dlgAlert.create().show();
    }

    // Adds a new line to the status messages box.
    private void addStatusMessage(String str)
    {
        TextView statusMessages = (TextView) findViewById(R.id.status_messages);

        statusMessages.setText(statusMessages.getText() + "\n" + str);
    }

    // Clears the contents of the status messages box.
    private void clearStatusMessages()
    {
        TextView statusMessages = (TextView) findViewById(R.id.status_messages);

        statusMessages.setText("");
    }


    // Returns all dealt cards to the playing deck and then shuffles them.
    public void onShuffleButtonClicked(View view) {
        // Create a new instance of the deck of cards to start with a fresh new deck.
        this.deckOfCards = new DeckOfCards();

        // Shuffle the deck.
        this.deckOfCards.shuffleDeck();

        // Clear the status messages, new deal.
        this.clearStatusMessages();

        this.addStatusMessage("The cards have been shuffled.");

        // Update the cards available text element.
        this.updateCardCount();
    }

    // Updates the card count text element with the current count of cards remaining in the deck.
    private void updateCardCount() {
        String str = getString(R.string.cards_available) + Integer.toString(this.deckOfCards.getListPlayingCards().size());
        TextView cardsAvailable = (TextView) findViewById(R.id.cards_available_text);
        cardsAvailable.setText(str);
    }

    // Deals one card from the top of the deck and removes that card from the deck.
    public void onDealButtonClicked(View view) {
        // Get a card from the top of the deck.
        PlayingCard card = this.deckOfCards.dealOneCard();

        if (card == null)
        {
            // All cards have been dealt, empty deck.  Tell the user to shuffle the deck.
            this.easyMessageBox("All cards have been dealt.  Shuffle the deck to start over.");
            return;
        }

        // Tell the user what card they were dealt.
        String str = String.format("You were dealt the %s of %s.", card.getRank().toString(), card.getSuit().toString());
        this.addStatusMessage(str);

        // Update the cards available text element.
        this.updateCardCount();
    }
}
