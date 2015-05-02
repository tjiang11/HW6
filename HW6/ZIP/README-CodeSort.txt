We decided to construct a directed graph where each vertex in the graph
consists of one letter in the 'alphabet'. In this way, each letter will
come before every other letter that it points to in the graph.

(a) --> (c) --> (z)
 |       ^
 v       |
(b) ------

This is an example graph that could be constructed. If the dictionary
implies that the letter a comes before the letter c in the alphabet, 
the two vertices will be created such that the a->c path exists, but not
the other way around.

The reason a directed graph was used was because this allowed for a
topological sort. Once this graph is topologically sorted, we have:

a -> b -> c -> z

Meaning a,b,c,z occur in the alphabet in that order.

Once the order of the alphabet is established, the unsorted input file
can be sorted in the correct order.

So the program reads through the sorted dictionary one pair at a time.
From each word, pairs of characters are compared - the first character
of each word, then the second character of each word, and so on. The 
first pair of characters that do not match are then loaded into the
directed character map. A path is created pointing from the first char
to the second if the path does not already exist. If the map does not
already contain a vertex containing either character, then a node is
created. Afterwards, a topological sort is called to find the correct
order of the alphabet.

The unsorted file is then read one word at a time and inserted into a
sorted array using the alphabet order as reference. The sorted array is
then printed to the specified output file.