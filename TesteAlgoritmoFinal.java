import java.util.*;

public class TesteAlgoritmoFinal {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        List<String> linhas = new ArrayList<>();

        while (sc.hasNext())
            linhas.add(sc.nextLine().toString());
        sc.close();

        ArvoreBinaria tree = new ArvoreBinaria();

        Node root = null;
        int tamanhoArray = 0;
        int qtdCase = 1;

        for (int i = 0; i < linhas.size(); i++) {

            if (!linhas.get(i).contains("P") && !linhas.get(i).contains("I") && linhas.get(i).trim().contains(" ")) {
                String[] linhaDelimitadora = linhas.get(i).trim().split(" ", 2);
                tamanhoArray = Integer.parseInt(linhaDelimitadora[0]);

                if (i == 0) {
                    System.out.println("Case " + qtdCase++ + ":");

                    root = null;
                    tree = new ArvoreBinaria();

                } else if (i + 1 != linhas.size()) {
                    System.out.println();
                    System.out.println("Case " + qtdCase++ + ":");

                    root = null;
                    tree = new ArvoreBinaria();
                }

                continue;
            } else {
                if (tamanhoArray > 0) {

                    int novoElemento = Integer.valueOf(linhas.get(i).trim());
                    root = tree.add(root, novoElemento);
                    tamanhoArray--;

                } else {
                    if (linhas.get(i).contains("P")) {
                        if(root == null){
                            System.out.println("empty");
                            continue;
                        }

                        root = tree.printAndDeleteMax(root);

                    } else {
                        String[] linhaDelimitadora = linhas.get(i).trim().split(" ", 2);
                        int novoElemento = Integer.parseInt(linhaDelimitadora[1]);

                        root = tree.add(root, novoElemento);

                    }

                }
            }

        }

        System.out.println();

    }


}

class ArvoreBinaria {

    public Node add(Node node, int key) {
        Node newNode = new Node(key);
        if (node == null)
            return newNode;
        else {
            if (key < node.key)
                node.left = add(node.left, key);
            else
                node.right = add(node.right, key);

        }
        return node;

    }

    public Node searchMin(Node node){

        if(node.left == null)
            return node;

        return searchMin(node.left);
    }

    public Node findMax(Node node){

        if(node.right == null)
            return node;

        return findMax(node.right);
    }

    public Node delete(Node root, int key){

        if(root == null)
            return root;

        if(key<root.key)
            root.left = delete(root.left,key);
        else if(key>root.key)
            root.right = delete(root.right,key);
        else{

            //node com nenhuma folha
            if(root.left == null && root.right == null)
                return null;
            else if(root.left == null)
                // node com um node (não é node esquerdo)
                return root.right;
            else if(root.right == null)
                // node com um node (não é node direito)
                return root.left;
            else {
                // nodes com dois nodes
                // procurar pelo menor node na sub arvore da direita

                Node temp = searchMin(root.right);
                root.key = temp.key;
                root.right = delete(root.right, temp.key);
            }
        }

        return root;
    }


    public Node printAndDeleteMax(Node root){

        if(root == null)
            return root;

        Node node = findMax(root);
        System.out.println(node.key);
        return delete(root,node.key);
    }


}

class Node {

    int key;

    Node left;
    Node right;

    Node(int key) {
        this.key = key;
    }

}




