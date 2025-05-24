public class Voter implements Runnable {
    private final VoteCounter counter;

    public Voter(VoteCounter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        int presChoice = (int) (Math.random() * 2);
        int vpChoice = (int) (Math.random() * 2);
        counter.castVote(presChoice, vpChoice);
    }
}
