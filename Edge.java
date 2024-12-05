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