# Seven Segment Display

We are using a single 7 segments display to display a digit but some of the segments are broken
and can not be lit. Now we are cycling counting down numbers and have observed a series of display.
You are asked to predict next display.

At first we should not that the prediction is based on the broken status of the display. If a segment
is broken, then in our prediction it should not be lit neither. We can use an integer to represent a
displaying with its binary bits. As the number are counting down, instead of guessing the first number
we can just assume what the next digit is and check back counting up and find if there is a contradiction.

And, as we do not know which segment is broken, at first we use bitwise `OR` operation of the series
of display first to find those segments that are sure to be good. As for those never lit, we iterate
all subsets of them and with that we check if there is an assumption that is valid. If there is only one
possible prediction is valid, we get our answer. Or, if there are no or multiple possible answers,
we return error.

!CODEFILE "../apac/2015/RoundA/SevenSegmentDisplay.java"
