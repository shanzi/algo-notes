public class CandyMaking {
    public double findSuitableDensity(int[] containerVolume, int[] desiredWeight) {
        double min = 10000000000.0;
        for (int i = 0; i < containerVolume.length; i++) {
            double density = desiredWeight[i] / (double)containerVolume[i];
            double difference = 0.0;
            for (int j = 0; j < containerVolume.length; j++) {
                difference += Math.abs(containerVolume[j] * density - desiredWeight[j]);
            }
            min = Math.min(min, difference);
        }

        return min;
    }
}
