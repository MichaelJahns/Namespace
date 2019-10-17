package michaelj.namespace.namespace;

import com.sun.corba.se.impl.orbutil.graph.Graph;

public class tester {
    public static void main(String args[]) {
        int vertices = 6;
        WeightedGraph graph = new WeightedGraph(vertices);
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 3);
        graph.addEdge(1, 3, 2);
        graph.addEdge(1, 2, 5);
        graph.addEdge(2, 3, 7);
        graph.addEdge(3, 4, 2);
        graph.addEdge(4, 0, 4);
        graph.addEdge(4, 1, 4);
        graph.addEdge(4, 5, 6);
        graph.printGraph();

    }
}

