package ec.edu.uees.modelo;

import java.util.List;
import java.util.Stack;

public class BT<E> {
    private Node<E> root;
    private int contador = 0;;
    
    class Node<E> {
        E data;
        Node<E> left;
        Node<E> right;

        Node(E data) {
            this.data = data;
        }
    }

    public void armarPostOrder(List<E> postorder) {
        if (postorder == null || postorder.isEmpty()) return;
        
        Stack<Node<E>> stack = new Stack<>(); // pila de nodos
        for (E valor : postorder) {
            Node<E> nodo = new Node<>(valor);
            if (valor.toString().startsWith("¿")) { // es una pregunta
                nodo.right = stack.pop(); // hijo derecho primero
                nodo.left = stack.pop();  // hijo izquierdo
            }
            stack.push(nodo); // metemos el nodo a la pila
        }
        root = stack.pop(); // la raiz del árbol es el ultimo nodo que queda en la pila
    }

    public void preOrden() {
        preOrden(this.root);
        System.out.println();
    }

    private void preOrden(Node<E> p) {
        if(p!=null) {
            System.out.print(p.data+" ");
            preOrden(p.left);
            preOrden(p.right);    
        }
    }

    public void posOrden() {
        posOrden(this.root);
        System.out.println();
    }

    private void posOrden(Node<E> p) {
        if(p!=null) {
            posOrden(p.left);
            posOrden(p.right);   
            System.out.print(p.data+" "); 
        }
    }

    public void enOrden() {
        enOrden(this.root);
        System.out.println();
    }

    private void enOrden(Node<E> p) {
        if(p!=null) {
            enOrden(p.left);
            System.out.print(p.data+" "); 
            enOrden(p.right);   
        }
    }

    public int countLeaves() {
        return countLeaves(this.root);
    }

    private int countLeaves(Node<E> p) {
        if (p == null) 
            return 0;
        else if (p.left == null && p.right == null) {
            return 1;
        }
        return countLeaves(p.left) +countLeaves(p.right);
    }

    public int size() {
        return size(this.root);
    }

    private int size(Node<E> p) {
        if (p == null) return 0;
        return 1 + size(p.left) + size(p.right);
    }

}
