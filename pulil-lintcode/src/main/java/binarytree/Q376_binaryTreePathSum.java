package binarytree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author pulil
 * @version V1.0
 * @Title
 * @Description
 * @date 2019-01-29 上午12:52
 */
public class Q376_binaryTreePathSum {
    /**
     * 给定一个二叉树，找出所有路径中各节点相加总和等于给定 目标值 的路径。
     * 一个有效的路径，指的是从根节点到叶节点的路径。
     * 样例
     * 给定一个二叉树，和 目标值 = 5:
     *    1
         / \
         2   4
         / \
         2   3
         返回：

         [
         [1, 2, 2],
         [1, 4]
         ]
     */

    /**
     * Solution
     * 本题与Q480题一样，都是用到深度优先搜索查询全路径，在Q480的基础上再加一点点限制条件，即几个节点的和等于给定值
     */
    public List<List<Integer>> binaryTreePathSum(TreeNode root, int target) {
        // write your code here
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(root == null)
            return result;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode current = root;
        TreeNode pre = null;
        while(current != null || !stack.isEmpty()) {
            if(current != null) {
                stack.push(current);
                current = current.left;
            } else {
                current = stack.peek();
                if(current.right != null && pre != current.right) {
                    current = current.right;
                } else {
                    current = stack.pop();
                    if(current.left == null && current.right == null) {
                        int sum = 0;
                        List<Integer> res = new ArrayList<Integer>();
                        for(TreeNode t : stack) {
                            sum += t.val;
                            res.add(t.val);
                        }
                        sum += current.val;
                        res.add(current.val);
                        if(sum == target) {
                            result.add(res);
                        }
                    }
                    pre = current;
                    current = null;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.right = new TreeNode(5);
        root.left.left = new TreeNode(4);
        root.right = new TreeNode(3);
        List<List<Integer>> result = new Q376_binaryTreePathSum().binaryTreePathSum(root,7);
        System.out.println(result.toString());
    }
}
