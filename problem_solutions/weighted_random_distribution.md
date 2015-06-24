# Weighted Random Distribution

About this topic, [this article](http://www.electricmonk.nl/log/2009/12/23/weighted-random-distribution/)
gives quite a detailed description. This chapter is going to rephrase some of the description of it
and rewrite the sample code into Java.

## Preface

If we'd like to randomly choose an element in an array, and each element has the same probability
to be chosen, then we just generate a random int $$i \in [0, N)$$ where $$N$$ is the number of elements,
and use $$i$$ as the index to access the element in the array. This is called *uniform distribution*.

But what if we'd like the elements have different likelihood to be chosen? That is weighted random
distribution problem. Let's say we want the item with hight weight value more likely to be chosen
and the probability is a linear function of weight. In other words, if item A has weight $$1$$ and
item B has weight $$2$$, then the item B is twice as likely to be chosen as item A. 

The solution to this problem depends on the actual demand of the problem and properties of
the weight value. Before we start to solve the problem, we should take notice of the following
few questions:

1. How many items to be randomly chosen from?
2. Do the members of item list change frequently?
3. Does weight value of an item change frequently?
4. How much extra space we can use?
5. How is the precision of weight value? That is, is the value an integer or decimal?
if it is a float number, how many decimal spaces is allowed?

Based on different answers to these questions, the different solution is most suitable.

## Precision of weight value

Let's talk about precision or number of decimal spaces of weight value. The first thing
we need to do is to uniform all the value into integer. Why we need to do this?
That is because our random number generated is ususally integer, if we'd like to use
this integer random value to pick element, we have to either translate the random number
to decimal or translate the weight value into integer.

But why we should translate the weight value instead of the converse? Because in
a computer system, some decimal number in base 10 is in fact not able to be represented
precisely in binary. So it is better to translate all the values into integer.

After the translation, we can set out to find methods to meet the demand.

## Expanding

Expanding is the easiest way to solve this problem. The idea is simple, we
repeat an item as many as the weight value after translation the value into integer.
This method is proper for that an item won't be repeat too many time.

In this solution, you can add a new item easily. But removing or changing the value will still cost a lot.
The following codes shows how to do it.

```java
import java.util.*;

class ExpandingWeightRandom {
    ArrayList<Integer> items = new ArrayList<Integer>();

    void addItem(int item, int weight) {
        for(int i = 0; i < weight; i++) {
            items.add(item);
        }
    }

    void removeItem(int item) {
        int start = -1;
        for(int i = 0; i < items.size(); i++) {
            if (items.get(i) == item){
                start = i;
                break;
            }
        }
        int end = start + 1;
        for (; end < items.size(); end++) {
            if (items.get(i) != item) break;
        }
        items.removeRange(start, end);
    }

    int chooseOne() {
        if (items.isEmpty()) return 0;

        return items.get(Random.nextInt(items.size() - 1))
    }
}
```

# In-place (Unsorted)

As it is said above, if the weight value is very large, say 412308312, it will be
not even possible to use expanding at all. Here we have another simple solution. 

Instead of using an `ArrayList`, we use a `HashMap` to keep the item as well as its
weight value in record. Each time we generate a random number between zero and the total
weight value in storage. Then we iterate through the items, sum up the weight values processed.
The iterate should terminated when we have an item that, before added the weight value of it,
the sum of processed weight is smaller or equal to the random number, and after that, the sum
exceed. This item will be the item we choose.

```java
import java.util.*;

class InPlaceWeightRandom {
    HashMap<Integer, Integer> weightsMap = new HashMap<Integer, Integer>();
    int totalWeight = 0;

    void putItem(int item, int weight) {
        if (weightsMap.containsKey(item)) {
            totalWeight -= weight;
        }
        weightsMap.put(item, weight);
        totalWeight += weight;
    }

    void removeItem(int item) {
        if (weightsMap.containsKey(item)) {
            totalWeights -= weightMap.get(item);
            weightsMap.remove(item);
        }
    }

    int chooseOne() {
        int sum = 0; 
        int random = Random.nextInt(totalWeights - 1);
        for(Map.Entry<Integer, Integer> entry: weightsMap) {
            sum += entry.getValue();
            if (sum > random) return entry.getKey();
        }
        return 0;
    }
}
```

This method is quite good, it is space efficient and to add, remove and change item are all operations with
little time cost. It can handles item with any large weight. But to choose an item has an time complexity of $$O(N)$$.
This is probably unacceptable in some cases.

# In-place(sorted)

To improve the In-place algorithm above, we can develop an sorted version of it. This technique uses greedy strategy
and will improve the picking a lot. At first, we have the following observation, the larger a weight is added to
`sum` in the code above, the less is left to reach `random`, and the sooner the iteration will ends.
So if we let the iteration starts from the largest weight to the smallest, the iteration will ends the soonest.

The codes below implement this sored In-place algorithm:

```java
import java.util.*;

class InPlaceWeightRandom {
    int totalWeight = 0;
    ArrayList<Map.Entry<Integer, Integer>> weightsList = new ArrayList<Map.Entry<Integer, Integer>>()

    void addItem(int item, int weight) {
        weightsList.add(new Map.Entry<Integer, Integer>(item, weight));
        collections.sort(weightsList, 
            new Comparator<Map.Entry<Integer, Integer>>() {
                int compare(Map.Entry<Integer, Integer> a, Map.Entry<Integer, Integer> b) {
                    if (b.getValue() > a.getValue()) return 1;
                    else if (b.getValue() < a.getValue()) return -1;
                    else return b.getKey().compareTo(a.getKey());
                }
            }
        );

        totalWeights += weight;
    }

    void removeItem(int item) {
        Map.Entry itemEntry = null;
        for (Map.Entry entry : weightsList) {
            if (entry.getKey() == item) itemEntry = entry;
        }
        if (itemEntry != null) {
            weightsList.remove(entry);
            totalWeights -= weight;
        }
    }

    int chooseOne() {
        int sum = 0; 
        int random = Random.nextInt(totalWeights - 1);
        for (Map.Entry<Integer, Integer> entry : weightsList) {
            sum += entry.getValue();
            if (sum > random) {
                return entry.getKey();
            }
        }
        return 0;
    }
}
```

As to maintain the order of `weightsList`, we have to sort the list every time we added a new element.
We can also give a protocol to add several element first before sort. But no matter how we change
the implementation, this algorithm is generally costly when adding and removing elements. Choosing
is a little faster than the older in-place algorithm but not that much.

Thus, this algorithm is in fact not a good one. But we should pay attention to the idea that we
use greedy strategy to speed up the summing process. This technique is also useful at many other places.

# Pre-calculated

This might be the best solution if the problem is not suitable for the Expanding one.
The main idea is, we pre-calculate the sum of weights and decide the boundary to choose each item.
Each time we'd like to pick an element, we use binary search technique to find and return the element fast.

Adding new items is quite fast with this algorithm but removing one is quite costly. Choosing one item
has a time complexity of $$O(\log N)$$, which should be quite satisfied.

Sample implementation:

```java
import java.util.*;

class PreCalculateWeightsRandom{
    int totalWeight = 0;
    ArrayList<Integer> itemList = new ArrayList<Integer>();
    ArrayList<Integer> weightsList = new ArrayList<Integer>();

    void addItem(int item, int weight) {
        itemList.add(item);
        totalWeights += weight;
        weightList.add(totalWeights);
    }

    void removeItem(int item) {
        int index = itemList.indexOf(item);
        itemList.remove(index);
        int weight = weightList.get(index);
        weightsList.remove(index);
        for (int i = index; i < weightsList.size(); i++) {
            weightList.get(i) = weightList.get(i) - weight;
        }
    }

    int chooseOne() {
        int random = Random.nextInt(totalWeights - 1);
        int index = binarySearchBoundary(weightList, random);
        return itemList.get(index);
    }

    int binarySearchBoundary(ArrayList<Integer> list, int value) {
        int l = 0;
        int r = list.size() - 1;

        while (l < r) {
            int mid = (l + r) / 2;
            if (list.get(mid) < value) l = mid + 1;
            else r = mid;
        }

        return r;
    }
}
```

# Summary

We introduced 4 different methods to pick an element from a set with a weighed probability of each element.
Generate speaking, although is a little amateurish, the Expanding method is in fact a very good solution
as it provides fast adding and picking function. The implementation is simple too. It is extremely useful
when no item is needed to be repeat too large times and removing operation is scarcely performed.
It is also a good start point for further improvement.

If adding and removing is often and picking is scarce, In-place(unsorted) might be a good choice.
It has constant time complexity for these two operation but picking is a little slow.

If adding and picking is often and we scarcely removing items, the Pre-calculated solution is the best.
Adding item cost contant time and picking item cost $$O(\log N)$$. Removing items in its worst case
may cost $$O(N)$$.

The In-place(sorted) might not be a good solution. But the greedy strategy idea should be well learnt
and keep in mind.
