package com.example.robert.shuffle;

/**
 * Created by Robert on 7/13/2014. (c) 2014, Android Technologies, Inc.
 * All rights reserved.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// This class represents a standard deck of playing cards.
public class DeckOfCards {

    // --------------------------- CLASS DATA MEMBERS ------------------------------

    // The container for the deck of cards.
    protected List<PlayingCard> listPlayingCards = new ArrayList<PlayingCard>();

    // --------------------------- PROPERTY GETTERS/SETTERS ------------------------

    public List<PlayingCard> getListPlayingCards() {
        return listPlayingCards;
    }

    public void setListPlayingCards(List<PlayingCard> listPlayingCards) {
        this.listPlayingCards = listPlayingCards;
    }

    // --------------------------- METHODS ----------------------------------------

    public DeckOfCards() {
        super();

        // Build the deck of cards.

        // Iterate the available Suits.
        for (PlayingCard.Suits suit : PlayingCard.Suits.values()) {
            // Iterate the available Ranks.
            for (PlayingCard.Ranks rank : PlayingCard.Ranks.values()) {
                this.listPlayingCards.add(new PlayingCard(rank, suit));
            }
        }
    } // public DeckOfCards() {)

    // Shuffles a list of type T using a swap technique involving randomly selected index pairs
    //  to ensure a healthy dispersion of the list elements.  Returns a new list with the
    //  original list contents shuffled.
    public void shuffleDeck() throws NullPointerException {
        if (this.listPlayingCards == null)
            throw new NullPointerException("The deck of cards is unassigned.");

        if (this.listPlayingCards.size() < 2)
            return; // Nothing to shuffle.

        // Holding cell for use during an item swap between index locations.
        PlayingCard tempObj;

        // Pair of indices to use during a swap operation.
        int ndx1, ndx2;

        // Use the square of the length of the array to ensure a decent dispersal.
        for (int i = 0; i < this.listPlayingCards.size() * this.listPlayingCards.size(); i++)
        {
            Random rnd = new Random();

            // Select two indices randomly that are within the range of the list.
            ndx1 = rnd.nextInt(this.listPlayingCards.size());
            ndx2 = rnd.nextInt(this.listPlayingCards.size());

            // If the indices are equal, increment the second one.
            if (ndx1 == ndx2)
                ndx2++;

            // If the second index is beyond the array, wrap around to the start.
            if (ndx2 >= this.listPlayingCards.size())
                ndx2 = 0;

            // Do the swap.
            tempObj = this.listPlayingCards.get(ndx1);
            this.listPlayingCards.set(ndx1, this.listPlayingCards.get(ndx2));
            this.listPlayingCards.set(ndx2, tempObj);
        } // for()

        return; // The deck is now shuffled
    } // public static void Shuffle()

    // Takes a card from the top of the deck and returns it to the caller.  Returns NULL
    //  if the deck is empty.
    public PlayingCard dealOneCard() {

        PlayingCard card = null;

        if (this.listPlayingCards.size() > 0) {
            card = this.listPlayingCards.get(0);

            this.listPlayingCards.remove(0);
        } // if (this.listPlayingCards.size() > 0)

        return card;
    }
}
