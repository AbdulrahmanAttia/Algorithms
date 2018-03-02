import java.util.*;

public class SeatAssigner {

	private int n;
	private HashMap<Integer, Student> map = new HashMap<>();
	private PriorityQueue<Student> pq;

	public SeatAssigner(int n) {
		this.n = n;
		Student senRight = new Student(null, null, -2, n);
		Student senLeft = new Student(null, senRight, -1, -1);
		senLeft.left = senLeft;
		senRight.right = senRight;
		senLeft.right = senRight;
		senRight.left = senLeft;
		pq = new PriorityQueue<>(new Comparator<Student>() {
			public int compare(Student s1, Student s2) {
				if (s2.calcRightSz() == s1.calcRightSz())
					return s1.pos - s2.pos;
				return (s2.calcRightSz() - s1.calcRightSz());
			}
		});
		map.put(-1, senLeft);
		map.put(-2, senRight);
		pq.add(senLeft);
		pq.add(senRight);
	}

	class Student {
		public Student left;
		public Student right;
		public int id;
		public int pos;

		public Student(Student left, Student right, int id, int pos) {
			this.left = left;
			this.right = right;
			this.id = id;
			this.pos = pos;
		}

		public int calcRightSz() {
			if (right == null) {
				return 0;
			} else {
				return right.pos - pos;
			}
		}
	}

	public void removeStudent(int id) throws InterruptedException {
		if (map.containsKey(id) == false) {
			throw new InterruptedException();
		}
		Student remove = map.remove(id);
		Student left = remove.left;
		Student right = remove.right;
		left = updateLeft(left, right);
		right = updateRight(left, right);
		pq.remove(remove);
		map.put(left.id, left);
		map.put(right.id, right);
		pq.add(left);
		pq.add(right);
	}

	public int addStudent(int id) throws InterruptedException {
		Student left = pq.peek();
		Student right = left.right;
		if (left.calcRightSz() == 0) {
			throw new InterruptedException();
		}
		int ret = -1;
		if (left.left == left) {
			addStudent(left, right, id, 0);
			ret = 0;

		} else if (right.right == right) {
			addStudent(left, right, id, n - 1);
			ret = n - 1;
		} else {
			int mid = (left.pos + right.pos) / 2;
			addStudent(left, right, id, mid);
			ret = mid;
		}
		return ret;
	}

	public void addStudent(Student left, Student right, int id, int pos) {
		Student newStudent = new Student(left, right, id, pos);
		left = updateLeft(left, newStudent);
		right = updateRight(newStudent, right);
		map.put(id, newStudent);
		pq.add(left);
		pq.add(right);
		pq.add(newStudent);
	}

	public Student updateRight(Student left, Student right) {
		pq.remove(right);
		right.left = left;
		return right;
	}

	public Student updateLeft(Student left, Student right) {
		pq.remove(left);
		left.right = right;
		return left;
	}

	public static void main(String args[]) throws InterruptedException {
		/*
		 * 0 2 1 3 2 1 0 4 3 3 3 3 5 5 2 1
		 */
		int[][] land = { { 0, 2, 1, 3 }, { 2, 1, 0, 4 }, { 3, 3, 3, 3 },
				{ 5, 5, 2, 1 } };

		// BasinsCounter bc = new BasinsCounter();
		// System.out.println(bc.countBasins(land));

		int a[] = { 12, 9, 10, 9, 3, 12, 11, 3, 5, -2 };
		Solution s = new Solution();
		int[] sums = s.findSuOfLessNumsOnRight(a);

		for (int i = 0; i < sums.length; i++) {
			System.out.print(sums[i] + " ");
		}

	}
}