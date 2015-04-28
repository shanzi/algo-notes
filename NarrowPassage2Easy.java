public class NarrowPassage2Easy {

    public int count(int[] size, int maxSize) {
        return count(size, maxSize, 0);
    }

    private void swap(int[] array, int a, int b) {
        int tmp = array[a];
        array[a] = array[b];
        array[b] = tmp;
    }

    private int count(int[] size, int maxSize, int start) {
        if (start >= size.length) {
            return 1;
        }
        int sizeMax = size[start];
        int sum = count(size, maxSize, start + 1);
        for (int i = start + 1; i < size.length; i++) {
            if (sizeMax + size[i] <= maxSize) {
                for (int j = i; j > start; j--) {
                    swap(size, j, j - 1);
                }
                sum += count(size, maxSize, start + 1);
                for (int j = start; j < i; j++) {
                    swap(size, j, j + 1);
                }
            }
            if (size[i] > sizeMax) sizeMax = size[i];
        }
        
        return sum;
    }
    public static void main(String[] args) {
        int[] size = {2, 4, 6, 1, 3, 5};
        NarrowPassage2Easy app = new NarrowPassage2Easy();
        System.out.println(app.count(size, 8));
    }
}
