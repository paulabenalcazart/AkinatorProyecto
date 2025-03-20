package ec.edu.uees.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BT<E> {
    private Node<E> root;
    private Node<E> current;
    private List<E> arbolPostOrder;
    
    class Node<E> {
        E data;
        Node<E> left;
        Node<E> right;

        Node(E data) {
            this.data = data;
        }
    }
    
    public boolean irIzquierda() {
        if (current == null || current.left.left == null) {
            return false;
        }
        current = current.left;
        return true;
    }
    
    public void irHojaIzq() {
        current = current.left;
    }
    
    public void irHojaDer() {
        current = current.right;
    }
    
    public boolean irDerecha() {
        if (current == null || current.right.right == null) {
            return false;
        }
        current = current.right;
        return true;
    }
    
    public E getCurrent() {
        return this.current != null ? this.current.data : null;
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
        current = root;
    }
    
    public Node<E> buscar(E e) {
        return buscar(root, e);
    }

    private Node<E> buscar(Node<E> p, E e) {
        if (p == null) return null;
        if (p.data.equals(e)) return p;

        Node<E> encontrar = buscar(p.left, e);
        if (encontrar == null) {
            encontrar = buscar(p.right, e);
        }
        return encontrar;
    }
    
    public boolean existeEnArbol(E e) {
        return existeEnArbol(root, e);
    }

    private boolean existeEnArbol(Node<E> p, E e) {
        if (p == null || e == null) return false;
        if (existe(p, e)) {
            return true;
        }
        return existeEnArbol(p.left, e) || existeEnArbol(p.right, e);
    }
    
    private boolean existe(Node<E> p, E e) {
        if(e == null || p == null) return false;
        if(p.data instanceof String && e instanceof String) { // Solo asegurando que entre un string
            String original = (String) p.data; // lo paso de object a string a ambos
            String nuevo = (String) e;
            if(!original.startsWith("¿")) { // asegurandome de que no estoy con una pregunta
                String[] data_split = original.split(" "); // separo al actor en nombres y apellidos
                int actorCompleto = 0;
                for(int i = 0; i < data_split.length; i++) {
                    if(nuevo.toLowerCase().contains(data_split[i].toLowerCase())) {
                        actorCompleto++; // Porque hay actores que solo tiene uno o dos nombres
                        // es validacion para saber si todo el actor esta presente en el otro string
                    }
                }
                if(actorCompleto == data_split.length) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean add(E pregunta, E izquierdo, E derecho) {
        Node<E> padre = buscar(derecho);
        if (padre == null || padre.left != null || padre.right != null) {
            return false;
        }
        padre.data = pregunta;
        padre.left = new Node<>(izquierdo);
        padre.right = new Node<>(derecho);
        return true;
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
    
    public List<E> desarmarPostOrder() {
        arbolPostOrder = new ArrayList<>();
        desarmarPostOrder(this.root);
        return arbolPostOrder;
    }
    
    private void desarmarPostOrder(Node<E> p) {
        if(p!=null) {
            desarmarPostOrder(p.left);
            desarmarPostOrder(p.right);   
            arbolPostOrder.add(p.data);
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
