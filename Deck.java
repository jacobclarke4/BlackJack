package BlackJack;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
    private ArrayList<Card> deck;

    Deck(){
        deck = new ArrayList<Card>();
        for(int x = 0; x < 4; x++){
            for(int y = 1; y < 13; y++){
                deck.add(new Card( x, y));
            } // end for loop 2
        }//end for loop 1
    } //end of Deck()

    public void shuffle(){
        Random random = new Random();
        Card place_holder;
        for(int x = 0; x < 500; x++){
            int place_1 = random.nextInt(deck.size()-1);
            int place_2 = random.nextInt(deck.size() - 1);
            place_holder = deck.get(place_2);
            deck.set(place_2, deck.get(place_1));
            deck.set(place_1, place_holder);
        }//end of for loop
    }//end of shuffling

    public Card draw(){
        return deck.remove(0);
    }



}//end of class Deck
