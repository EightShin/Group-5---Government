public class CandidateList<T extends Candidate> {
    private T[] candidates;

    public CandidateList(T[] candidates) {
        this.candidates = candidates;
    }

    public T get(int index) {
        return candidates[index];
    }

    public int size() {
        return candidates.length;
    }
}