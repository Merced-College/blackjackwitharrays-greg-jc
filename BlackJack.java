/* Names: Erandy Aguilar Perez, Jesus Coria, Richard Manzo
*  Date: 01/21/25
*  Assignment: To update BlackJack (10-20 lines)
*/

import java.util.Random; 
import java.util.Scanner;

public class BlackJack {
    //constants - cannot change their values
    //static - I can use these in every function without having to pass them in 
    private static final String[] SUITS = { "Hearts", "Diamonds", "Clubs", "Spades" }; 
    // SUITS and RANKS are both declared arrays, they're both final and private so they cannot be changed after initialization or be accessed outside the class. 
    private static final String[] RANKS = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King",
            "Ace" };
    private static final int[] DECK = new int[52]; //DECK is an integer array with up to 52 elements. 
    private static int currentCardIndex = 0; //The variable currentCardIndex can only be accessed within the class and is initialized to zero. 


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        //The following two methods are being called, meaning the program will execute the code within each method. 
        initializeDeck();
        shuffleDeck(); 
        //Two integer variables are declared and initialized to the methods, these methods are expected to return a value, the returned value is then assigned to the two variables.  
        int playerTotal = dealInitialPlayerCards();
        int dealerTotal = dealInitialDealerCards();
        
        //This playerTotal variable returns an integer value from the method playerTurn depending on the input from scanner and the added number to the new total value. 
        playerTotal = playerTurn(scanner, playerTotal);
        //If playerTotal is Greater than 21 then the following System.out.println is printed out and the program terminates. 
        if (playerTotal > 21) {
            System.out.println("You busted! Dealer wins.");
            return;
        }
        
        dealerTotal = dealerTurn(dealerTotal); //The dealerTurn method is called with the current value of dealerTotal as an input. The current value is then used to execute the code within. 
        
        determineWinner(playerTotal, dealerTotal); //The determineWinner method is called using the current values of playerTotal and dealerTotal to use within the method's code.
        //closes the Scanner object. 
        scanner.close();
    }
    //assigning each element in the DECK array a value from 0 to 51
    private static void initializeDeck() {
        for (int i = 0; i < DECK.length; i++) {
            DECK[i] = i; //assigning value of current index to represent a specific card
        }
    }
    //shuffles the deck randomly by swapping the position of cards in the Deck array
    private static void shuffleDeck() {
        Random random = new Random();
        //looping through deck while swapping each card with another randomly chosen card
        for (int i = 0; i < DECK.length; i++) {
            int index = random.nextInt(DECK.length); //generate random index within DECK's range

            //The following 3 lines below are using the swap algorithm. 
            int temp = DECK[i];
            DECK[i] = DECK[index];
            DECK[index] = temp;
        }

        System.out.println("printed deck");
        //looping through deck to display card's value
        for (int i = 0; i < DECK.length; i++) { 
            System.out.println(DECK[i] + " ");
        }
    }

    private static int dealInitialPlayerCards() {
        int card1 = dealCard(); //Deal the first card to the player
        int card2 = dealCard(); //Deal the second card to the player
        
        //Display the player's two cards using the RANKS and SUITS arrays
        System.out.println("Your cards: " + RANKS[card1] + " of " + SUITS[DECK[currentCardIndex] % 4] + " and "
                + RANKS[card2] + " of " + SUITS[card2 / 13]);
        // Calculate and return the value of the two cards
        return cardValue(card1) + cardValue(card2);
    }

    private static int dealInitialDealerCards() {
        int card1 = dealCard(); //Deal a singl card to the dealer
        // Display the dealer's card using the RANKS and SUITS arrays
        System.out.println("Dealer's card: " + RANKS[card1] + " of " + SUITS[DECK[currentCardIndex] % 4]);
        return cardValue(card1); //return value of the dealt card
    }

    //Asks player to hit or stand
    private static int playerTurn(Scanner scanner, int playerTotal) {
        while (true) {
            
            //Tells user card total and asks if they want to hit or stand
            System.out.println("Your total is " + playerTotal + ". Do you want to hit or stand?");
            String action = scanner.nextLine().toLowerCase();
            
            //If user chooses hit, it deals a random card and adds it to total
            if (action.equals("hit")) {
                
                int newCard = dealCard(); 
                playerTotal += cardValue(newCard); //Adds newly dealt card to index total
                
                //Outputs the random card you drew, its value, and your new total
                System.out.println("new card index is " + newCard);
                System.out.println("You drew a " + RANKS[newCard] + " of " + SUITS[DECK[currentCardIndex] % 4]);
                
                //breaks loop for all possible choices
                if (playerTotal > 21) {
                    break;
                }
                } else if (action.equals("stand")) {
                break;
                } else {
                System.out.println("Invalid action. Please type 'hit' or 'stand'.");
                }
        }
        return playerTotal;
    }

    //Dealers turn: draws cards and adds to total until index value is 17 or more
    private static int dealerTurn(int dealerTotal) {
        while (dealerTotal < 17) {
            int newCard = dealCard();
            dealerTotal += cardValue(newCard);
        }
        //Outputs the dealer's total 
        System.out.println("Dealer's total is " + dealerTotal);
        return dealerTotal;
    }

    //Compares dealerTotal and playerTotal to determines a winner
    private static void determineWinner(int playerTotal, int dealerTotal) {
        if (dealerTotal > 21 || playerTotal > dealerTotal) {
            System.out.println("You win!");
        } else if (dealerTotal == playerTotal) {
            System.out.println("It's a tie!");
        } else {
            System.out.println("Dealer wins!");
        }
    }

    //Cards are ranked 0-12
    private static int dealCard() {
        return DECK[currentCardIndex++] % 13;
    }

    //converts the card ranking to blackjack value: Ace = 10 here
    private static int cardValue(int card) {
        return card < 9 ? card + 2 : 10;
    }

    int linearSearch(int[] numbers, int key) {
        int i = 0;
        for (i = 0; i < numbers.length; i++) {
            if (numbers[i] == key) {
                return i;
            }
        }
        return -1; // not found
    }
}
