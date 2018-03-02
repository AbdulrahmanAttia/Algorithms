import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Solution {

	private int[] sums;

	private class Pair {
		int num;
		int idx;

		public Pair(int num, int idx) {
			this.num = num;
			this.idx = idx;
		}
	}

	public int[] findSuOfLessNumsOnRight(int[] nums) {
		sums = new int[nums.length];

		Pair[] pairs = new Pair[nums.length];
		for (int i = 0; i < nums.length; i++)
			pairs[i] = new Pair(nums[i], i);

		int n = nums.length;
		mergeSort(0, n - 1, pairs);
		return sums;
	}

	public void mergeSort(int lo, int hi, Pair[] nums) {
		if (lo >= hi) {
			return;
		}
		int mid = lo + (hi - lo) / 2;
		mergeSort(lo, mid, nums);
		mergeSort(mid + 1, hi, nums);
		merge(lo, mid, hi, nums);
	}

	public void merge(int lo, int mid, int hi, Pair[] a) {
		Pair[] aux = new Pair[a.length];

		int i = lo;// 0
		int j = mid + 1;// a.length if merging two arrays

		for (int k = lo; k <= hi; k++)
			aux[k] = a[k];

		int sum = 0;
		for (int k = lo; k <= hi; k++) {
			Integer index = aux[i].idx;
			if (j > hi) {
				sums[index] += sum;
				a[k] = aux[i++];
			} else if (i > mid) {
				a[k] = aux[j++];
				sum += a[k].num;
			} else if (aux[i].num <= aux[j].num) {
				sums[index] += sum;
				a[k] = aux[i++];
			} else {
				a[k] = aux[j++];
				sum += a[k].num;
			}
		}
	}
}
