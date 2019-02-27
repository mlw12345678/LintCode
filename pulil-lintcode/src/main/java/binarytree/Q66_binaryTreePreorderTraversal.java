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
public class Q66_binaryTreePreorderTraversal {
    /**
     * 给出一棵二叉树，返回其节点值的前序遍历。
     * 给出一棵二叉树 {1,#,2,3},
     *  1
         \
          2
         /
        3
        返回 [1,2,3]
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
    public List<Integer> preorderTraversal(TreeNode root) {
        // write your code here
        List<Integer> result = new ArrayList<Integer>();
        if(root == null)
            return result;
        TreeNode cur = root;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while(cur != null || !stack.isEmpty()) {
            if(cur != null) {
                stack.push(cur);
                result.add(cur.val);
                cur = cur.left;
            } else {
                cur = stack.pop();
                cur = cur.right;
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
        List<Integer> result = preorderTraversal(root);
        System.out.println(result.toString());
    }
}
