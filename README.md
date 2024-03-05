Dynamic Programming (DP) was chosen to solve the problem because it efficiently handles the subproblems by storing their solutions, thus avoiding redundant computations. In this particular case, the problem of finding the edit distance between two strings to check for misspellings, and using the LCS algorithm to calculate the percentage of similarity that exists between the 2 texts;


The complexity of the DP solution to calculate the edit distance between two strings is O(m*n), where 'm' and 'n' are the lengths of the two strings respectively. This complexity arises due to the nested loops used to populate the DP table.

Yes, there are non-DP solutions to find the edit distance between two strings, such as recursive approaches like the Wagner-Fischer algorithm. However, these approaches can be inefficient due to redundant computations.