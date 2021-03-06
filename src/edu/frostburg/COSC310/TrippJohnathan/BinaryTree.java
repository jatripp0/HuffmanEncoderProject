/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.frostburg.COSC310.TrippJohnathan;

import java.util.Iterator;

/**
 *
 * @author Johnathan Tripp (╯°□°）╯︵ ┻━┻
 * @param <E>
 */
public class BinaryTree<E> extends AbstractBinaryTree<E>{
    
    protected Node<E> root = null;
    private int size = 0;
    public BinaryTree(){}
    
    protected Node<E> createNode(E e, Node<E> parent,
                                Node<E> left, Node<E> right, int frequency) {
        return new Node<>(e, parent, left, right, frequency);
    } 
    
    protected Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node)) 
           throw new IllegalArgumentException("Not valid position type");
        Node<E> node = (Node<E>) p;
        if (node.getParent() == node)
           throw new IllegalArgumentException("p is no longer in the tree");
        return node;
    } 
    
    public int size(){ return size;}
    public Position<E> root(){ return root;}
    
    public Position<E> parent(Position<E> p) throws IllegalArgumentException { 
        Node<E> node = validate(p);
        return node.getParent();
    } 
    
    public Position<E> left(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return node.getLeft();
    } 
    
    public Position<E> right(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return node.getRight();
    } 
    
    public Position<E> addRoot(E e, int f) throws IllegalStateException {
        if (!isEmpty()) throw new IllegalStateException("Tree is not empty");
        root = createNode(e, null, null, null, f);
        size = 1;
        return root;
    } 
    
    public Position<E> addLeft(Position<E> p, E e, int f)
                            throws IllegalArgumentException {
        Node<E> parent = validate(p);
        if (parent.getLeft() != null)
            throw new IllegalArgumentException("p already has a left child");
        Node<E> child = createNode(e, parent, null, null, f);
        parent.setLeft(child);
        size++;
        return child;
    } 
    
    public Position<E> addRight(Position<E> p, E e, int f)
                            throws IllegalArgumentException {
        Node<E> parent = validate(p);
        if (parent.getRight()!= null)
            throw new IllegalArgumentException("p already has a right child");
        Node<E> child = createNode(e, parent, null, null, f);
        parent.setRight(child);
        size++;
        return child;
    } 
    
    public E set(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        E temp = node.getElement();
        node.setElement(e);
        return temp;
    }
    
    public void attach(Position<E> p, BinaryTree<E> t1,
                       BinaryTree<E> t2) throws IllegalArgumentException {
        Node<E> node = validate(p);
        if(isInternal(p)) throw new IllegalArgumentException("p must be a leaf");
        size += t1.size() + t2.size();
        if(!t1.isEmpty()){
            t1.root.setParent(node);
            node.setLeft(t1.root);
            t1.root = null;
            t1.size = 0;
        }
        if(!t2.isEmpty()){
            t2.root.setParent(node);
            node.setRight(t2.root);
            t2.root = null;
            t2.size = 0;
        }
    }

    public boolean isEmpty() {return size ==0;}
    
    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterable<Position<E>> positions() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
//    public class Node<E> implements Position<E>{
//
//    private E element;
//    private int frequency;
//    private Node<E> parent;
//    private Node<E> left;
//    private Node<E> right;
//    
//    public Node(E e, Node<E> above, Node<E> leftChild, Node<E> rightChild, int frequency){
//        element = e;
//        parent = above;
//        left = leftChild;
//        right = rightChild;
//        this.frequency = frequency;
//    }
//    
//    public Node<E> getParent(){ return parent;}
//    public Node<E> getLeft(){ return left;}
//    public Node<E> getRight(){ return right;}
//    @Override
//    public E getElement() {return element;}
//    public int getFrequency(){return frequency;}
//    
//    public void setElement(E e){ element = e;}
//    public void setFrequency(int f){frequency = f;}
//    public void setParent(Node<E> parentNode){ parent = parentNode;}
//    public void setLeft(Node<E> leftChild){ left = leftChild;}
//    public void setRight(Node<E> rightChild){ right = rightChild;}
//}
}
