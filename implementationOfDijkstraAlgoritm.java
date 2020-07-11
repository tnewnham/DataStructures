package app;
import java.util.*;

    class Node {
        String name;
        int cost;
        boolean visited;
        List<Edge> edges;
    
        public Node (String name) {
            this.name = name;
            this.visited = false;
            this.cost = Integer.MAX_VALUE;
            this.edges = new ArrayList<Edge>();
        }
    
        public void add (Edge edge) {
            edges.add(edge);
        }
    
        @Override
        public boolean equals (Object o) {
            if (this == o) return true;
            if (!(o instanceof Node)) return false;
            Node node = (Node) o;
            return name.equals(node.name);
        }
    
        @Override
        public int hashCode () {
            return Objects.hash(name);
        }
    }
    
    class Edge {
        Node destination;
        int weight;
    
        public Edge(Node destination, int cost) {
            this.destination = destination;
            this.weight = cost;
        }
    }
    
    class Result {
        /*
         * Complete the 'findCheapestPath' function below.
         *
         * The function is expected to return an INTEGER.
         * The function accepts 2D_STRING_ARRAY edges as parameter.
         */
    
        public static int findCheapestPath(List<List<String>> edges) {
            HashMap<String, Node> nodeMap = new HashMap<>();
    
            Queue<Node> unvisitedNodes = new ArrayDeque<Node>();
    
            for (List<String> edge : edges) {
                    Node a = nodeMap.get(edge.get(0));
                    if (a == null) {
                        a = new Node(edge.get(0));
                        nodeMap.put(edge.get(0), a);
                    }
                    Node b = nodeMap.get(edge.get(1));
                    if (b == null) {
                        b = new Node(edge.get(1));
                        nodeMap.put(edge.get(1), b);
                    }
    
                    a.add(new Edge(b, Integer.parseInt(edge.get(2))));
            }
    
            Iterator mapIterator = nodeMap.entrySet().iterator();
    
            while (mapIterator.hasNext()) {
                Map.Entry<String, Node> mapEntry = (Map.Entry<String, Node>)mapIterator.next();
                unvisitedNodes.add((Node)(mapEntry.getValue()));
            }
    
            nodeMap.get("A").cost = 0;
    
            while (!unvisitedNodes.isEmpty()) {
                // remove start.
                Node v = getMinDistances(unvisitedNodes.poll());
                if (v != null) {
                    for (Edge edge : v.edges) {
                        int newCost = v.cost + edge.weight;
                        if (newCost < edge.destination.cost) {
                            if (nodeMap.get(edge.destination.name).visited) {
                                Node tempNode = nodeMap.get(edge.destination.name);
                                tempNode.cost = newCost;
                                nodeMap.replace(tempNode.name, tempNode);
                            }
                            edge.destination.cost = newCost;
                            edge.destination.visited = true;
                        }
                    }
                    nodeMap.put(v.name, v);
                }
            }
    
            return nodeMap.get("H").cost;
        }
    
        public static Node getMinDistances (Node currentNode) {
            Node cheapestPath = null;
            if (currentNode.cost == 0) {
                return currentNode;
            }
            if (currentNode.edges.size() != 0) {
                cheapestPath = currentNode.edges.get(0).destination;
            }
            for (Edge route : currentNode.edges) {
                if (cheapestPath != null && route.weight < cheapestPath.cost) {
                    cheapestPath = route.destination;
                    cheapestPath.cost = currentNode.cost + route.weight;
                }
            }
    
            return cheapestPath;
        }
    }
    
    
}