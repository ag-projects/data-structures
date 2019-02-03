package ds.trees;

public class BasicBinaryTree<T extends Comparable<T>> implements BinaryTree<T> {

    private Node root;
    private int size;

    public BasicBinaryTree() {
        this.root = null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(T item) {
        Node newNode = new Node(item);

        /**
         * If this is the first item in the tree
         * set is as root
         */
        if(root == null) {
            this.root = newNode;
            System.err.println("Set root: " + newNode.getItem());
            this.size++;
        }
        // Otherwise insert the new item into the tree using the binary tree insert algorithm
        else {
            insert(this.root, newNode);
        }

    }

    @Override
    public boolean delete(T item) {
        boolean deleted = false;

        // if the root node does not exists -> nothing to delete
        if (root == null) {
            return false;
        }

        // find the node to delete
        Node currentNode = getNode(item);
        if (currentNode != null) {
            /**
             * If node to be deleted does not have any children, just delete it.
             */
            if (currentNode.getLeft() == null && currentNode.getRight() == null) {
                unlink(currentNode, null);
                deleted = true;
            }
            /**
             * If node to be deleted has only a right child, remove it in the hierarchy
             */
            else if(currentNode.getLeft() == null && currentNode.getRight() != null) {
                unlink(currentNode, currentNode.getRight());
                deleted = true;
            }
            /**
             * If the node to be deleted has only a left child, remove it in the hierarchy
             */
            else if (currentNode.getLeft() != null && currentNode.getRight() == null) {
                unlink(currentNode, currentNode.getLeft());
                deleted = true;
            }
            /**
             * the node have both children, do a node swap to delete
             */
            else {
                // swap out the node with right most leaf node on the left side
                Node child = currentNode.getLeft();
                while(child.getRight() != null && child.getLeft() != null) {
                    child = child.getRight();
                }

                // we have the right most leaf node. We can replace the current node with this node
                child.getParent().setRight(null);  // remove the leaf node from it's current position

                child.setLeft(currentNode.getLeft());
                child.setRight(currentNode.getRight());

                unlink(currentNode, child);
                deleted = true;
            }
        }

        if (deleted) {
            size--;
        }

        return deleted;
    }

    private void unlink(Node current, Node replacingNode) {

        if(current == root) {
            replacingNode.setLeft(current.left);
            replacingNode.setRight(current.right);
            root = replacingNode;
        }
        else if(current.getParent().getRight() == current) {
            current.getParent().setRight(replacingNode);
        } else {
            current.getParent().setLeft(replacingNode);
        }
    }

    @Override
    public boolean contains(T item) {
        Node currentNode = getNode(item);

        if(currentNode != null) {
            return true;
        } else {
            return false;
        }
    }


    public Node getNode(T item) {

        Node current = root;

        while (current != null) {

            if (item.compareTo(current.getItem()) == 0) {
                return current;
            }

            else if (item.compareTo(current.getItem()) < 0) {
                // go to the left
                current = current.left;
            }

            else if (item.compareTo(current.getItem()) > 0) {
                // go to the right
                current = current.right;
            }
        }
        return null;
    }



    // associates a child to a parent
    private void insert(Node parent, Node child) {

        /**
         * if the child is less than the parent -> it goes to the left
         */
        if(child.getItem().compareTo(parent.getItem()) < 0) {
            // if the left node is null -> the correct spot is found!
            if(parent.getLeft() == null) {
                parent.setLeft(child);
                child.setParent(parent);
                this.size++;
            }
            // otherwise we need to call the insert again
            // and test for left or right recursively!
            else {
                insert(parent.getLeft(), child);
            }
        }

        /**
         * if the child is greater than the parent -> it goes to the right
         */
        else if(child.getItem().compareTo(parent.getItem()) > 0) {
            // if the right child is null -> the correct spot is found
            if(parent.right == null) {
                parent.setRight(child);
                child.setParent(parent);
                this.size++;
            }
            // otherwise we need to call the insert again
            // and test for left/right recursively
            else {
                insert(parent.getRight(), child);
            }
        }
        /**
         * If the parent and child are both equal, we do not do anything
         * data in a binary tree is assumed to be unique
         * and the value already exists in the tree!
         */


    }























    private class Node {
       private Node left;
       private Node right;
       private Node parent;
       private T item;

        public Node(T item) {
            this.item = item;
            this.left = null;
            this.right = null;
            this.parent = null;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public T getItem() {
            return item;
        }

        public void setItem(T item) {
            this.item = item;
        }
    }
}
