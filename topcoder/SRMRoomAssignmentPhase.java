import java.util.Arrays;

public class SRMRoomAssignmentPhase {
    public int countCompetitors(int[] ratings, int k) {
        int me = ratings[0];
        Arrays.sort(ratings);
        int pos = Arrays.binarySearch(ratings, me);
        return (ratings.length - pos - 1) / k;
    }
}
