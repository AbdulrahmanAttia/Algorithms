public class MaxEvenSum {

	static int maxEven;

	// Pair.f = maxEvenSum, Pair.s - maxOddSum
	// e e e, o o e,
	public Pair getMaxEvenSum(TreeNode root) {
		if (root == null) {
			return new Pair(0, 0);
		}
		Pair left = getMaxEvenSum(root.left);
		Pair right = getMaxEvenSum(root.right);
		if (root.left != null && root.right != null)
			maxEven = Math.max(maxEven, Math.max(left.f, right.f));
		Pair ret = new Pair(-120, -121);// set this with min number in the
										// tree or handle Integer.min
		if (root.val % 2 == 0) {
			maxEven = Math.max(maxEven, root.val + left.s + right.s);
			maxEven = Math.max(maxEven, root.val + left.f + right.f);
			maxEven = Math.max(maxEven,
					Math.max(root.val + right.f, root.val + left.f));
			maxEven = Math.max(maxEven, root.val);
			ret.f = root.val;
			if (root.left != null || root.right != null)
				ret.f = Math.max(ret.f,
						Math.max(ret.f + left.f, ret.f + right.f));
			if (root.left != null || root.right != null)
				ret.s = Math.max(root.val + left.s, root.val + right.s);
		} else {
			if (root.left != null && root.right != null)
				maxEven = Math.max(maxEven, root.val + left.s + right.f);
			if (root.left != null && root.right != null)
				maxEven = Math.max(maxEven, root.val + left.f + right.s);
			if (root.right != null)
				maxEven = Math.max(maxEven, root.val + right.s);
			if (root.left != null)
				maxEven = Math.max(maxEven, root.val + left.s);
			ret.s = root.val;
			if (root.left != null || root.right != null)
				ret.f = Math.max(ret.s,
						Math.max(ret.s + left.f, ret.s + right.f));
			if (root.left != null || root.right != null)
				ret.f = Math.max(root.val + left.s, root.val + right.s);
		}
		// System.out.println(root.val + " " + ret.f + " " + ret.s);
		return ret;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		maxEven = -120;
		TreeNode root = new TreeNode(-7);
		TreeNode left = new TreeNode(-2);
		TreeNode right = new TreeNode(-5);
		left.left = new TreeNode(-114);
		left.right = new TreeNode(-101);
		right.right = new TreeNode(0);
		right.left = new TreeNode(-17);
		root.left = left;
		root.right = right;
		new MaxEvenSum().getMaxEvenSum(root);
		System.out.println(maxEven);
	}

}
