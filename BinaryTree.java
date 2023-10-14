public class BinaryTree {
    private Node root;


    public BinaryTree() {
        this.root = null;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public Node push(Node root, int data) {

        if (this.root == null) {
            Node temp = new Node();
            temp.setData(data);
            this.root = temp;
            return temp;
        } else if (root == null) {
            Node temp = new Node();  // inicializando um novo node para colocar o novo valor dentro da arvore
            temp.setData(data);
            return temp;
        } else if (data < root.getData()) {
            root.setLeft(push(root.getLeft(), data));
        } else if (data >= root.getData()) {
            root.setRight(push(root.getRight(), data));
            if(data == root.getData()){
                root.setCount(root.getCount()+1);
            }
        }


        return root;


    }

    public Node push(int data) {
        return push(root, data);
    }


    /*  public void Push(int c){     esse metodo e sem recursividade
          Node p = root;
          Node q = new Node();
          Node auxiliary = new Node();
          if(p == null){
              Node ne = new Node();
              ne.setData(c);
              root =  ne;
              return;
          }
          while (p!= null){
               if(c<p.getData()){
                  auxiliary = p;
                  q= auxiliary ;
                  if(p.getLeft() == null){
                      Node ne = new Node();
                      ne.setData(c);
                      p.setLeft(ne);
                      return;
                  }
                  else {
                      p = p.getLeft();
                  }
              }
               else {
                    if(c>=p.getData()){
                         auxiliary = p ;
                         q = auxiliary;
                         if(p.getRight() == null){
                             Node ne = new Node();
                             ne.setData(c);
                             p.setRight(ne);
                             return;
                         }
                         else {
                             p=p.getRight();
                         }
                    }
               }
          }
      } */
    public void inordem(Node focusnode) {
        if (focusnode != null) {
            inordem(focusnode.getLeft());
            System.out.print(focusnode.getData() + " ");
            inordem(focusnode.getRight());
        }
    }

    public void preordem(Node focusnode) {
        if (focusnode != null) {
            System.out.print(focusnode.getData() + " ");
            preordem(focusnode.getLeft());
            preordem(focusnode.getRight());
        }
    }

    public void posordem(Node focusnode) {
        if (focusnode != null) {
            preordem(focusnode.getLeft());
            preordem(focusnode.getRight());
            System.out.print(focusnode.getData() + " ");
        }
    }

    public Node achar_maior(Node focusnode) {
        Node result = focusnode;
        if (focusnode != null) {

            Node nodeLeft = achar_maior(focusnode.getLeft());
            Node nodeRight = achar_maior(focusnode.getRight());


            if (nodeLeft != null && nodeLeft.getData() > result.getData()) {

                result = nodeLeft;
            }
            if (nodeRight != null && nodeRight.getData() > result.getData()) {

                result = nodeRight;
            }
        }

        return result;
    }

    public Node achar_menor(Node focusnode) {
        Node result = focusnode;
        if (focusnode != null) {

            Node nodeLeft = achar_maior(focusnode.getLeft());
            Node nodeRight = achar_maior(focusnode.getRight());


            if (nodeLeft != null && nodeLeft.getData() < result.getData()) {

                result = nodeLeft;
            }
            if (nodeRight != null && nodeRight.getData() < result.getData()) {

                result = nodeRight;
            }
        }

        return result;
    }

    public Node delete_Biggest() {
        Node max = achar_maior(root);
        Node focusnode = root;
        Node parent_node = new Node();
        while (focusnode.getRight() != null) {
            if (focusnode.getRight().getData() == max.getData()) {
                parent_node = focusnode;
                break;
            }

            focusnode = focusnode.getRight();

        }

        parent_node.setRight(null);

        return max;


    }

    public Node delete_Smallest() {
        Node min = achar_menor(root);
        Node focusnode = root;
        Node parent_node = new Node();
        while (focusnode.getLeft() != null) {
            if (focusnode.getLeft().getData() == min.getData()) {
                parent_node = focusnode;
                break;
            }

            focusnode = focusnode.getLeft();

        }

        parent_node.setLeft(null);

        return min;


    }

    public Node delete_node(Node root, int n) {
        if (root == null) {
            return null;
        } else if (n < root.getData()) {
            root.setLeft(delete_node(root.getLeft(), n));

        } else if (n > root.getData()) {
            root.setRight(delete_node(root.getRight(), n));

        } else {
            if(root.getCount() >=2) {
                root.setRight(delete_node(root.getRight(),n));
                root.setCount(root.getCount()-1);
            }
           else if (root.getLeft() == null || root.getRight() == null) {
                Node temp = null;
                temp = root.getLeft() == null ? root.getRight() : root.getLeft();

                if (temp == null) {
                    if (this.root == root){
                        this.root = null;
                    }
                    else{
                    root = null;}
                } else {
                    if(this.root == root){
                        if(temp == root.getLeft()){
                            this.root = root.getLeft();

                        }
                        else {
                            this.root = root.getRight();
                        }
                    }
                    else{
                    root = temp;
                }}
            } else {
                Node successor = getSuccessor(root);
                root.setData(successor.getData());
                root.setRight(delete_node(root.getRight(), root.getData()));



            }
        }
        return root;
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
            String pointerLeft = (node.getRight() != null) ? "├──" : "└──(left)";

            traverseNodes(sb, paddingForBoth, pointerLeft, node.getLeft(), node.getRight() != null);
            traverseNodes(sb, paddingForBoth, pointerRight, node.getRight(), false);
        }
    }

    public void print() {
        System.out.println((traversePreOrder(root)));
    }

    public Node search(int elemento) {
        Node actual = this.root;
        while (actual != null && actual.getData() != elemento){

            if (actual.getData() > elemento)
                actual = actual.getLeft();
            else
                actual = actual.getRight();}

        return actual;


    }
}