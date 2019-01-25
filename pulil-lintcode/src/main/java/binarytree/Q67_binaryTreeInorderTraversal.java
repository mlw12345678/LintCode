package binarytree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author pupil
 * @version V1.0
 * @Title
 * @Description
 * @date 2019-01-25 下午3:56
 */
public class Q67_binaryTreeInorderTraversal {
    /**
     * Question67
     * 给出一棵二叉树,返回其中序遍历
     * 给出二叉树 {1,#,2,3},
     * 1
     *  \
     *   2
     *  /
     * 3
     * 返回 [1,3,2].
     */

    /**
     *  转一篇简书： https://www.jianshu.com/p/8359c1369066
     *
     *  二叉树的处理都是先处理根，处理左节点，处理右节点，在非递归的条件下，前序、中序、后续唯一的区别就是何时打印根的值
     *  前序：碰到根节点立即打印
     *  中序：处理完左子树后打印
     *  后续：处理完右子树后打印
     *
     *  本题是中序，因此算法如下
     *  1、碰到根节点，压入栈保存，处理左节点
     *  2、左节点的左节点不为空，继续入栈
     *  3、左节点为空，此时左子树已经搞完了，取出栈顶元素，打印，再处理其右子树
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        if(root == null)
            return null;
        List<Integer> resultList = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode tempNode = null;
        while(root != null || !stack.isEmpty()) {
            if(root != null) {
                stack.push(root);
                root = root.left;
            } else {
                tempNode = stack.pop();
                resultList.add(tempNode.val);
                root = tempNode.right;
            }
        }
        return resultList;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        List<Integer> result = new Q67_binaryTreeInorderTraversal().inorderTraversal(root);
        System.out.println(result.toString());
    }
}
