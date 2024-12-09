package rabbitescape;

import org.junit.Test;
import rabbitescape.engine.*;
import rabbitescape.engine.textworld.TextWorldManip;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestSingleton
{
    @Test
    public void behavioursSingletonTest() {
        RabbitTest rabbit1 = new RabbitTest( 1, 2, Direction.RIGHT, Rabbit.Type.RABBIT);
        RabbitTest rabbit2 = new RabbitTest( 1, 2, Direction.RIGHT, Rabbit.Type.RABBIT);

        List<Behaviour> b1 = rabbit1.getBehaviours();
        List<Behaviour> b2 = rabbit2.getBehaviours();

        int i = 0;
        boolean flag = true;
        for(Behaviour behaviour : b1) {
            if( !(behaviour == b2.get( i ) ))
                flag = false;
            i++;
        }

        assertTrue(flag);
    }

    @Test
    public void behaviourToolsSingletonTest(){
        RabbitTest rabbit1 = new RabbitTest( 1, 2, Direction.RIGHT, Rabbit.Type.RABBIT);
        RabbitTest rabbit2 = new RabbitTest( 1, 2, Direction.RIGHT, Rabbit.Type.RABBIT);
        World world = TextWorldManip.createWorld(  "   ",
            "#o#",
            "###",
            "###",
            "   ",
            "   ",
            "###" );
        BehaviourTools b1 = BehaviourTools.getInstance( rabbit1, world );
        BehaviourTools b2 = BehaviourTools.getInstance( rabbit2, world );

        assertEquals(b1, b2);
    }
}
