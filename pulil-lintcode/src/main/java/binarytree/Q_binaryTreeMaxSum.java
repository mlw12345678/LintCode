package binarytree;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author pulil
 * @version V1.0
 * @Title
 * @Description
 * @date 2019-02-27 上午12:35
 */
public class Q_binaryTreeMaxSum {
    /**
     * Question:
     * 二叉树的最大路径和问题，本题并未出现在lintcode上，而是出现在leetCode124题
     *
     * 样例1：
     *  样例：
     *  给定一棵二叉树
     *           -1
     *          / \
     *         2   3
     *        / \
     *       4   5
     *  输出：11
     *
     *  Solution：
     *  参考：@https://blog.csdn.net/linhuanmars/article/details/22969069
     *  1、先考虑叶子节点A：由于其左右子树都为null，那么最大路径=0+0+A.val，如果比maxValue大，更新maxValue
     *  2、非叶子节点B的计算分为两部分
     *      第一：计算B的左右子树的最大值，左子树最大值+右子树最大值+B.val > maxValue，说明最大路径和暂时产生于节点B，更新maxValue
     *      第二：如果B存在父节点，那么将 左子树的最大值+B.val 或者 右子树最大值+B.val 返回供上层节点计算左右子树使用
     *      （返回值取左右最大值+B.val的原因是，假设B是上层节点C的左子树，那么C左子树的最大值肯定只能是经过B的一条路径，即B的左或右子树的最大值+B.val）
     *  3、本算法的关键点就是66行和68行
     *      68行返回值，用来标记经过当前节点的唯一一条最大路径和，供上层节点计算最大值使用
     *      66行比较，用来判断以当前节点为根的左右子树的最大路径和是否是整个树中最大的路径和（如果当前节点最大路径和>maxValue，更新maxValue）
     *
     */

    //实例变量，记录结果
    private int maxValue = Integer.MAX_VALUE;

    public int maxPathSum(TreeNode root) {
        if(root == null)
            return 0;
        maxValue = Integer.MIN_VALUE;
        maxSum(root);
        return maxValue;
    }

    /**
     * 求任意节点最大路径和
     * @param root
     * @return
     */
    public int maxSum(TreeNode root) {
        if(root == null)
            return 0;
        //因为节点的值可以为负数，所以最大值取0和子树最大值的较大者
        int left = Math.max(0,maxSum(root.left));
        int right = Math.max(0,maxSum(root.right));
        //如果将当前root作为根节点，那么最大值是root.val+左子树最大值+右子树最大值
        maxValue = Math.max(maxValue,left + right + root.val);
        //只能返回左右子树中较大值+root.val
        return Math.max(0,root.val + Math.max(left,right));
    }

    @Test
    public void test() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(-2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(10);
        root.left.right = new TreeNode(8);
        int max = maxPathSum(root);
        System.out.println(max);

    }
}
