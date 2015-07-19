# Candy

We should apply greedy strategy in solving this problem. At first, if a child $$i$$ has a higher rating
than his or her neighbour $$i - 1$$, we let it get one candy more than $$i - 1$$ to achieve a minimum candies count.
Of course, if his or her rating is higher than $$i + 1$$ we should have the same condition holds.

In fact, the number of candies a child get equals to the greater value between increasing ratings count on the left
and decreasing ratings count on the right. So we can have a two round solution:

```java
public class Candy {
    public int candy(int[] ratings) {
        if (ratings.length == 0) return 0;
        int[] candies = new int[ratings.length];
        
        candies[0] = 1;
        for (int i = 1; i < candies.length; i++) {
            if (ratings[i] > ratings[i - 1]) candies[i] = candies[i - 1] + 1;
            else candies[i] = 1;
        }
        
        for (int i = candies.length - 1; i > 0; i--) {
            if (ratings[i] < ratings[i - 1]) candies[i - 1] = Math.max(candies[i - 1], candies[i] + 1);
        }
        
        int sum = 0;
        for (int c : candies) sum += c;
        
        return sum;
    }
}
```

To be more clear, we can see that after the first round of iteration, each child get the minimum count of
candies while the rating conditions holds only for their left neighours. After the second round of
iteration, we let the conditions hold for their right neigbours too. It can be proved thaht it is optimal
as if you decrease any of the child's number of candies, either left or right conditions will be break.
