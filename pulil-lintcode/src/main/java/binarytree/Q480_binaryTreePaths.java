package binarytree;

import sun.applet.Main;

import java.util.*;

/**
 * @author pulil
 * @version V1.0
 * @Title
 * @Description
 * @date 2019-01-28 下午3:53
 */
public class Q480_binaryTreePaths {
    /**
     *  给一棵二叉树，找出从根节点到叶子节点的所有路径。
     *  输入：
     *    1
         / \
        2   3
         \
          5
        输出：
     *  [
         "1->2->5",
         "1->3"
         ]
     */

    /**
     * Solution:非递归算法
     * 思路：题目需要 遍历二叉树所有的路径，该问题是深度优先搜索，广度优先所有借助于数据结构"队列"，深度优先搜索则借助数据结构"栈"，
     *
     *   前序遍历的深度优先算法很简单，将所有左儿子入栈并打印直到左儿子为null，为null后弹出栈顶元素，即上一个节点，转到其右儿子
     *   本题需要遍历所有路径，那么就需要用pre保存上一个处理过的节点，然后在是否转向右子树的时候判断右子树是不是处理过了，
     *   如果没处理过，继续处理右子树，如果处理过了，就继续pop，直到右子树么有被处理过
     *
     *   步骤：
     *   1、先将根入栈
     *   2、如果左儿子非null，入栈
     *   3、左儿子为空，取出栈顶元素，如果右儿子非空且没被处理过，继续处理右儿子
     *   4、否则，弹出栈顶元素，判断是否是叶子节点，是打印
     *   5、记录当前处理元素，当前处理元素置空
     */
    public List<String> binaryTreePaths(TreeNode root) {
        // write your code here
        List<String> result = new ArrayList<String>();
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
                        String res = "";
                        for(TreeNode t : stack) {
                            res = res + "->" + t.val;
                        }
                        res += "->" + current.val;
                        result.add(res.substring(2,res.length()));
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
        List<String> result = new Q480_binaryTreePaths().binaryTreePaths(root);
        System.out.println(result.toString());
    }
}
