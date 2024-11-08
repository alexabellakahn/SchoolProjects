package unitSIx;
import java.util.ArrayList;
import java.util.Scanner;

public class BlackjackPlayer {
	private String name;
    private ArrayList <BlackjackCard> hand;

    public BlackjackPlayer(String name) {
        this.name = name;
        hand = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void seeHand() {
        for (BlackjackCard c : hand)
            System.out.println(c);
    }

    public int getTotalHand() {
        // gets the total value of cards in the hand
        int total = 0;
        for (BlackjackCard c : hand) {
            total += c.getNumVal();
        }
        return total;
    }

    public void hit(BlackjackCard c) {
        // picks up a new card
        // if it is an ace, ask if 1 or 10 - need scanner for this
        // add to hand
        hand.add(c);
        if (c.getValue().equals("A")) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Choose the value for Ace (1 or 10): ");
            int value = scanner.nextInt();
            if (value == 1 || value == 10) {
                ((BlackjackCard) c).setAce(value);
            }
        }
    }
}
