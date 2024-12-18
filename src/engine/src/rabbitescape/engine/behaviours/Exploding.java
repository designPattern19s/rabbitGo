package rabbitescape.engine.behaviours;

import static rabbitescape.engine.Token.Type.*;
import static rabbitescape.engine.ChangeDescription.State.*;

import rabbitescape.engine.*;
import rabbitescape.engine.ChangeDescription.State;

public class Exploding extends Behaviour
{
    private static Exploding instance;

    private Exploding(){}

    public static Exploding getInstance() {
        if (instance == null) {
            instance = new Exploding();
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
        BehaviourTools t = BehaviourTools.getInstance( rabbit, world );
        t.initialize( rabbit, world );
        return t.pickUpToken( explode, true );
    }

    @Override
    public State newState( BehaviourTools t, boolean triggered )
    {
        if ( triggered )
        {
            return RABBIT_EXPLODING;
        }
        return null;
    }

    @Override
    public boolean behave( World world, Rabbit rabbit, State state )
    {
        if ( state == RABBIT_EXPLODING )
        {
            world.changes.killRabbit( rabbit );
            return true;
        }

        return false;
    }
}
