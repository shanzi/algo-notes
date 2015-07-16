# The Skyline Problem

This problem is a little hard. Given a series of buildings, we reserve a queue where in the queue the buildings
is sorted by their right sides' position and their height is descending all the way. Note that the won't
be a building in the queue contains right top point of another building in the queue.

We we are going to add next building, first from the head of queue we poll all buildings whose right
side is on the left of left side of current building as these buildings won't affect skyline after
current building's left side. Then we removes all buildings in the queue whose top right point is
contained by current building. At last we insert current building to a proper position in the queue.

To build the result, we add upstair points when we first meet a buildings if it is higher than any
buildings in the queue. Noted that many buildings may have the same left side position, so when adding
upstair points, we first check last points and make sure the buildings with same left side position
only contribute one highest upstair point in the result. We add downstair points when poll bulidings
whose right side is at the left of current building as well as in the end of program to empty queue.

There are two ways to implement this algorithm. The first is using `LinkedList` and insertion method
when adding items into the queue. The other method is using a `PriorityQueue` and let the `PriorityQueue`
to sort buildings for us.

The `LinkedList` solution:

!CODEFILE "../leetcode/TheSkylineProblem.java"

The `PriorityQueue` solution:

!CODEFILE "../leetcode/TheSkylineProblemII.java"
