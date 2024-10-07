package BlackJack;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BlackJack {


    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);

        while (true) {
            System.out.println("\nWelcome to BlackJack, would you like to play? Enter (Yes) to play, and enter (No) to not play."); //Initial ask for player interaction.
            String check = keyboard.nextLine();
            check = check.toUpperCase(); //Allows for submission of yes, yEs, YES, etc.
            if (check.equals("YES")) {


                Deck deck = new Deck(); //Deck is created, and then shuffled.
                deck.shuffle();
                Hand Player = new Hand("Player", deck); //Generation of player hand.
                Hand Dealer = new Hand("Dealer", deck); //Generation of dealer hand.

               System.out.println("You have been dealt.\n");

               Player.getHandCardsPlayer();
               Player.getHandValue();

                Dealer.getHandCardsDealer();


                //PLAYER's TURN
                while (true) { //this is the Player's turn.
                    System.out.println("\nWould you like to hit, or stand?");
                    String checkPlayer = keyboard.nextLine();
                    checkPlayer = checkPlayer.toUpperCase(); //conversion for capitalization within hit.
                    if (checkPlayer.equals("HIT")) {

                        Player.hit(deck); //Add a card.
                        Player.getHandCardsPlayer(); //Returns all cards (including the new one)
                        Player.getHandValue(); //Returns value of cards (accounting for aces, difference in rank, etc.)

                        if (Player.bustCheck() == true) { break; } //Automatically forfeits any possible moves upon busting.

                    }

                    if (checkPlayer.equals("STAND")) { break; } //Ends player's turn, as they've stood.
                } //End of Player while-loop/turn



                //DEALER's TURN
                while (true)
                {
                    if (Dealer.getHandValueDealer() < 17) //"Dealer hits under 17"
                    {
                        Dealer.hit(deck);
                        System.out.print("\nThe Dealer HITS!");
                        Dealer.checkAce(); //Changes ace value if necessary to prevent busting.

                        if (Dealer.bustCheck() == true) //Break the loop upon bust.
                        {
                            System.out.print("\nThe Dealer BUSTED!");
                            break;
                        }

                        if ((Dealer.getHandValueDealer() >= 17) && (Dealer.getHandValueDealer() < 22)) //"Dealer stands on 17-21"
                        {

                            break;
                        }
                    }

                    if ((Dealer.getHandValueDealer() >= 17) && (Dealer.getHandValueDealer() < 22)) //"Dealer stands on 17-21"
                    {

                        break;
                    }

                    if (Dealer.getHandValueDealer() > 21) //Dealer is in a bust position.
                    {
                        Dealer.checkAce(); //Check to see if an Ace can be changed from 11 to 1.
                        if (Dealer.bustCheck() == true) //If still over 21, then full bust.
                        {
                            System.out.print("\nThe Dealer BUSTED!");
                            break;
                        }

                        if ((Dealer.getValueDealer2() >= 17) && (Dealer.getValueDealer2() < 22)) //If AceCheck creates a situation where the dealer can stand, he will.
                        {

                            break;
                        }

                        if (Dealer.getValueDealer2() < 17) //If AceCheck lead to a value under 17.
                        {
                            Dealer.hit(deck);
                            System.out.print("\nThe Dealer HITS!");
                            Dealer.checkAce(); //If dealer lands another ace, and is in a bust position.
                            if (Dealer.bustCheck() == true) //Fails second AceCheck, otherwise, starts loop from beginning.
                            {
                                System.out.print("\nThe Dealer BUSTED!");
                                break;
                            }
                        }

                    }





                }//End of Dealer's turn.

                //SCORING
                System.out.println("\nYour hand value is "+Player.getHandValueFinalPlayer()); //Statement of both scores.
                System.out.println("The Dealer's hand value is "+Dealer.getValueDealer2());
                if(Player.getHandValueFinalPlayer() == 21) //Congratulatory remark upon either entity landing a BJ.
                {
                    System.out.println("BLACKJACK FOR PLAYER!");
                }

                if(Dealer.getValueDealer2() == 21)
                {
                    System.out.println("BLACKJACK FOR DEALER!");
                }

                if((Player.bustCheck() == true)&&(Dealer.bustCheck()== true)) //Both Bust, Dealer wins.
                {
                    System.out.println("You both BUSTED. Dealer wins.");
                }

                if((Player.bustCheck() == true)&&(Dealer.bustCheck()== false)) //Player Bust, Dealer wins.
                {
                    System.out.println("You BUSTED. Dealer wins.");
                }

                if((Player.bustCheck() == false)&&(Dealer.bustCheck()== true)) //Dealer Bust, Player wins.
                {
                    System.out.println("Dealer BUSTED. You win.");
                }


                if((Player.bustCheck() == false)&&(Dealer.bustCheck()== false)) //Neither bust, Score checked.
                {
                    if(Player.getHandValueFinalPlayer() > Dealer.getValueDealer2()) //Dealer wins ties. Player must have score greater than dealer to win.
                    {
                        System.out.println("Player wins. Congratulations!");
                    }
                    else
                    {
                        System.out.println("Dealer wins. Sorry!");
                    }
                }

            } //end of if(yes)

            if (check.equals("NO")) //Denial of play.
            {
                System.out.println("\nOk."); //Cheeky remark.
                break;
            }
        } //end of while(true), and executable code.
    }
}

