package michaelj.namespace.namespace;

import michaelj.namespace.namespace.inventory.Inventory;

import java.util.List;

public class tester {
    public static void main(String args[]){
        Inventory bates = new Inventory();
        bates.getHerbBag().forageForHerbs();

        List<String> one = bates.getHerbBag().getContentKeys();
        List<Integer> two = bates.getHerbBag().getContentValues();

        for(String item: one){
            System.out.println(item);
        }

        for(Integer does: two){
            System.out.println(does);
        }
    }
}
