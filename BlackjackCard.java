package unitSIx;


public class BlackjackCard extends Card {
    private int numVal;

    public BlackjackCard(String s, String v) {
        super(s, v);
        if (v.equals("A")) {
            // Ace can be either 1 or 10, defaulting to 1
            numVal = 1;
        } else if (v.equals("T") || v.equals("J") || v.equals("Q") || v.equals("K")) {
            // Face cards (10, Jack, Queen, King) have a value of 10
            numVal = 10;
        } else {
            // Other cards have numeric values
            numVal = Integer.parseInt(v);
        }
    }

    public boolean setAce(int set) {
        // return true if set is 1 or 10, and reset
        // false if set is not 1 and not 10
        if (set == 1 || set == 10) {
            numVal = set;
            return true;
        }
        return false;
    }

    public int getNumVal() {
        return numVal;
    }

    @Override
    public String toString() {
        // call super version of toString to implement
        return super.toString();
    }
}
