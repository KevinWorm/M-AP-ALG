/**
 * Created by Kevin Worm on 11-9-2014.
 */
public class Node {

    //Node information
    private int data;
    private int height;

    //Node Roots
    private Node leftRootNode;
    private Node rightRootNode;

    public Node()
    {
        data = 0;
        height = 0;

        leftRootNode = null;
        rightRootNode = null;
    }

    public Node(int _data)
    {
        data = _data;
        height = 0;

        leftRootNode = null;
        rightRootNode = null;
    }

    public void insertNode(Node node)
    {
        //if added data is smaller, insert left
        if(node.getData() < data)
        {
            //if root is empty, set root to node
            if(leftRootNode == null) leftRootNode = node;
            else leftRootNode.insertNode(node);

            //Check balance factor of roots
            if(getHeight(leftRootNode) - getHeight(rightRootNode) == 2){
                //Out off balance
                if(node.getData() < leftRootNode.getData()){
                    rotateWithLeftRootNode();
                }
                else{
                    leftRootNode.rotateWithRightRootNode();
                    rotateWithLeftRootNode();
                }
            }
        }
        //if added data is bigger insert right
        else if(node.getData() > data)
        {
            //if root is empty, set root to node
            if(rightRootNode == null) rightRootNode = node;
            else rightRootNode.insertNode(node);

            //Check balance factor of roots
            if(getHeight(rightRootNode) - getHeight(leftRootNode) == 2){
                //Out off balance
                if(node.getData() > rightRootNode.getData()){
                    rotateWithRightRootNode();
                }
                else{
                    rightRootNode.rotateWithLeftRootNode();
                    rotateWithRightRootNode();
                }
            }
        }

        //calculate node height
        setHeight(Math.max(getHeight(leftRootNode), getHeight(rightRootNode)) + 1);
    }

    private void rotateWithLeftRootNode(){

        //copy values of this node to new node
        Node node = new Node(this.getData());
        //set new roots
        node.leftRootNode = leftRootNode.getRightRootNode();
        node.rightRootNode = rightRootNode;

        //set current node as new left node and left node data as new current node data
        setData(leftRootNode.getData());
        leftRootNode = leftRootNode.getLeftRootNode();
        rightRootNode = node;

        //calculate heights
        this.setHeight(Math.max(getHeight(leftRootNode), getHeight(rightRootNode)) + 1);
        node.setHeight(Math.max(getHeight(node.leftRootNode), getHeight(node.rightRootNode)) + 1);
    }

    private void rotateWithRightRootNode(){
        //copy values of this node to new node
        Node node = new Node(this.getData());
        //set new roots
        node.leftRootNode = leftRootNode;
        node.rightRootNode = rightRootNode.getLeftRootNode();

        //set current node as new right node and right node data as new current node data
        setData(rightRootNode.getData());
        rightRootNode = rightRootNode.getRightRootNode();
        leftRootNode = node;

        //calculate heights
        this.setHeight(Math.max(getHeight(leftRootNode), getHeight(rightRootNode)) + 1);
        node.setHeight(Math.max(getHeight(node.leftRootNode), getHeight(node.rightRootNode)) + 1);
    }

    /* Getters and Setters */
    private void setHeight(int _height)
    {
        height = _height;
    }

    public void setData(int _data)
    {
        data = _data;
    }

    public int getData()
    {
        return data;
    }

    public int getHeight()
    {
        return height;
    }

    private int getHeight(Node node)
    {
        return node == null ? -1 : node.height;
    }

    public Node getLeftRootNode()
    {
        return leftRootNode;
    }

    public Node getRightRootNode()
    {
        return rightRootNode;
    }

}
