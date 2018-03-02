public class StringParser {

	private class Pair {
		int index;
		int value;

		public Pair(int index, int value) {
			this.index = index;
			this.value = value;
		}
	}

	public String[][] parse(String CFN) {
		String board[][] = new String[6][7];
		int length = 0;
		for (int i = 0; i < CFN.length(); i++) {
			if (checkNum(CFN.charAt(i))) {
				Pair current = parseNumber(CFN, i);
				i = current.index;
				length += current.value;
			} else {
				length++;
			}
		}
		if (length != 42 ) {
			throw new RuntimeException("Invalid String");
		}

		int currentRow = 0;
		int currentCol = 0;
		for (int i = 0; i < CFN.length(); i++) {
			if (checkNum(CFN.charAt(i))) {
				Pair current = parseNumber(CFN, i);
				i = current.index;
				int iterations = Math.min(7 - currentCol, current.value);
				int c = current.value;
				char val = CFN.charAt(i);
				while (c > 0) {
					while (c > 0 && iterations > 0) {
						board[currentRow][currentCol++] = val + "";
						c--;
						iterations--;
					}
					if (c <= 0) {
						break;
					}
					currentRow++;
					currentCol = 0;
					iterations = Math.min(7 - currentCol, c);
				}
			} else {
				if (currentCol < 7) {
					board[currentRow][currentCol] = CFN.charAt(i) + "";
					currentCol++;
				} else {
					currentRow++;
					board[currentRow][0] = CFN.charAt(i) + "";
					currentCol = 1;

				}
			}
		}

		return board;
	}

	private Pair parseNumber(String CFN, int i) {
		String currNum = CFN.charAt(i) + "";
		i++;
		while (i < CFN.length() && checkNum(CFN.charAt(i))) {
			currNum += CFN.charAt(i);
			i++;
		}
		return new Pair(i, Integer.parseInt(currNum));
	}

	private boolean checkNum(char current) {
		return (current - '0' >= 0 && current - '0' <= 9);
	}

	public static void main(String args[][]) {
		String st = "10_r4_brbrbr_3b2rb_b2r2br_r2b3rvc";
		StringParser sp = new StringParser();
		System.out.println(sp.parse(st));
	}

}
