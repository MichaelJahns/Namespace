package michaelj.namespace.namespace;

import com.sun.org.apache.xpath.internal.operations.Bool;
import michaelj.namespace.namespace.inventory.Inventory;

public class tester {
    public static void main(String args[]){
        Inventory xx = new Inventory();
        xx.herbBag.forageForHerbs();
        xx.herbBag.forageForReagents();
        xx.herbBag.bigTester();

    }
}
