public class Avl {
    private Node root;

    public Node getRoot() {
        return root;
    }

    public static int altura(Node node) {
        if (node == null){
            return -1 ;
        }

        int esquerda = altura(node.getLeft());
        int direita = altura(node.getRight());
        if( esquerda > direita ){

            return 1 + esquerda;}
        return 1 + direita;



    }
    public  Node left_rotate(Node node){
        Node b = node.getRight();
        Node y = b.getLeft();

        b.setLeft(node);
        node.setRight(y);
        if(node== this.root){
            this.root =b;
        }
        System.out.println("Left R " + node.getData());

        return b;

    }

    public  Node right_rotate(Node node){
        Node b = node.getLeft();
        Node y = b.getRight();

        b.setRight(node);
        node.setLeft(y);
        if(node== this.root){
            this.root =b;
        }

        System.out.println("Right R " + node.getData());
        return b;

    }
    public  int getBalance(Node root){
        return altura(root.getLeft()) - altura(root.getRight());
    }

    public Node insert(Node node ,int key){

        if(this.root == null){
            Node temp = new Node();
            temp.setData(key);
            this.root = temp;
            return this.root;
        }
        if(node == null){
            Node temp = new Node();
            temp.setData(key);
            return temp;
        }
        if(key< node.getData()){
            node.setLeft(insert(node.getLeft(), key));
        }
        else if(key >= node.getData()){
            node.setCount(node.getCount()+1);
            node.setRight(insert(node.getRight(),key));
        }

        int balance = getBalance(node);

        if(balance > 1 && key<node.getLeft().getData()){
            return right_rotate(node);
        }
        if(balance > 1 && key >= node.getLeft().getData()){
            node.setLeft(left_rotate(node.getLeft()));
            return  right_rotate(node);
        }
        if(balance<-1 && key < node.getRight().getData()){
            node.setRight(right_rotate(node.getRight()));
            return left_rotate(node);
        }
        if(balance <-1 && key >= node.getRight().getData()){
            return left_rotate(node);
        }

        return node;



    }
    public void insert(int key){
        insert(root,key);
    }


    public Node buscar(int elemento) {

        Node atual = this.root;
        while (atual != null && atual.getData() != elemento){

            if (atual.getData() > elemento)
                atual = atual.getLeft();
            else
                atual = atual.getRight();

        }

        return atual;
    }
    public String traversePreOrder(Node root) {

        if (root == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(root.getData());

        String pointerRight = "└──";
        String pointerLeft = (root.getRight() != null) ? "├──" : "└──";

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
            String pointerLeft = (node.getRight() != null) ? "├──" : "└──";

            traverseNodes(sb, paddingForBoth, pointerLeft, node.getLeft(), node.getRight() != null);
            traverseNodes(sb, paddingForBoth, pointerRight, node.getRight(), false);
        }
    }

    public void print() {
        System.out.println((traversePreOrder(root)));
    }


}
