import java.util.Scanner;

public class Gov {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Create President candidate up to 2
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

        System.out.println("\nChoose voting mode:");
        System.out.println("1. Manual Voting (5 voters)");
        System.out.println("2. Simulated Voting");
        System.out.print("Enter option (1 or 2): ");
        int mode = sc.nextInt();
        sc.nextLine(); // consume newline

        if (mode == 1) {
            // Manual voting for 5 voters (Anonymous)
            for (int voter = 1; voter <= 5; voter++) {
                System.out.println("\n=== Voting for Voter #" + voter + " ===");

                System.out.println("Vote for President:");
                for (int i = 0; i < presidents.length; i++) {
                    System.out.println((i + 1) + ". " + presidents[i].getName());
                }
                System.out.print("Enter number (1 or 2): ");
                int votePresident = sc.nextInt();

                System.out.println("\nVote for Vice President:");
                for (int i = 0; i < vicePresidents.length; i++) {
                    System.out.println((i + 1) + ". " + vicePresidents[i].getName());
                }
                System.out.print("Enter number (1 or 2): ");
                int voteVP = sc.nextInt();
                sc.nextLine(); // consume leftover newline

                voteCounter.castVote(votePresident, voteVP);

                System.out.println("Vote recorded.\n");
            }
        } else if (mode == 2) {
            // Simulated voting (Based on User input how much voters will be simulated)
            System.out.print("Enter number of simulated voters: ");
            int numSimulated = sc.nextInt();
            sc.nextLine(); // consume newline

            for (int i = 0; i < numSimulated; i++) {
                int randomPresidentVote = (int) (Math.random() * 2) + 1;
                int randomVPVote = (int) (Math.random() * 2) + 1;
                voteCounter.castVote(randomPresidentVote, randomVPVote);
            }
            System.out.println(numSimulated + " simulated votes cast.\n");
        } else {
            System.out.println("Invalid mode selected. Exiting.");
            sc.close();
            return;
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
    }
}
