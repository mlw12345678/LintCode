package binarytree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author pulil
 * @version V1.0
 * @Title
 * @Description
 * @date 2019-01-25 下午6:08
 */
public class Q69_binaryTreeLevelOrderTraversal {
    /**
     * Question69
     * 二叉树的层次遍历
     * 给出一棵二叉树，返回其节点值的层次遍历（逐层从左往右访问）
     *
     * 样例
     * 给一棵二叉树 {3,9,20,#,#,15,7}
     *    3
         / \
        9  20
          /  \
         15   7
     * [
         [3],
         [9,20],
         [15,7]
       ]
     *  挑战1：只使用一个队列去实现它
     *  挑战2：用BFS算法来做
     */

    /**
     * Solution
     *
     * BSF广度优先算法，一层一层的进行遍历
     *
     * 二叉树的层次遍历需要借助一个队列
     * 由于队列的特性是先进先出，在出队的时候访问元素，该算法就取决于元素何时入队列
     * 1、先将根节点入队
     * 2、如果当前节点是队列头节点，出队并访问
     * 3、如果当前节点左节点不为空则左节点入队
     * 4、如果当前节点右节点不为空，将右节点入队,所以出队顺序也是按行从左到右依次出队
     *
     * 层次遍历的问题已经解决了，如何解决每一层结果放到一个List中呢，
     * 可以在每一层遍历的时候一次性出队完成，即拿到队列里面需要出队的本层元素的个数，全部出队，并按照顺序把他们的左右儿子再次入队
     * 下次遍历的时候，拿的就是再下一层总的元素个数
     *
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        // write your code here
        if(root == null)
            return null;
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        TreeNode current = null;
        //根元素入队
        queue.offer(root);
        while(!queue.isEmpty()) {
            List<Integer> currentList = new ArrayList<Integer>();
            //一层元素总数
            int layerCount = queue.size();
            //一次性出队一层全部元素
            while(layerCount-- != 0) {
                //出队头元素并访问
                current = queue.poll();
                currentList.add(current.val);
                if(current.left != null) {
                    queue.offer(current.left);
                }
                if(current.right != null) {
                    queue.offer(current.right);
                }
            }
            result.add(currentList);
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        List<List<Integer>> result = new Q69_binaryTreeLevelOrderTraversal().levelOrder(root);
        System.out.println(result.toString());
    }
}
