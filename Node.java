public class Node {
    String name;
    boolean visited;
    int distance;
    EdgeList edges;
    Node next;
    Node parent;

    Node(String name) {
        this.name = name;
        this.visited = false;
        this.distance = Integer.MAX_VALUE;
        this.edges = new EdgeList();
        this.next = null;
        this.parent = null;
    }
}

class Edge {
    Node target;
    int weight;
    Edge next;

    Edge(Node target, int weight) {
        this.target = target;
        this.weight = weight;
        this.next = null;
    }
}

class EdgeList {
    Edge head;

    void addEdge(Edge edge) {
        if (head == null) {
            head = edge;
        } else {
            Edge current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = edge;
        }
    }
}
