package binarytree;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author pulil
 * @version V1.0
 * @Title
 * @Description
 * @date 2019-02-26 上午12:05
 */
public class Q1181_diameterOfBinaryTree {
    /**
     * Question:
     *  给定一颗二叉树，您需要计算树的直径长度。 二叉树的直径是树中任意两个节点之间最长路径的长度。
     *  此路径不一定会通过树根。
     *
     *  样例：
     *  给定一棵二叉树
     *           1
     *          / \
     *         2   3
     *        / \
     *       4   5
     *  输出：3
     *  因为，这是路径[4,2,1,3] 或者 [5,2,1,3]的长度.
     */

    /**
     * Solution:
     * 思路：本题的根本在于考察数的高度
     *  对于每个节点，例如根节点，计算左子树的高度，计算右子树的高度，相加，就是经过根节点的路径长度
     *  计算所有节点的左右子树的高度和
     *  找到最大值即可
     *
     *  步骤：
     *  1、遍历整颗树（借助队列，广度优先搜索）
     *  2、对于每个节点，计算左右子树的高度，相加，如果比初始值max大，替换max为左右子树和
     */

    public int diameterOfBinaryTree(TreeNode root) {
        if(root == null)
            return 0;
        int max = 0;
        //自顶向下遍历二叉树所有节点，借助队列
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            TreeNode temp = queue.poll();
            //左子树高度
            int left = height(temp.left);
            //右子树高度
            int right = height(temp.right);
            //找到最大的max
            if(left + right > max)
                max = left + right;
            if(temp.left != null)
                queue.offer(temp.left);
            if(temp.right != null)
                queue.offer(temp.right);
        }
        return max;
    }

    /**
     * 计算一个树的高度
     * @param node
     * @return
     */
    public int height(TreeNode node) {
        if(node == null)
            return 0;
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);
        return Math.max(leftHeight,rightHeight) + 1;
    }

    @Test
    public void test() {
        TreeNode t = new TreeNode(1);
        t.left = new TreeNode(2);
        t.right = new TreeNode(3);
        t.left.left = new TreeNode(4);
        t.left.right = new TreeNode(5);
        System.out.println(diameterOfBinaryTree(t));
    }
}
