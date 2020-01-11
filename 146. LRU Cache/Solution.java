class LRUCache {
    private class Node {
        int key;
        int value;
        Node prev;
        Node next;
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.prev = null;
            this.next = null;
        }
    }

    private int capacity;
    private Map<Integer, Node> cache;
    private Node head;
    private Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;

        cache = new HashMap<>();

        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }

        Node node = cache.get(key);
        extractNode(node);
        putNodeToTail(node);

        return node.value;
    }
    
    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            node.value = value;
            cache.put(key, node);
            extractNode(node);
            putNodeToTail(node);

            return;
        }

        Node newNode = new Node(key, value);
        cache.put(key, newNode);
        putNodeToTail(newNode);
        if (cache.size() > this.capacity) {
            Node evictedNode = evictFromHead();
            cache.remove(evictedNode.key);
        }

        return;
    }

    private void extractNode(Node node) {
        Node prevNode = node.prev;
        Node nextNode = node.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;

        return;
    }

    private void putNodeToTail(Node node) {
        Node prevNode = this.tail.prev;
        Node tailNode = this.tail;
        prevNode.next = node;
        node.prev = prevNode;
        node.next = tailNode;
        tailNode.prev = node;

        return;
    }

    private Node evictFromHead() {
        Node nodeToEvict = this.head.next;
        Node nextNode = nodeToEvict.next;
        Node headNode = this.head;

        headNode.next = nodeToEvict.next;
        nextNode.prev = headNode;
        
        return nodeToEvict;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */