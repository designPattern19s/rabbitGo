package rabbitescape.engine.behaviours;

import static rabbitescape.engine.ChangeDescription.State.*;

import rabbitescape.engine.*;
import rabbitescape.engine.ChangeDescription.State;

public class Exiting extends Behaviour
{
    private static Exiting instance;

    private Exiting(){}

    public static Exiting getInstance() {
        if (instance == null) {
            instance = new Exiting();
        }
        return instance;
    }

    public void getVariables(RabbitBehaviourVariables vars) {

    }

    public void saveVariables(RabbitBehaviourVariables vars)
    {

    }

    @Override
    public void cancel()
    {
    }

    @Override
    public boolean checkTriggered( Rabbit rabbit, World world )
    {
        if ( rabbit.type == Rabbit.Type.RABBOT )
        {
            return false;  // Rabbots ignore exits
        }

        for ( Thing thing : world.things )
        {
            if (
                   ( thing instanceof Exit )
                && ( thing.x == rabbit.x && thing.y == rabbit.y )
            )
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public State newState( BehaviourTools t, boolean triggered )
    {
        if ( triggered )
        {
            if ( t.rabbit.state == RABBIT_CLIMBING_LEFT_CONTINUE_2 )
            {
                return RABBIT_ENTERING_EXIT_CLIMBING_LEFT;
            }
            if ( t.rabbit.state == RABBIT_CLIMBING_RIGHT_CONTINUE_2 )
            {
                return RABBIT_ENTERING_EXIT_CLIMBING_RIGHT;
            }
            return RABBIT_ENTERING_EXIT;
        }
        return null;
    }

    @Override
    public boolean behave( World world, Rabbit rabbit, State state )
    {
        if (
               state == RABBIT_ENTERING_EXIT
            || state == RABBIT_ENTERING_EXIT_CLIMBING_RIGHT
            || state == RABBIT_ENTERING_EXIT_CLIMBING_LEFT
           )
        {
            world.changes.saveRabbit( rabbit );
            return true;
        }
        else
        {
            return false;
        }
    }
}
