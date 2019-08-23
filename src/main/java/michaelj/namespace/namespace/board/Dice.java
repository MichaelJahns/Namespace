package michaelj.namespace.namespace.board;

import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Dice {

    public static List rollDice(int sides, int count, @Nullable boolean advantage, @Nullable boolean disadvantage){
        List output = new ArrayList();

        for(int i = 0; i < count; i++){
            int roll = (int) (Math.random() * sides) + 1;
            output.add(roll);
        }

        if(advantage){
            //shuffle with greatest numbers first
        }else if(disadvantage) {
            //shuffle with lowest numbers first
        }
        return  output;
    }
}
