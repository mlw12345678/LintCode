package binarytree;

/**
 * @author pulil
 * @version V1.0
 * @Title
 * @Description Definition of TreeNode:
 * @date 2019-01-25 下午3:59
 */
public class TreeNode {
    public int val;
    public TreeNode left, right;

    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}