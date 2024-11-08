package unitSIx;

// Alexa Kahn Risk Assessment

// Risk Assessment.java
// Adapted from Jillian Cardamon 6/6/18

/* Idea (from article): each inmate is assigned a true probability of 
   reoffending based on normal distribution mean and std dev from the 
   Ohio data - weighted avg = 21.79, std dev = 13.85 then the computer 
   uses those probabilities to decide whether each dot will actually 
   reoffend a dot with a 75% risk will on average reoffend 3/4 times no 
   one knows the true chance of reoffending so risk assessment tries to 
   estimate it normal distribution with mean set at true risk and std 
   dev 0.15 can use random.gauss(mu, sigma) for normal distribution
*/

import java.util.*;
import java.util.Random;

public class Risk_Assessment {
    public static double lowCutOff = 0;
    public static double mediumCutOff = 0;
    public static double highCutOff = 43;

    public static Defendant[] defendants;
    public static ArrayList<Defendant> awardedParole;
    public static ArrayList<Defendant> deniedParole;

    public static void printDefendantList(Defendant[] l) {
        // given an array of defendants, print out each defendant
        // to help debugging
        for (Defendant d : l)
            System.out.println(d);
    }

    public static Defendant[] createDefendants() { //step 1
        Defendant[] defendants = new Defendant[100];
        Random random = new Random();

        for (int i = 0; i < 100; i++) {
            // calctrueRisk using a normal distribution with mean 21.79 and standard deviation 13.85
            double trueRisk = random.nextGaussian() * 13.85 + 21.79;
            trueRisk = Math.max(0, Math.min(trueRisk, 43)); // Ensure trueRisk is between 0 and 43 inclusive

            // determine whether the defendant will reoffend based on trueRisk
            double reoffendThreshold = random.nextDouble() * 43;
            boolean willReoffend = reoffendThreshold <= trueRisk;

            // create defendant object with calculated trueRisk and willReoffend
            defendants[i] = new Defendant(trueRisk, willReoffend);
        }

        return defendants;
    }

    public static double[] chooseCutOffs() { // step 2
    	 Scanner scanner = new Scanner(System.in);
         double lowCutoff = 0;
         double mediumCutoff = 0;
         boolean validInput = false;

         // prompt the user for lowCutoff input and validate
         while (!validInput) {
             try {
                 System.out.print("Enter the low cutoff (1-99): ");
                 int lowPercentage = scanner.nextInt();
                 if (lowPercentage < 1 || lowPercentage > 99) {
                     throw new IllegalArgumentException("Invalid input. Please enter a value between 1 and 99.");
                 }
                 lowCutoff = (double) lowPercentage / 100 * highCutOff;
                 validInput = true;
             } catch (Exception e) {
                 System.out.println(e.getMessage());

             }
         }

         // prompt the user for mediumCutoff input and validate
         validInput = false;
         while (!validInput) {
             try {
                 System.out.print("Enter the medium cutoff (1-99): ");
                 int mediumPercentage = scanner.nextInt();
                 if (mediumPercentage < 1 || mediumPercentage > 99) {
                     throw new IllegalArgumentException("Invalid input. Please enter a value between 1 and 99.");
                 }
                 mediumCutoff = (double) mediumPercentage / 100 * highCutOff;
                 validInput = true;
             } catch (Exception e) {
                 System.out.println(e.getMessage());
             }
         }

         return new double[]{lowCutoff, mediumCutoff, highCutOff};
     
    }


    public static void assessDefendants() { //step 3
    	
    	awardedParole = new ArrayList<Defendant>();
        deniedParole = new ArrayList<Defendant>();
    	
    	for (Defendant defendant : defendants) {
            defendant.assess(lowCutOff, mediumCutOff, highCutOff);
            defendant.decideParole();
            System.out.println(defendant);

            if (defendant.isGivenParole()) {
                awardedParole.add(defendant);
            } else {
                deniedParole.add(defendant);
            }
        }
    }

    public static void paroleStats() {// step 4
   
    	
    	int totalAwardedParole = awardedParole.size();
        int totalDeniedParole = deniedParole.size();

        int reoffendAfterParole = 0;
        int wouldNotReoffendAfterDenial = 0;

        for (Defendant defendant : awardedParole) {
            if (defendant.willOffendAgain()) {
                reoffendAfterParole++;
            }
        }

        for (Defendant defendant : deniedParole) {
            if (!defendant.willOffendAgain()) {
                wouldNotReoffendAfterDenial++;
            }
        }

        double reoffendAfterParolePercentage = (double) reoffendAfterParole / totalAwardedParole * 100;
        double wouldNotReoffendAfterDenialPercentage = (double) wouldNotReoffendAfterDenial / totalDeniedParole * 100;

        System.out.println("Parole Statistics:");
        System.out.println("Total Defendants Awarded Parole: " + totalAwardedParole);
        System.out.println("Total Defendants Denied Parole: " + totalDeniedParole);
        System.out.println("Percentage of Defendants Reoffending After Parole: " + reoffendAfterParolePercentage + "%");
        System.out.println("Percentage of Defendants Not Reoffending After Denial: " + wouldNotReoffendAfterDenialPercentage + "%");
    }

    public static void main(String[] args) {
    	
    	awardedParole = new ArrayList<>();
        deniedParole = new ArrayList<>();// to avoid null pointer exception
        
    	Scanner scanner = new Scanner(System.in);

        // step 1 (create defendants)
        defendants = createDefendants();

        // step 2 (get user input for cutoff percents)
        double[] cutoffs = chooseCutOffs();
        lowCutOff = cutoffs[0];
        mediumCutOff = cutoffs[1];
        highCutOff = cutoffs[2];

        // step 3 (assess defendants)
        assessDefendants();

        // step 4 (print stats)
        paroleStats();
    }
}


