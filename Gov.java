import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Gov {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Pattern namePattern = Pattern.compile("^[A-Za-z ]+$");

        // Create President candidates
        President[] presidentArray = new President[2];
        System.out.println("=== Enter President Candidates ===");
        for (int i = 0; i < 2; i++) {
            String name;
            while (true) {
                System.out.print("President " + (i + 1) + ": ");
                name = scanner.nextLine();
                if (namePattern.matcher(name).matches()) break;
                System.out.println("Invalid name. Please enter letters only.");
            }
            presidentArray[i] = new President(name);
        }

        // Create Vice President candidates
        VicePresident[] vpArray = new VicePresident[2];
        System.out.println("\n=== Enter Vice President Candidates ===");
        for (int i = 0; i < 2; i++) {
            String name;
            while (true) {
                System.out.print("Vice President " + (i + 1) + ": ");
                name = scanner.nextLine();
                if (namePattern.matcher(name).matches()) break;
                System.out.println("Invalid name. Please enter letters only.");
            }
            vpArray[i] = new VicePresident(name);
        }

        CandidateList<President> presidents = new CandidateList<>(presidentArray);
        CandidateList<VicePresident> vicePresidents = new CandidateList<>(vpArray);

        VoteCounter voteCounter = new VoteCounter();

        // Voting Mode
        System.out.println("\n=== Voting Mode ===");
        System.out.println("1. Manual Voting");
        System.out.println("2. Simulated Voting");
        System.out.print("Choose an option: ");
        int mode = scanner.nextInt();

        if (mode == 1) {
            for (int i = 0; i < 5; i++) {
                System.out.println("\nVoter #" + (i + 1));

                System.out.println("Vote for President:");
                for (int j = 0; j < presidents.size(); j++) {
                    System.out.println((j + 1) + ". " + presidents.get(j).getName());
                }
                System.out.print("Enter number (1 or 2): ");
                int votePresident = scanner.nextInt();

                System.out.println("\nVote for Vice President:");
                for (int j = 0; j < vicePresidents.size(); j++) {
                    System.out.println((j + 1) + ". " + vicePresidents.get(j).getName());
                }
                System.out.print("Enter number (1 or 2): ");
                int voteVP = scanner.nextInt();

                voteCounter.castVote(votePresident, voteVP);
            }

        } else if (mode == 2) {
            Random rand = new Random();
            System.out.print("Enter number of simulated voters: ");
            int simulatedVoters = scanner.nextInt();
            for (int i = 0; i < simulatedVoters; i++) {
                int randomPresVote = rand.nextInt(2) + 1;
                int randomVPVote = rand.nextInt(2) + 1;
                voteCounter.castVote(randomPresVote, randomVPVote);
            }
            System.out.println(simulatedVoters + " votes have been simulated.");
        } else {
            System.out.println("Invalid mode selected.");
        }

        voteCounter.displayResults(presidents, vicePresidents);
        scanner.close();
    }
}
