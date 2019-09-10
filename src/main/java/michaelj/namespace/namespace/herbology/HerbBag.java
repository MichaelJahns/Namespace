package michaelj.namespace.namespace.herbology;

import java.util.HashMap;

public class HerbBag {
    private String name;
    private int capacity;

    private HashMap<String, Integer> stock;

    public HerbBag(String name, int capacity){
        this.name = name;
        this.capacity = capacity;
        this.stock = new HashMap<String, Integer>();
    }

    public void speak(){
        System.out.println("I also exist");
    }

    public boolean checkHerbBag(String herb){
        Boolean output = this.stock.containsKey(herb);
        return output;
    }

    public void addHerbToBag(String herb, int quanity){
        this.stock.merge(herb, quanity, Integer::sum);
    }


    //create function to decrement kvpair
    //create function to delete kvpair if 0;


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
