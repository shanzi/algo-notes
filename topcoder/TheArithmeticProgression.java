class TheArithmeticProgression {
    double minimumChange(int a, int b, int c) {
        double delta = b * 2 - a - c;
        return Math.abs(delta / 2.0);
    }
}
