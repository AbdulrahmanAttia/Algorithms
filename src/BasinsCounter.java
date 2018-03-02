import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class BasinsCounter {

	public ArrayList<Integer> countBasins(int[][] land) {
		boolean[][] visited = new boolean[land.length][land[0].length];
		ArrayList<Integer> basins = new ArrayList<>();
		for (int i = 0; i < land.length; i++) {
			for (int j = 0; j < land[0].length; j++) {
				if (!visited[i][j] && isSink(land, i, j)) {
					basins.add(basinDFS(land, visited, i, j));
				}
			}
		}

		Collections.sort(basins, new Comparator<Integer>() {
			public int compare(Integer i1, Integer i2) {
				return i2 - i1;
			}
		});

		return basins;
	}

	public boolean isSink(int[][] land, int i, int j) {
		if (i + 1 < land.length && land[i][j] >= land[i + 1][j])
			return false;
		if (i - 1 >= 0 && land[i][j] >= land[i - 1][j])
			return false;
		if (j + 1 < land[0].length && land[i][j] >= land[i][j + 1])
			return false;
		if (j - 1 >= 0 && land[i][j] >= land[i][j - 1])
			return false;

		return true;
	}

	public int basinDFS(int[][] land, boolean[][] visited, int i, int j) {
		int count = 0;
		if (visited[i][j]) {
			return count;
		}
		count++;
		visited[i][j] = true;
		if (i + 1 < land.length && land[i][j] == minNeighbor(land, i + 1, j)) {
			count += basinDFS(land, visited, i + 1, j);
		}
		if (i - 1 >= 0 && land[i][j] == minNeighbor(land, i - 1, j)) {
			count += basinDFS(land, visited, i - 1, j);
		}
		if (j + 1 < land.length && land[i][j] == minNeighbor(land, i, j + 1)) {
			count += basinDFS(land, visited, i, j + 1);
		}
		if (j - 1 >= 0 && land[i][j] == minNeighbor(land, i, j - 1)) {
			count += basinDFS(land, visited, i, j - 1);
		}

		return count;
	}

	public int minNeighbor(int[][] land, int i, int j) {
		int min = Integer.MAX_VALUE;
		if (i + 1 < land.length) {
			min = Math.min(min, land[i + 1][j]);
		}
		if (i - 1 >= 0) {
			min = Math.min(min, land[i - 1][j]);
		}
		if (j + 1 < land[0].length) {
			min = Math.min(min, land[i][j + 1]);
		}
		if (j - 1 >= 0) {
			min = Math.min(min, land[i][j - 1]);
		}

		return min;
	}
}
