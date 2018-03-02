import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MinFixes {

	public int fix(int g[][], int n, int m) {
		int left = 0;
		int right = 0;
		int count[] = new int[n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (g[i][j] == 0) {
					count[i]++;
				}
			}
		}
		int cnt = count[n - 1];
		int steps = 0;
		for (int i = 0; i < m; i++) {
			steps++;
			if (g[n - 1][i] == 0) {
				left = steps;
				count[n - 1]--;
				if (count[n - 1] == 0) {
					break;
				}
			}

		}
		steps = 0;
		count[n - 1] = cnt;
		for (int i = m - 1; i >= 0; i--) {
			steps++;
			if (g[n - 1][i] == 0) {
				right = steps;
				count[n - 1]--;
				if (count[n - 1] == 0) {
					break;
				}
			}

		}
		left--;
		right--;

		for (int i = n - 2; i >= 0; i--) {
			if (count[i] != 0) {
				cnt = count[i];
				steps = 0;
				int lastLeft = left;
				for (int j = 0; j < m; j++) {
					steps++;
					if (g[i][j] == 0) {
						count[i]--;
						if (count[i] == 0) {
							left = Math.min(2 * steps - 1 + left, m + right);
							break;
						}
					}
				}
				count[i] = cnt;
				steps = 0;

				if (i > 0) {
					for (int j = m - 1; j >= 0; j--) {
						steps++;
						if (g[i][j] == 0) {
							count[i]--;
							if (count[i] == 0) {
								right = Math.min(2 * steps - 1 + right, m
										+ lastLeft);
								break;
							}
						}
					}
					count[i] = cnt;
				}

			} else {
				int lastLeft = left;
				left = Math.min(1 + left, m + right);
				if (i > 0) {
					right = Math.min(1 + right, m + lastLeft);
				}
			}

		}
		return left;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MinFixes m = new MinFixes();
		int[][] room = new int[][] { { 1, 1, 1, 1, 1 }, { 1, 0, 1, 1, 0 },
				{ 1, 1, 0, 1, 1 }, { 1, 1, 1, 1, 0 }, { 1, 1, 1, 1, 1 } };
		System.out.println(m.fix(room, room.length, room[0].length));

	}

}
