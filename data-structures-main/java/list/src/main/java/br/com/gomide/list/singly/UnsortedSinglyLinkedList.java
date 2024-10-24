package br.com.gomide.list.singly;

import java.security.interfaces.ECKey;

import br.com.gomide.list.ListNode;
import br.com.gomide.list.interfaces.ISinglyLinkedList;
import br.com.gomide.list.interfaces.IUnsortedList;

public class UnsortedSinglyLinkedList<T extends Object> implements ISinglyLinkedList<T>, IUnsortedList<T> {

  private ListNode<T> first;
  private ListNode<T> last;
  private ListNode<T> navigationPointer;

  @Override
  public void prepend(T value) {
    ListNode<T> newElement = new ListNode<T>(value);

    if (first == null) {
      this.first = newElement;
      this.last = newElement;
      this.navigationPointer = newElement;
      this.last.setNext(this.first); // O utilmo nó aponta para o primeiro
    } else {
      newElement.setNext(this.first);
      this.first = newElement;
      this.last.setNext(this.first); // Atualiza o ulitmo no para apontar para o primeiro
    }
  }

  @Override
  public void append(T value) {
    ListNode<T> newElement = new ListNode<T>(value);

    if (first == null) {
      this.first = newElement;
      this.last = newElement;
      this.navigationPointer = newElement;
      this.last.setNext(this.first);
    } else {
      this.last.setNext(newElement);
      this.last = newElement;
      this.last.setNext(this.first);
    }
  }

  @Override
  public T find(T value) {
    if (first == null) {
      return null;
    }
    ListNode<T> element = this.first;
    do {
      T currentValue = element.getValue();
      if (currentValue.equals(value)) {
        return currentValue;
      }
      element = element.getNext();
    } while (element != this.first);
    return null;
  }

  @Override
  public boolean remove(T value) {
    if (first == null) {
      return false;
    }
    ListNode<T> element = this.first;
    ListNode<T> noAnterior = this.last;
    
    do {
      T currentValue = element.getValue();

      if (currentValue.equals(value)) {
        if (element == this.first) {
          if(element == this.last){
            this.first = null;
            this.last = null;
          }else{
            this.first = this.first.getNext();
            this.last.setNext(this.first);
          }
        } else if (element == this.last) {
          this.last = noAnterior;
          this.last.setNext(this.first);
        } else {
          noAnterior.setNext(element.getNext());
        }
        return true;
      }
      noAnterior = element;
      element = element.getNext();
    } while (element != this.first);

    return false;
  }

  @Override
  public void clear() {
    this.first = null;
    this.last = null;
    this.navigationPointer = null;
  }

  @Override
  public String listContent() {
    if (first == null) {
      return "Lista vazia";
    }
    StringBuilder listContent = new StringBuilder();

    ListNode<T> element = this.first;
    do {
      listContent.append(element.getValue());
      listContent.append(" -> ");
      element = element.getNext();
    } while (element != this.first);

    listContent.append("volta ao inicio");

    return listContent.toString();
  }

  @Override
  public void clearNavigation() {
    this.navigationPointer = this.first;
  }

  @Override
  public T getNextElement() {
    if (this.first == null || this.last == null) {
      return null;
    }

    if (this.navigationPointer == null) {
      return null;
    }

    T value = this.navigationPointer.getValue();
    this.navigationPointer = this.navigationPointer.getNext();
    return value;
  }

}
