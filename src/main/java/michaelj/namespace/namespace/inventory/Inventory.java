package michaelj.namespace.namespace.inventory;

import michaelj.namespace.namespace.herbology.HerbBag;

public class Inventory {
    public HerbBag herbBag;

    public Inventory(){
        this.herbBag = new HerbBag("Homespun Herb Pouch", 30);
    }

    public void speak(){
        System.out.println("I exist");
    }
}
