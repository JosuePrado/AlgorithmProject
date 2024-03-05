import java.util.ArrayList;
import java.util.SimpleTimeZone;

public class PlagiarismReview {

    private String file1;
    private String file2;
    private String[] text1Arr;
    private String[] text2Arr;

    public PlagiarismReview(String path1, String path2) {
        FileManager manager = new FileManager();
        manager.readFiles(path1, path2);
        file1 = manager.getFile1Contend();
        file2 = manager.getFile2Contend();
        text1Arr = file1.split(" ");
        text2Arr = file2.split(" ");
    }

    public int similitudePercentage() {
        int m = text1Arr.length;
        int n = text2Arr.length;
        int longestText = longestText();
        int minNumWords = Math.min(m, n);

        int percentage = (longestText * 100) / minNumWords;
        return percentage;
    }

    private int longestText() {

        String[] text2Correct = correctMisspelling().split(" ");
        int m = text1Arr.length;
        int n = text2Correct.length;

        int[][] lcs = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1Arr[i - 1].equals(text2Correct[j - 1])) {
                    lcs[i][j] = lcs[i - 1][j - 1] + 1;
                } else {
                    lcs[i][j] = Math.max(lcs[i - 1][j], lcs[i][j - 1]);
                }
            }
        }
        return lcs[m][n];
    }

    private String correctMisspelling() {
        ArrayList<String> misspellings = allMisspelling();
        String correctText2 = file2;

        for (int i = 1; i < misspellings.size(); i += 2) {
            String misspelling = misspellings.get(i);
            correctText2 = correctText2.replace(misspelling, misspellings.get(i - 1));
        }

        return correctText2;
    }

    public ArrayList<String> allMisspelling() {
        ArrayList<String> misspelling = new ArrayList<>();

        for (String word1 : text1Arr) {
            for (String word2 : text2Arr) {
                int maxLength = Math.max(word1.length(), word2.length());
                if (maxLength > 3) {
                    int similitude = editDistance(word1, word2);
                    int percentage = ((maxLength - similitude) * 100) / maxLength;
                    if (percentage != 100 && percentage >= 55) {
                        misspelling.add(word1);
                        misspelling.add(word2);
                    }
                }
            }
        }

        return misspelling;
    }

    private int editDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1],
                            Math.min(dp[i][j - 1], dp[i - 1][j]));
                }
            }
        }

        return dp[m][n];
    }

}
