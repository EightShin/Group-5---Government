import java.util.ArrayList;
import java.util.List;

public class VoteCounter {
    private List<Vote> votes = new ArrayList<>();

    // Nested class to represent a single vote
    public static class Vote {
        private final int presidentIndex;
        private final int vicePresidentIndex;

        public Vote(int presidentIndex, int vicePresidentIndex) {
            this.presidentIndex = presidentIndex;
            this.vicePresidentIndex = vicePresidentIndex;
        }

        public int getPresidentIndex() {
            return presidentIndex;
        }

        public int getVicePresidentIndex() {
            return vicePresidentIndex;
        }
    }

    public void castVote(int presidentChoice, int vicePresidentChoice) {
        votes.add(new Vote(presidentChoice, vicePresidentChoice));
    }

    public int countVotes(Position position, int candidateIndex) {
        int count = 0;
        for (Vote vote : votes) {
            if (position == Position.PRESIDENT && vote.getPresidentIndex() == candidateIndex) {
                count++;
            } else if (position == Position.VICE_PRESIDENT && vote.getVicePresidentIndex() == candidateIndex) {
                count++;
            }
        }
        return count;
    }

    public List<Vote> getVotes() {
        return votes;
    }
}
