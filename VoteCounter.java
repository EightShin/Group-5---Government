public class VoteCounter {
    private int[] presidentVotes = new int[2];
    private int[] vicePresidentVotes = new int[2];

    public void castVote(int presIndex, int vpIndex) {
        if (presIndex >= 1 && presIndex <= 2) {
            presidentVotes[presIndex - 1]++;
        }
        if (vpIndex >= 1 && vpIndex <= 2) {
            vicePresidentVotes[vpIndex - 1]++;
        }
    }

    public void displayResults(CandidateList<President> presidents, CandidateList<VicePresident> vicePresidents) {
        System.out.println("\n=== Voting Results ===");
        System.out.println("President Votes:");
        for (int i = 0; i < presidents.size(); i++) {
            System.out.println(presidents.get(i).getName() + ": " + presidentVotes[i]);
        }

        System.out.println("\nVice President Votes:");
        for (int i = 0; i < vicePresidents.size(); i++) {
            System.out.println(vicePresidents.get(i).getName() + ": " + vicePresidentVotes[i]);
        }
    }
}