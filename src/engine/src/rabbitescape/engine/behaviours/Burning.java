package rabbitescape.engine.behaviours;

import static rabbitescape.engine.ChangeDescription.State.*;

import rabbitescape.engine.*;
import rabbitescape.engine.ChangeDescription.State;

public class Burning extends Behaviour
{
    private static Burning instance;

    public static Burning getInstance() {
        if (instance == null) {
            instance = new Burning();
        }
        return instance;
    }

    private Burning(){}

    public void getVariables( RabbitBehaviourVariables vars) {

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
        return world.fireAt( rabbit.x, rabbit.y );
    }

    @Override
    public State newState(
        BehaviourTools t, boolean triggered
        )
    {
        if ( triggered )
        {
            if ( t.rabbit.onSlope )
            {
                return RABBIT_BURNING_ON_SLOPE;
            }
            else
            {
                return RABBIT_BURNING;
            }
        }

        return null;
    }

    @Override
    public boolean behave( World world, Rabbit rabbit, State state )
    {
        switch ( state )
        {
        case RABBIT_BURNING:
        case RABBIT_BURNING_ON_SLOPE:
        {
            world.changes.killRabbit( rabbit );
            return true;
        }
        default:
        {
            return false;
        }
        }
    }
}
