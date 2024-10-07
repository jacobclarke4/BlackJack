package BlackJack;

import java.util.Scanner;

public class Card {
    private int rank;
    private int suit;
    private int value;
    private static String[] ranks = {null, "Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King", "Ace"};
    private static String[] suits = {"Spades", "Clubs", "Diamonds", "Hearts"};

    public Card(int suit, int values){
        this.suit = suit;
        this.rank = values;
    }

    public int getRank(){
        return rank;
    } // end of getRank

    public int getSuit(){
        return suit;
    } // end of getSuit

    public int getValue(){
        if(rank <= 10){
            value = rank;
        }

        if(rank == 1){
            System.out.println("Would you like the Ace of " + suits[suit] + " to have a value of 1 or 11?");
            Scanner keyboard = new Scanner(System.in);
            while(true){
                value = keyboard.nextInt();
                if((value == 1) || (value == 11)){
                    break;
                }
                else
                    System.out.println("Your input was invalid. Input either 1 or 11.");
            }
        } // sets the value of ace to 1 or 11

        if(rank > 10){
            value = 10;
        }
        return value;
    } // end of getValue



    public int getValueDealer(){ //in progress
        if(rank <= 10){
            value = rank;
        }

        if(rank == 1)
        {
            value = 11;
        } // sets the value of ace to 1 or 11

        if(rank > 10){
            value = 10;
        }
        return value;
    } // end of getValueDealer



    public String toString(){

        return ranks[rank] + " of " + suits[suit];

    } // end of toString

}//end of class Card
