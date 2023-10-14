import javax.management.StandardEmitterMBean;

public class Avl {
    private Node root;

    public Node getRoot() {
        return root;
    }

    public static int altura(Node node) {
        if (node == null) {
            return -1;
        }

        int esquerda = altura(node.getLeft());
        int direita = altura(node.getRight());
        if (esquerda > direita) {

            return 1 + esquerda;
        }
        return 1 + direita;


    }

    public Node left_rotate(Node node) {
        Node b = node.getRight();
        Node y = b.getLeft();
        int temp = b.getCount();

        b.setLeft(node);
        node.setRight(y);
        if (node == this.root) {
            this.root = b;
        }
        if (node.getData() == b.getData()) {

            b.setCount(node.getCount());
            node.setCount(temp);
        }


        return b;

    }

    public Node right_rotate(Node node) {
        Node b = node.getLeft();
        Node y = b.getRight();
        int temp = b.getCount();


        b.setRight(node);
        node.setLeft(y);
        if (node == this.root) {
            this.root = b;

        }
        if (node.getData() == b.getData()) {

            b.setCount(node.getCount());
            node.setCount(temp);
        }


        return b;

    }

    public int getBalance(Node root) {
        return altura(root.getLeft()) - altura(root.getRight());
    }

    public Node insert(Node node, int key) {

        if (this.root == null) {
            Node temp = new Node();
            temp.setData(key);
            this.root = temp;
            return this.root;
        }
        if (node == null) {
            Node temp = new Node();
            temp.setData(key);
            return temp;
        }
        if (key < node.getData()) {
            node.setLeft(insert(node.getLeft(), key));
        } else if (key >= node.getData()) {

            node.setRight(insert(node.getRight(), key));
            if (key == node.getData()) {
                node.setCount(node.getCount() + 1);
            }

        }


        int balance = getBalance(node);

        if (balance > 1 && key < node.getLeft().getData()) {
            return right_rotate(node);
        }
        if (balance > 1 && key >= node.getLeft().getData()) {
            node.setLeft(left_rotate(node.getLeft()));
            return right_rotate(node);
        }
        if (balance < -1 && key < node.getRight().getData()) {
            node.setRight(right_rotate(node.getRight()));
            return left_rotate(node);
        }
        if (balance < -1 && key >= node.getRight().getData()) {
            return left_rotate(node);
        }

        return node;


    }

    public void insert(int key) {
        insert(root, key);
    }

    public Node delete(Node node, int key) {
        if (node == null)
            return node;

        if (root.getData() == key && root.getRight() == null && root.getLeft() == null) {
            root = null;
        }


        if (key < node.getData()) {
            node.setLeft(delete(node.getLeft(), key));
        } else if (key > node.getData()) {
            node.setRight(delete(node.getRight(), key));
        } else {

            if (node.getRight() == null || node.getLeft() == null) {
                Node temp = node.getLeft() == null ? node.getRight() : node.getLeft();

                if (temp == null) {
                    node = null;
                    if (this.root == node) {
                        this.root = null;
                    }
                } else {
                    if (this.root == node) {
                        if (temp == node.getLeft()) {
                            this.root = node.getLeft();

                        } else {
                            this.root = node.getRight();
                        }
                    }
                    node = temp;
                }

            } else {


                Node temp = getSuccessor(node);

                node.setData(temp.getData());
                node.setCount(temp.getCount());
                node.setRight(delete(node.getRight(), temp.getData()));
            }
        }

        if (node == null)
            return node;

        int balance = getBalance(node);

        if (balance > 1 && getBalance(node.getLeft()) >= 0)
            return right_rotate(node);

        if (balance > 1 && getBalance(node.getLeft()) < 0) {
            node.setLeft(left_rotate(node.getLeft()));
            return right_rotate(node);
        }

        if (balance < -1 && getBalance(node.getRight()) <= 0)
            return left_rotate(node);

        if (balance < -1 && getBalance(node.getRight()) > 0) {
            node.setRight(right_rotate(node.getRight()));
            return left_rotate(node);
        }

        return node;
    }


    public Node getSuccessor(Node root) {
        if (root == null) {
            return null;
        }
        Node temp = root.getRight();

        while (temp.getLeft() != null && temp.getData() != root.getData()) {
            temp = temp.getLeft();
        }
        return temp;

    }

    public Node buscar(int elemento) {

        Node atual = this.root;
        while (atual != null && atual.getData() != elemento) {

            if (atual.getData() > elemento)
                atual = atual.getLeft();
            else
                atual = atual.getRight();

        }

        return atual;
    }

    public Node src_equal(Node node, int key) {
        if (node == null) {
            return null;
        }

        if (node.getData() == key && node.getCount() == 1) {
            return node;
        }

        Node leftResult = src_equal(node.getLeft(), key);
        if (leftResult != null) {
            return leftResult;
        }

        return src_equal(node.getRight(), key);  // Search in the right subtree
    }

    public String traversePreOrder(Node root) {

        if (root == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(root.getData());

        String pointerRight = "└──";
        String pointerLeft = (root.getRight() != null) ? "├──" : "└──(left)";

        traverseNodes(sb, "", pointerLeft, root.getLeft(), root.getRight() != null);
        traverseNodes(sb, "", pointerRight, root.getRight(), false);

        return sb.toString();
    }

    public void traverseNodes(StringBuilder sb, String padding, String pointer, Node node,
                              boolean hasRightSibling) {
        if (node != null) {
            sb.append("\n");
            sb.append(padding);
            sb.append(pointer);
            sb.append(node.getData());

            StringBuilder paddingBuilder = new StringBuilder(padding);
            if (hasRightSibling) {
                paddingBuilder.append("│  ");
            } else {
                paddingBuilder.append("   ");
            }

            String paddingForBoth = paddingBuilder.toString();
            String pointerRight = "└──";
            String pointerLeft = (node.getRight() != null) ? "├──" : "└── (left)";

            traverseNodes(sb, paddingForBoth, pointerLeft, node.getLeft(), node.getRight() != null);
            traverseNodes(sb, paddingForBoth, pointerRight, node.getRight(), false);
        }
    }

    public void print() {
        System.out.println((traversePreOrder(root)));
    }

    public Node search(int elemento) {
        Node actual = this.root;
        while (actual != null && actual.getData() != elemento) {

            if (actual.getData() > elemento)
                actual = actual.getLeft();
            else
                actual = actual.getRight();
        }

        return actual;


    }


}
