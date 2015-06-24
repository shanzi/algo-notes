public class RectangleArea {
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        
        int t1 = Math.max(B, D);
        int b1 = Math.min(B, D);
        int l1 = Math.min(A, C);
        int r1 = Math.max(A, C);
        
        int t2 = Math.max(F, H);
        int b2 = Math.min(F, H);
        int l2 = Math.min(E, G);
        int r2 = Math.max(E, G);
        
        int area1 = (t1 - b1) * (r1 - l1);
        int area2 = (t2 - b2) * (r2 - l2);
        
        int insectArea = 0;
        
        int t3 = Math.min(t1, t2);
        int b3 = Math.max(b1, b2);
        int l3 = Math.max(l1, l2);
        int r3 = Math.min(r1, r2);
        
        if (t3 > b3 && r3 > l3) {
            insectArea = (t3 - b3) * (r3 - l3);
        }
        
        return area1 + area2 - insectArea;
    }
}
