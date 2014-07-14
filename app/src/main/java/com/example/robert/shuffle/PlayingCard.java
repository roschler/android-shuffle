package com.example.robert.shuffle;

/**
 * Created by Robert on 7/13/2014. (c) 2014, Android Technologies, Inc.
 *  All rights reserved.
 */
// This object represents a single playing card in a standard deck of cards.
public class PlayingCard {

    // --------------------------- ENUM --------------------------------------------
    public enum Ranks { Ace, Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King }
    public enum Suits{ Clubs, Diamonds, Hearts, Spades }

    // --------------------------- CLASS DATA MEMBERS ------------------------------
    private Ranks rank;
    private Suits suit;

    // --------------------------- PROPERTY GETTERS/SETTERS ------------------------

    // The Rank for the playing card.
    public Ranks getRank() {
        return rank;
    }

    public void setRank(Ranks rank) {
        this.rank = rank;
    }

    // The suit for the playing card.
    public Suits getSuit() {
        return suit;
    }

    public void setSuit(Suits suit) {
        this.suit = suit;
    }

    // --------------------------- METHODS ----------------------------------------

    // Create a playing card with the given rank & suit.
    public PlayingCard(Ranks rank, Suits suit) {
        super();

        this.rank = rank;
        this.suit = suit;
    }
}
