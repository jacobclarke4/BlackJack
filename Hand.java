package BlackJack;
import MarriageTest.L9Person;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private boolean bust = false; //Bust boolean for scoring and code breaking within game.
    private int value, valueDealer, valuePlayer; //value is of card value, used within value calculations, the other two are hand-specific for specific functions as to prevent overwrite.
    private String handName;
    private ArrayList<Card> handCards = new ArrayList<>();; //Hand is an arrayList of cards.


    public Hand(String name, Deck deck) //Dealer and Player will be named.
    {
        List<Card> newHand = new ArrayList<>(); //Declaration of hand arrayList, two cards are announced to the player.

            handCards.add(deck.draw()); //Draws a card to the hand.
            handCards.add(deck.draw());

            handName = name; //Declares name of hand (Player, Dealer).

    }

    public void hit(Deck deck) { handCards.add(deck.draw()); } //Draws card from the deck, adds it to the specified hand.

    public void getHandCardsPlayer()
    {
        System.out.println(handCards);
    } //Lists cards in Player's hand.

    public void getHandCardsDealer() { System.out.println("\nThe Dealer's shown card is the " +handCards.get(0)); } //Only outputs the first card visible by the dealer.

    public boolean bustCheck() { return bust; } //Allows for bust mechanics, boolean just to be able to output a value of the bust condition.

    public void getHandValue()
    {
        for(int x = 0; x < handCards.size(); x++) //Loops through each array item, checks it's value, then adds it to the total.
        {

            Card card = handCards.get(x);
            value = value + card.getValue(); //Upon Ace, choice is offered to player to decide. If they decide 11 and bust, it's their own fault.
            valuePlayer = value;
        }
        if(value > 21) //Bust condition
        {
            System.out.print("The value of your cards is "+value+"\nSorry, you BUSTED!\n");
            bust = true;
        }
        else
            {
            System.out.print("\nThe value of your cards is " + value);
            value = 0; //Sets the card value back to 0, as to not incur a running sum != to actual hand value.
            }
    }


    public int getHandValueFinalPlayer() { return valuePlayer; } //Gives final hand value at end of game.



    public int getHandValueDealer(){
        valueDealer = 0; //Prevents running sum total, "washes" value from previous calculation.
        for(int x = 0; x < handCards.size(); x++)
        {

            Card card = handCards.get(x);
            valueDealer = valueDealer + card.getValueDealer(); //Sum of values of cards in hand.
        }

        return valueDealer; //Saves card total to be extrapolated if no other actions are performed. Will reset to 0 if method is called again.

    }

    public int getValueDealer2() { return valueDealer; } //Final hand value for dealer at end of game.


    public void checkAce() //Dealer specific, if the dealer is over 21, but has an ace, checks to see if it can assign them to value of 1 instead.
    {
        for(int x = 0; x < handCards.size(); x++)
        {
            Card card = handCards.get(x);

            if(card.getRank()== 1) //Ace is flagged.
            {
                if(valueDealer > 21) //In pre-bust state. If one ace is 'good enough', the next ace, etc. will not be decreased in value.
                {
                    valueDealer = valueDealer - 10; //Change from 11 to 1.
                }


            }

        }
        if(valueDealer > 21) //All aces have been reduced, but still in pre-bust state (vale over 21). Actual BUST state is now set to true.
        {
            bust = true;

        }




    }

}
