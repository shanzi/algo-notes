# Stall Reservation (POJ 3190)

This problem is for a greedy strategy. At first we sort the cows by their start milking time and from the first
we start serve them with a stall. Instead of deciding number of stall at the beginning, we allocate a new stall
when needed. Once a new cow comes, we check if there is a free stall. If true, we allocate the cow to the
free stall, or we have to allocate a new one. As to speed up the finding free stalls procedure, we can use a
`PriorityQueue` which sort cows in service by their end milking time. So when a new cow comes, we first poll
cows from the queue if the serving has already finished before we try to serve new cow.

!CODEFILE "../poj/StallReservation.java"
