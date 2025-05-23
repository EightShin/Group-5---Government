import java.util.Scanner;

public class Gov {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] presidents = new String[2];
        String[] vicePresidents = new String[2];

        // Step 1: Input candidate names President and Vice President
        System.out.println("=== Enter Candidate Names ===");

        for (int i = 0; i < 2; i++) {
            System.out.print("Enter name for President " + (i + 1) + ": ");
            presidents[i] = sc.nextLine();
        }

        for (int i = 0; i < 2; i++) {
            System.out.print("Enter name for Vice President " + (i + 1) + ": ");
            vicePresidents[i] = sc.nextLine();
        }

        // Step 2: Voting - show options for Voting
        System.out.println("\n=== Voting Time ===");

        System.out.println("President Candidates:");
        for (int i = 0; i < 2; i++) {
            System.out.println((i + 1) + ". " + presidents[i]);
        }
        System.out.print("Enter the number of your vote for President (1 or 2): ");
        int votePres = sc.nextInt();

        System.out.println("\nVice President Candidates:");
        for (int i = 0; i < 2; i++) {
            System.out.println((i + 1) + ". " + vicePresidents[i]);
        }
        System.out.print("Enter the number of your vote for Vice President (1 or 2): ");
        int voteVP = sc.nextInt();

        // Step 3: Stores the votes
        String selectedPresident = presidents[votePres - 1];
        String selectedVicePresident = vicePresidents[voteVP - 1];

        // Step 4: Display stored result for now
        System.out.println("\n=== Vote Stored ===");
        System.out.println("Voted President: " + selectedPresident);
        System.out.println("Voted Vice President: " + selectedVicePresident);

        sc.close();
    }
}