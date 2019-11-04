package michaelj.namespace.namespace;

import org.springframework.lang.Nullable;

public class Edge{
    int source;
    int destination;
    int closeness;
    String description;

    public Edge(int source, int destination, int closeness){
        this.source = source;
        this.destination = destination;
        this.closeness = closeness;
    }

    public Edge(int source, int destination, int closeness, @Nullable String description){
        this.source = source;
        this.destination = destination;
        this.closeness = closeness;
        this.description = description;
    }
}
