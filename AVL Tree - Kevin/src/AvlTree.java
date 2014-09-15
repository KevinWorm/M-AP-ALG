import javax.swing.*;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Created by Kevin Worm on 11-9-2014.
 */
public class AvlTree {
    Node root;

    public AvlTree()
    {
        root = null;
    }

    public void insert(int data)
    {
        if(root == null){
            root = new Node(data);
        }
        else{
            root.insertNode(new Node(data));
        }
    }

    public void search()
    {
    }

    /* Function for in order traversal */
    public void printInOrder()
    {
        inOrder(root);
    }
    private void inOrder(Node node)
    {
        if (node != null)
        {
            inOrder(node.getLeftRootNode());
            System.out.print(node.getData() +" ");
            inOrder(node.getRightRootNode());
        }
    }
    /* Function for pre order traversal */
    public void printPreOrder()
    {
        preOrder(root);
    }
    private void preOrder(Node node)
    {
        if (node != null)
        {
            System.out.print(node.getData() +" ");
            preOrder(node.getLeftRootNode());
            preOrder(node.getRightRootNode());
        }
    }
    /* Function for post order traversal */
    public void printPostOrder()
    {
        postOrder(root);
    }
    private void postOrder(Node node)
    {
        if (node != null)
        {
            postOrder(node.getLeftRootNode());
            postOrder(node.getRightRootNode());
            System.out.print(node.getData() +" ");
        }
    }

    //internet print script
    public void printTree(OutputStreamWriter out) throws IOException {
        if (root.getRightRootNode() != null) {
            printTree(root.getRightRootNode(), out, true, "");
        }
        printNodeValue(root, out);
        if (root.getLeftRootNode() != null) {
            printTree(root.getLeftRootNode(), out, false, "");
        }
    }
    private void printNodeValue(Node node, OutputStreamWriter out) throws IOException {
        out.write(node.getData() + "");
        out.write('\n');
    }
    // use string and not stringbuffer on purpose as we need to change the indent at each recursion
    private void printTree(Node node, OutputStreamWriter out, boolean isRight, String indent) throws IOException {
        if (node.getRightRootNode() != null) {
            printTree(node.getRightRootNode(), out, true, indent + (isRight ? "        " : " |      "));
        }
        out.write(indent);
        if (isRight) {
            out.write(" /");
        } else {
            out.write(" \\");
        }
        out.write("----- ");
        printNodeValue(node, out);
        if (node.getLeftRootNode() != null) {
            printTree(node.getLeftRootNode(), out, false, indent + (isRight ? " |      " : "        "));
        }
    }

}
