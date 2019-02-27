package binarytree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author pulil
 * @version V1.0
 * @Title
 * @Description
 * @date 2019-01-29 下午3:12
 */
public class Q68_binaryTreePostorderTraversal {
    /**
     * 给出一棵二叉树，返回其节点值的后序遍历。
     * 给出一棵二叉树 {1,#,2,3},
     *  1
         \
          2
         /
        3
        返回 [3,2,1]
     */

    /**
     * Solution:
     * 二叉树的遍历只要记住
     * 1、借助栈
     * 2、前序遍历：入栈时访问
     * 3、中序遍历：弹出栈时访问
     *
     * 4、后续遍历：关键点在于，要先访问完左右子树后，才能访问当前节点
     *    那么就用一个pre节点标记刚访问过的节点，如果当前节点的右儿子为空或者右儿子是刚刚访问过的节点，此时弹出并访问栈顶元素
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        // write your code here
        List<Integer> result = new ArrayList<Integer>();
        if(root == null)
            return result;
        TreeNode cur = root;
        TreeNode pre = null;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while(cur != null || !stack.isEmpty()) {
            //所有左子树压入站
            if(cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                //左子树压入完成，获取栈顶元素
                cur = stack.peek();
                //右子树为空或右子树已经访问过了
                if(cur.right == null || cur.right == pre) {
                    cur = stack.pop();
                    result.add(cur.val);
                    pre = cur;
                    //cur置空，避免cur再次入栈导致循环
                    cur = null;
                } else {
                    //有右儿子没被访问，在下一次循环时入栈
                    cur = cur.right;
                }
            }
        }
        return result;
    }

    @Test
    public void test() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.right = new TreeNode(5);
        root.left.left = new TreeNode(4);
        root.right = new TreeNode(3);
        List<Integer> result = postorderTraversal(root);
        System.out.println(result.toString());
    }
}
