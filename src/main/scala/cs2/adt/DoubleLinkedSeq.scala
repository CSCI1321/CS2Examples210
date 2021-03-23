package cs2.adt

class DoubleLinkedSeq[A: Manifest] extends Seq[A] {
  private class Node(var data: A, var prev: Node, var next: Node) {
    def getNode(idx: Int): Node = {
      if (idx >= 0 && idx < len) {
        var rover = this
        for (ctr <- 1 to idx) {
          rover = rover.next
        }
        rover
      } else {
        end
      }
    }
  }

  class BidirectionalIterator extends Iterator[A] {
    private var rover = end.next
    def next(): A = {
      val ret = rover.data
      rover = rover.next
      ret
    }
    def hasNext(): Boolean = {
      rover != end
    }
    def previous(): A = {
      val ret = rover.data
      rover = rover.prev
      ret
    }
    def hasPrevious(): Boolean = {
      rover != end
    }
  }

  private var end: Node = new Node(new Array[A](1)(0), null, null)
  end.prev = end
  end.next = end
  private var len: Int = 0

  def iterator(): BidirectionalIterator = new BidirectionalIterator()

  def get(idx: Int): A = {
    val rover = end.next.getNode(idx)
    rover.data
  }
  def set(myIdx: Int, elem: A): Unit = {
    val rover = end.next.getNode(myIdx)
    rover.data = elem
  }
  def insert(idx: Int, elem: A): Unit = {
    val rover = end.next.getNode(idx - 1)
    rover.next = new Node(elem, rover, rover.next)
    rover.next.next.prev = rover.next
    len += 1
  }
  def remove(idx: Int): A = {
    val rover = end.next.getNode(idx - 1)
    val ret = rover.next.data
    rover.next = rover.next.next
    rover.next.prev = rover
    len -= 1
    ret
  }
  def length(): Int = len

  override def append(elem: A): Unit = {
    val rover = end.prev
    rover.next = new Node(elem, rover, rover.next)
    rover.next.next.prev = rover.next
    len += 1
  }

}
