import java.util.Scanner;

public class Gov {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        // Create President candidates
        President[] presidents = new President[2];
        System.out.println("=== Enter President Candidates ===");
        for (int i = 0; i < 2; i++) {
            System.out.print("President " + (i + 1) + ": ");
            String name = scanner.nextLine();
            presidents[i] = new President(name);
        }

        // Create Vice President candidates
        VicePresident[] vicePresidents = new VicePresident[2];
        System.out.println("\n=== Enter Vice President Candidates ===");
        for (int i = 0; i < 2; i++) {
            System.out.print("Vice President " + (i + 1) + ": ");
            String name = scanner.nextLine();
            vicePresidents[i] = new VicePresident(name);
        }

        VoteCounter voteCounter = new VoteCounter();

        System.out.println("\nChoose Voting Mode:");
        System.out.println("1. Manual Voting");
        System.out.println("2. Simulated Voting");
        System.out.print("Enter choice (1 or 2): ");
        int choice = scanner.nextInt();
        scanner.nextLine();  // consume newline

        if (choice == 1) {
            // Manual Voting for 5 voters
            int totalVoters = 5;
            for (int voter = 1; voter <= totalVoters; voter++) {
                System.out.println("\nVoter #" + voter);

                System.out.println("Vote for President:");
                for (int i = 0; i < presidents.length; i++) {
                    System.out.println((i + 1) + ". " + presidents[i].getName());
                }
                System.out.print("Enter number (1 or 2): ");
                int votePresident = scanner.nextInt();

                System.out.println("Vote for Vice President:");
                for (int i = 0; i < vicePresidents.length; i++) {
                    System.out.println((i + 1) + ". " + vicePresidents[i].getName());
                }
                System.out.print("Enter number (1 or 2): ");
                int voteVP = scanner.nextInt();

                voteCounter.castVote(votePresident - 1, voteVP - 1);
                System.out.println("Vote recorded for Voter #" + voter);
            }

            System.out.println("\n=== Voting Results ===");
            voteCounter.displayResults(presidents, vicePresidents);

        } else if (choice == 2) {
            // Simulated Voting
            System.out.print("Enter number of simulated voters: ");
            int numVoters = scanner.nextInt();

            Thread[] voters = new Thread[numVoters];
            for (int i = 0; i < numVoters; i++) {
                voters[i] = new Thread(new Voter(voteCounter));
                voters[i].start();
            }

            for (Thread voter : voters) {
                voter.join();
            }

            System.out.println("\n=== Simulated Voting Results ===");
            voteCounter.displayResults(presidents, vicePresidents);

        } else {
            System.out.println("Invalid choice.");
        }

        scanner.close();
    }
}