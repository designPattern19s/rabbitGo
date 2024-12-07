package rabbitescape.engine.behaviours;

import rabbitescape.engine.*;
import rabbitescape.engine.ChangeDescription.State;

public class RabbotCrash extends Behaviour
{
    private static RabbotCrash instance;

    public static RabbotCrash getInstance() {
        if (instance == null) {
            instance = new RabbotCrash();
        }
        return instance;
    }

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
        if ( rabbit.type == Rabbit.Type.RABBOT )
        {
            for ( Rabbit otherRabbit : world.rabbits )
            {
                if ( otherRabbit.type == Rabbit.Type.RABBIT &&
                    otherRabbit.x == rabbit.x &&
                    otherRabbit.y == rabbit.y
                )
                {
                    world.changes.killRabbit( otherRabbit );
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public State newState( BehaviourTools t, boolean triggered )
    {
        if ( triggered )
        {
            return State.RABBIT_CRASHING;
        }
        else
        {
            return null;
        }
    }

    @Override
    public boolean behave( World world, Rabbit rabbit, State state )
    {
        if ( state == State.RABBIT_CRASHING )
        {
            world.changes.killRabbit( rabbit );
            return true;
        }

        return false;
    }
}
