public class TheNumberGame {
    public String determineOutcome(int A, int B) {
        StringBuilder a = new StringBuilder();
        a.append(A);
        String b = String.valueOf(B);
        if (a.indexOf(b) >=0 || a.reverse().indexOf(b) >= 0) return "Manao wins";
        else return "Manao loses";
    }
}
