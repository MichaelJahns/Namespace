package michaelj.namespace.namespace;

import java.util.LinkedList;

public class WeightedGraph {
    int vertices;
    LinkedList<Edge>[] adjacencyList;

    public WeightedGraph(int vertices){
            this.vertices = vertices;
            adjacencyList = new LinkedList[vertices];

            for(int i =0; i < vertices; i++){
                adjacencyList[i] = new LinkedList<>();
            }
        }

        public void addEdge(int source, int destination, int closeness){
            Edge edge = new Edge(source, destination, closeness);
            adjacencyList[source].addFirst(edge);
        }

        public void printGraph(){
            for(int i = 0; i < vertices; i++){
                LinkedList<Edge> list = adjacencyList[i];
                for(int j = 0; j < list.size() ; j++){
                    System.out.println(
                            "character-" + i + " has a connection " + list.get(j).destination + " with closeness " + list.get(j).closeness);
                }
            }
        }

    }
