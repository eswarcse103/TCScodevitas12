import java.util.*;

public class JustifyWords {

    public static int maxWords(int k, String[] words, int n, int m) {
        int[][] dp = new int[k + 1][n + 1];

        for (int i = 0; i <= k; i++) {
            Arrays.fill(dp[i], 0);
        }

        for (int i = 1; i <= k; i++) {
            for (int lines = 1; lines <= n; lines++) {
                dp[i][lines] = dp[i - 1][lines]; // Skip the current word

                int lineLength = 0;
                int wordCount = 0;

                // Try to fit words ending at the current word
                for (int j = i; j >= 1; j--) {
                    int wordLength = words[j - 1].length();
                    if (lineLength == 0) {
                        lineLength = wordLength; // First word on the line
                    } else {
                        lineLength += wordLength + 1; // Add space and word
                    }

                    if (lineLength > m) break;

                    wordCount++;
                    dp[i][lines] = Math.max(dp[i][lines], dp[j - 1][lines - 1] + wordCount);
                }
            }
        }

        return dp[k][n];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input number of words
        int k = sc.nextInt();
        sc.nextLine();

        // Input words
        String[] words = new String[k];
        for (int i = 0; i < k; i++) {
            words[i] = sc.nextLine();
        }

        // Input N and M
        int n = sc.nextInt();
        int m = sc.nextInt();

        // Output the maximum number of words
        System.out.println(maxWords(k, words, n, m));
    }
}
