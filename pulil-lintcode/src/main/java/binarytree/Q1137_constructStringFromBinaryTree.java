package binarytree;

import org.junit.Test;

/**
 * @author pulil
 * @version V1.0
 * @Title
 * @Description
 * @date 2019-02-25 下午10:57
 */
public class Q1137_constructStringFromBinaryTree {
    /**
     * Question:你需要通过一棵二叉树的先序遍历，构建一个包含括号和整数的字符串。
     * 空结点需要用空括号对"()"来表示。
     * 同时你需要忽略掉所有的不影响字符串和原始二叉树一对一映射关系的空括号对。
     *
     * 样例1：
     * 输入: Binary tree: [1,2,3,4]
     *      1
     *    /   \
     *   2     3
     *  /
     *4
     * 输出: "1(2(4))(3)"
     * 解释: 一开始应该是 "1(2(4)())(3()())"，
     * 但是你需要忽略掉所有的不必要的空括号对.
     * 然后就变成了 "1(2(4))(3)".
     *
     * 样例2
     * 输入: Binary tree: [1,2,3,null,4]
     *       1
     *     /   \
     *    2     3
     *     \
     *      4
     *
     * 输出: "1(2()(4))(3)"
     *
     * 解释: 几乎和第一个样例相同,
     * 除了我们不能忽略第一个括号对，否则将会破坏输入和输出之间的一对一映射关系。
     */

    /**
     * Question:
     * 本题关键在于，如果右子树不为空，左子树为空的时候需要打印()
     * 当左子树为空的时候，且右子树不为空的时候，打印空()即可
     *
     * 步骤：
     * 1、打印根
     * 2、如果左子树不为空，用括号包上递归处理左子树
     * 3、如果左子树为空且右子树不为空，打印空()
     * 4、如果右子树不为空，括号包上递归处理右子树
     */
    public String tree2str(TreeNode t) {
        if(t == null)
            return "";
        String result = String.valueOf(t.val);
        if(t.left != null) {
            result += "(" + tree2str(t.left) + ")";
        } else if(t.right != null) {
            result += "()";
        }

        if(t.right != null) {
            result += "(" + tree2str(t.right) + ")";
        }
        return result;
    }

    @Test
    public void test() {
        TreeNode t1 = new TreeNode(1);
        t1.left = new TreeNode(2);
        t1.right = new TreeNode(3);
        t1.left.left = new TreeNode(4);
        System.out.println(tree2str(t1));

    }

}
