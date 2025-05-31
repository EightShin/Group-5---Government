import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Gov {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Create President candidates up to 2
        President[] presidents = new President[2];
        System.out.println("=== Enter President Candidates ===");
        for (int i = 0; i < 2; i++) {
            System.out.print("President " + (i + 1) + ": ");
            String name = sc.nextLine();
            presidents[i] = new President(name);
        }

        // Create Vice President candidates up to 2
        VicePresident[] vicePresidents = new VicePresident[2];
        System.out.println("\n=== Enter Vice President Candidates ===");
        for (int i = 0; i < 2; i++) {
            System.out.print("Vice President " + (i + 1) + ": ");
            String name = sc.nextLine();
            vicePresidents[i] = new VicePresident(name);
        }

        VoteCounter voteCounter = new VoteCounter();

        int mode = 0;
        boolean validMode = false;
        while (!validMode) {
            try {
                System.out.println("\nChoose voting mode:");
                System.out.println("1. Manual Voting (5 voters)");
                System.out.println("2. Simulated Voting");
                System.out.print("Enter option (1 or 2): ");
                mode = sc.nextInt();
                sc.nextLine(); // consume newline

                if (mode == 1 || mode == 2) {
                    validMode = true;
                } else {
                    System.out.println("Invalid mode. Please enter 1 or 2.");
                }
                //Exception handling: dont accept any other than 1 and 2 for inputs by user
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number (1 or 2).");
                sc.nextLine(); // clear invalid input
            }
        }

        if (mode == 1) {
            // Manual voting for 5 voters
            for (int voter = 1; voter <= 5; voter++) {
                System.out.println("\n=== Voting for Voter #" + voter + " ===");

                int votePresident = 0;
                while (true) {
                    try {
                        System.out.println("Vote for President:");
                        for (int i = 0; i < presidents.length; i++) {
                            System.out.println((i + 1) + ". " + presidents[i].getName());
                        }
                        System.out.print("Enter number (1 or 2): ");
                        votePresident = sc.nextInt();
                        if (votePresident < 1 || votePresident > 2) {
                            System.out.println("Invalid choice. Choose between 1 and 2.");
                        } else {
                            break;
                        }
                        //Exception handling: dont accept any other than 1 and 2 for inputs by user
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a number.");
                        sc.nextLine(); // clear invalid input
                    }
                }

                int voteVP = 0;
                while (true) {
                    try {
                        System.out.println("\nVote for Vice President:");
                        for (int i = 0; i < vicePresidents.length; i++) {
                            System.out.println((i + 1) + ". " + vicePresidents[i].getName());
                        }
                        System.out.print("Enter number (1 or 2): ");
                        voteVP = sc.nextInt();
                        if (voteVP < 1 || voteVP > 2) {
                            System.out.println("Invalid choice. Choose between 1 and 2.");
                        } else {
                            break;
                        }
                        //Exception handling: dont accept any other than 1 and 2 for inputs by user
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a number.");
                        sc.nextLine(); // clear invalid input
                    }
                }

                voteCounter.castVote(votePresident, voteVP);
                System.out.println("Vote recorded.\n");
            }

        } else if (mode == 2) {
            // Simulated voting based on the number entered by the user for example 5, its gonna simulate 5 voters and placed there votes on the candidates
            int numSimulated = 0;
            while (true) {
                try {
                    System.out.print("Enter number of simulated voters: ");
                    numSimulated = sc.nextInt();
                    if (numSimulated <= 0) {
                        System.out.println("Number must be greater than zero.");
                    } else {
                        break;
                    }
                    //Exception handling: dont accept any other than 1 and 2 for inputs by user
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a whole number.");
                    sc.nextLine(); // clear invalid input
                }
            }

            for (int i = 0; i < numSimulated; i++) {
                int randomPresidentVote = (int) (Math.random() * 2) + 1;
                int randomVPVote = (int) (Math.random() * 2) + 1;
                voteCounter.castVote(randomPresidentVote, randomVPVote);
            }
            System.out.println(numSimulated + " simulated votes cast.\n");
        }

        // Display vote counts
        System.out.println("=== Voting Results ===");
        for (int i = 1; i <= presidents.length; i++) {
            int count = voteCounter.countVotes(Position.PRESIDENT, i);
            System.out.println(presidents[i - 1].getName() + " received " + count + " vote(s) for President.");
        }
        for (int i = 1; i <= vicePresidents.length; i++) {
            int count = voteCounter.countVotes(Position.VICE_PRESIDENT, i);
            System.out.println(vicePresidents[i - 1].getName() + " received " + count + " vote(s) for Vice President.");
        }

        sc.close();
        
        // Save results to a file
        try (PrintWriter writer = new PrintWriter(new FileWriter("voting_results.txt"))) {
            writer.println("=== Voting Results ===");
            for (int i = 1; i <= presidents.length; i++) {
                int count = voteCounter.countVotes(Position.PRESIDENT, i);
                writer.println(presidents[i - 1].getName() + " received " + count + " vote(s) for President.");
            }
            for (int i = 1; i <= vicePresidents.length; i++) {
                int count = voteCounter.countVotes(Position.VICE_PRESIDENT, i);
                writer.println(vicePresidents[i - 1].getName() + " received " + count + " vote(s) for Vice President.");
            }
            System.out.println("\nResults saved to voting_results.txt");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the results: " + e.getMessage());
        }

    }
    
}
