/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.frostburg.COSC310.TrippJohnathan;

/**
 *
 * @author Johnathan Tripp (╯°□°）╯︵ ┻━┻
 */
public class Node<E> implements Position<E>{

    private E element;
    private int frequency;
    private Node<E> parent;
    private Node<E> left;
    private Node<E> right;
    
    public Node(E e, Node<E> above, Node<E> leftChild, Node<E> rightChild, int frequency){
        element = e;
        parent = above;
        left = leftChild;
        right = rightChild;
        this.frequency = frequency;
    }
    
    public Node<E> getParent(){ return parent;}
    public Node<E> getLeft(){ return left;}
    public Node<E> getRight(){ return right;}
    @Override
    public E getElement() {return element;}
    public int getFrequency(){return frequency;}
    
    public void setElement(E e){ element = e;}
    public void setFrequency(int f){frequency = f;}
    public void setParent(Node<E> parentNode){ parent = parentNode;}
    public void setLeft(Node<E> leftChild){ left = leftChild;}
    public void setRight(Node<E> rightChild){ right = rightChild;}
    
    public boolean isInternal(Position<E> p){return left != null || right != null;}
    public boolean isExternal(Position<E> p){return left == null && right == null;}
}
