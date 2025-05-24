
public class VoteCounter implements VoteProcessor {
    private final int[] presidentVotes = new int[2];
    private final int[] vicePresidentVotes = new int[2];

    @Override
    public synchronized void castVote(int choicePresident, int choiceVicePresident) {
        if (choicePresident >= 0 && choicePresident < presidentVotes.length) {
            presidentVotes[choicePresident]++;
        }

        if (choiceVicePresident >= 0 && choiceVicePresident < vicePresidentVotes.length) {
            vicePresidentVotes[choiceVicePresident]++;
        }
    }

    public void displayResults(President[] presidents, VicePresident[] vicePresidents) {
        System.out.println("\nVote Results:");
        for (int i = 0; i < presidentVotes.length; i++) {
            System.out.println("President " + (i + 1) + ": " + presidentVotes[i] + " vote(s)");
        }
        for (int i = 0; i < vicePresidentVotes.length; i++) {
            System.out.println("Vice President " + (i + 1) + ": " + vicePresidentVotes[i] + " vote(s)");
        }
    }
}