package michaelj.namespace.namespace.board;

import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Dice {

    public static int rollDice(int sides){
        int roll = (int) (Math.random() * sides) + 1;
        return roll;
    }
}
