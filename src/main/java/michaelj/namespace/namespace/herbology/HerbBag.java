package michaelj.namespace.namespace.herbology;

import java.util.HashMap;

public class HerbBag {
    private String name;
    private int capacity;

    private HashMap<String, Integer> stock;

    public HerbBag(String name, int capacity){
        this.name = name;
        this.capacity = capacity;
    }

    public void speak(){
        System.out.println("I also exist");
    }
    
    //Getters
    public String getName() {
        return name;
    }
    public int getCapacity() {
        return capacity;
    }
    public HashMap<String, Integer> getStock() {
        return stock;
    }

    //Setters
    public void setName(String name) {
        this.name = name;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
