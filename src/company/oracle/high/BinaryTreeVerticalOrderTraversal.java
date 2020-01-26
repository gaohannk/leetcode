package company.oracle.high;

import leetcode.common.TreeNode;

import java.util.*;

public class BinaryTreeVerticalOrderTraversal {
    public static List<List<Integer>> verticalOrder(TreeNode root) {
        if (root == null) {
            return new LinkedList<>();
        }

        Map<Integer, List<Integer>> map = new TreeMap<>();
        List<List<Integer>> res = new LinkedList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        Queue<Integer> distQueue = new LinkedList<>();
        distQueue.add(0);

        int max = Integer.MIN_VALUE;

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                TreeNode current = queue.poll();

                int dist = distQueue.poll();

                List<Integer> currentList = map.getOrDefault(dist, new LinkedList<Integer>());
                currentList.add(current.val);
                map.put(dist, currentList);

                if (current.left != null) {
                    queue.add(current.left);
                    distQueue.add(dist - 1);
                }

                if (current.right != null) {
                    queue.add(current.right);
                    distQueue.add(dist + 1);
                }
                size--;
            }
        }

        // left to right, from maximum -ve distance from root to maximum +ve distance from root
        for (int i : map.keySet()) {
            res.add(map.get(i));
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        System.out.println(verticalOrder(root));
    }
}
