import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int size = Integer.parseInt(br.readLine());

		TreeNode[] node = new TreeNode[size+1];

		for(int i = 1;i<=size;i++) {
			String[] nowValue = br.readLine().split(" ");
			TreeNode tn = new TreeNode(nowValue[0]);
			int index = nowValue[0].charAt(0) - 'A' + 1;

			String left = nowValue[1];
			String right = nowValue[2];
			if(!(left.equals("."))) {
				int leftNode = left.charAt(0) - 'A' + 1;
				tn.left = leftNode;
			}
			if(!(right.equals("."))) {
				int rightNode = right.charAt(0) - 'A' + 1;
				tn.right = rightNode;
			}
			node[index] = tn;
		}
		preorder(1, node);
		System.out.println();
		inorder(1, node);
		System.out.println();
		postorder(1, node);
	}

	static class TreeNode {
		String val;
		public int left = 0;
		public int right = 0;

		TreeNode (String val) {
			this.val = val;
		}
	}

	static void preorder(int idx, TreeNode[] node) {
		if (idx == 0) return;
		System.out.print(node[idx].val);
		preorder(node[idx].left, node);
		preorder(node[idx].right, node);
	}

	static void inorder(int idx, TreeNode[] node) {
		if (idx == 0) return;
		inorder(node[idx].left, node);
		System.out.print(node[idx].val);
		inorder(node[idx].right, node);
	}

	static void postorder(int idx, TreeNode[] node) {
		if (idx == 0) return;
		postorder(node[idx].left, node);
		postorder(node[idx].right, node);
		System.out.print(node[idx].val);
	}
}
