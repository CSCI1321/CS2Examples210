package cs2.adt

class HashTableSet[A <% Ordered[A]] extends Set[A] {
    private var hash = Array.fill(100)(new BinarySearchTree[A]())
    private var len = 0

    private def getBucket(elem:A):Int = elem.hashCode() % hash.length

    def iterator():Iterator[A] = ???
    def add(elem:A):Unit = {
        val bucket = getBucket(elem)
        if(!hash(bucket).contains(elem)) {
            hash(bucket).insert(elem)
            len += 1
        }
    }
    def remove(elem:A):Unit = {
        val bucket = getBucket(elem)
        if(hash(bucket).contains(elem)) {
            hash(bucket).remove(elem)
            len -= 1
        }
    }
    def contains(elem:A):Boolean = {
        hash(getBucket(elem)).contains(elem)
    }
    override def size():Int = len
}