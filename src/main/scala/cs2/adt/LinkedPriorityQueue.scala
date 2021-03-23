package cs2.adt

class LinkedPriorityQueue[A <% Ordered[A]] extends PriorityQueue[A] {
  private class Node(var data:A, var next:Node)
  private var head:Node = null

  def add(elem:A):Unit = {
    if(head == null || elem > head.data) {
      head = new Node(elem, head)
    } else {
      var rover = head
      while(rover.next != null && rover.next.data > elem) {
        rover = rover.next
      }
      rover.next = new Node(elem, rover.next)
    }
  }
  def get():A = {
    var ret = head.data
    head = head.next
    ret
  }
  def peek():A = head.data
  def isEmpty():Boolean = { head == null }

  /* Extra work in get/peek
  def add(elem:A):Unit = { head = new Node(elem, head) }
  def get():A = {
    //Belief values
    var biggest = head.data
    var obb:Node = null
    //Iterating values
    var rover = head
    var trail:Node = null

    while(rover != null) {
      if(rover.data > biggest) {
        biggest = rover.data
        obb = trail
      }
      trail = rover
      rover = rover.next
    }
    if(obb == null) {
      head = head.next
    } else {
      obb.next = obb.next.next
    }
    biggest
  }
  def peek():A = {
    var biggest = head.data
    var rover = head
    while(rover != null) {
      if(rover.data > biggest) biggest = rover.data
      rover = rover.next
    }
    biggest
  }
  def isEmpty():Boolean = { head == null }
  */
}