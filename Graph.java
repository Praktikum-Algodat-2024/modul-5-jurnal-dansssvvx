public class Graph {
    NodeList nodes;
    String[][] paths = new String[100][100];
    int[] pathLengths = new int[100];
    int pathCount = 0;

    Graph() {
        nodes = new NodeList();
    }

    void addNode(String name) {
        nodes.addNode(new Node(name));
    }

    void addEdge(String from, String to, int weight) {
        Node fromNode = nodes.findNode(from);
        Node toNode = nodes.findNode(to);
        if (fromNode != null && toNode != null) {
            fromNode.edges.addEdge(new Edge(toNode, weight));
        }
    }

    void dijkstra(String startName) {
        Node startNode = nodes.findNode(startName);
        if (startNode == null) return;

        startNode.distance = 0;

        while (true) {
            Node currentNode = null;

            Node temp = nodes.head;
            while (temp != null) {
                if (!temp.visited && (currentNode == null || temp.distance < currentNode.distance)) {
                    currentNode = temp;
                }
                temp = temp.next;
            }

            if (currentNode == null) break;

            currentNode.visited = true;

            Edge edge = currentNode.edges.head;
            while (edge != null) {
                Node neighbor = edge.target;
                int newDistance = currentNode.distance + edge.weight;
                if (newDistance < neighbor.distance) {
                    neighbor.distance = newDistance;
                    neighbor.parent = currentNode;
                }
                edge = edge.next;
            }
        }
    }

    void collectPath(Node target) {
        String[] path = new String[100];
        int length = 0;

        while (target != null) {
            path[length++] = target.name;
            target = target.parent;
        }

        paths[pathCount] = new String[length];
        for (int i = 0; i < length; i++) {
            paths[pathCount][length - 1 - i] = path[i];
        }
        pathLengths[pathCount] = length;
        pathCount++;
    }

    void sortPaths() {
        for (int i = 0; i < pathCount - 1; i++) {
            for (int j = 0; j < pathCount - i - 1; j++) {
                if (pathLengths[j] > pathLengths[j + 1]) {
                    // Swap paths
                    String[] tempPath = paths[j];
                    paths[j] = paths[j + 1];
                    paths[j + 1] = tempPath;

                    // Swap lengths
                    int tempLength = pathLengths[j];
                    pathLengths[j] = pathLengths[j + 1];
                    pathLengths[j + 1] = tempLength;
                }
            }
        }
    }

    void printPaths(String startName) {
        Node startNode = nodes.findNode(startName);
        if (startNode == null) return;

        Node current = nodes.head;
        while (current != null) {
            if (!current.name.equals(startName)) {
                collectPath(current);
            }
            current = current.next;
        }

        sortPaths();

        for (int i = 0; i < pathCount; i++) {
            System.out.print("");
            for (int j = 0; j < pathLengths[i]; j++) {
                System.out.print(paths[i][j]);
                if (j < pathLengths[i] - 1) {
                    System.out.print(" => ");
                }
            }
            System.out.println();
        }
    }
}
