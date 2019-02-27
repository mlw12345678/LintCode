package binarytree;

import org.junit.Test;
import sun.jvm.hotspot.code.ConstantOopReadValue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author pulil
 * @version V1.0
 * @Title
 * @Description
 * @date 2019-02-26 下午10:56
 */
public class Q481_binaryTreeLeafSum {
    /**
     * Question:计算二叉树的叶子节点之和
     *
     * 样例1：
     * 输入：
     *       1
     *      / \
     *     2   3
     *   /
     *  4
     *  输出：7
     *
     *  样例2：
     *  输入：
     *   1
     *    \
     *     3
     *   输出：3
     */

    /**
     * Solution:
     * 关键在于识别所有的叶子节点，即左右子树都为空，就把叶子节点加一下
     * 本题采用广度优先搜索机制，每次节点的时候判断一下是否为叶子节点
     * 当然也可以采用递归
     */
    public int leafSum(TreeNode root) {
        if(root == null)
            return 0;
        int result = 0;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            TreeNode temp = queue.poll();
            if(temp.left == null && temp.right == null)
                result += temp.val;
            if(temp.left != null)
                queue.offer(temp.left);
            if(temp.right != null)
                queue.offer(temp.right);
        }
        return result;
    }

    @Test
    public void test() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        int result = leafSum(root);
        System.out.println(result);
    }
}
