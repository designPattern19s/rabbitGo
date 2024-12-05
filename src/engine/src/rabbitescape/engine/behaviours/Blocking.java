package rabbitescape.engine.behaviours;

import static rabbitescape.engine.ChangeDescription.State.*;
import static rabbitescape.engine.Token.Type.*;

import java.util.Map;

import rabbitescape.engine.*;
import rabbitescape.engine.ChangeDescription.State;

public class Blocking extends Behaviour
{

    @Override
    public void cancel(Rabbit rabbit)
    {
        rabbit.abilityActive_blocking = false;
    }

    @Override
    public boolean checkTriggered( Rabbit rabbit, World world )
    {
        BehaviourTools t = BehaviourTools.getInstance( rabbit, world );
        t.initialize( rabbit, world );
        return t.pickUpToken( block );
    }

    @Override
    public void cancel()
    {

    }

    @Override
    public State newState( BehaviourTools t, boolean triggered )
    {
        if ( t.rabbit.abilityActive_blocking || triggered )
        {
            t.rabbit.possiblyUndoSlopeBashHop( t.world );
            t.rabbit.abilityActive_blocking = true;
            Block here = t.blockHere();
            if( BehaviourTools.isRightRiseSlope( here ) )
            {
                return RABBIT_BLOCKING_RISE_RIGHT;
            }
            else if ( BehaviourTools.isLeftRiseSlope( here ) )
            {
                return RABBIT_BLOCKING_RISE_LEFT;
            }
            else
            {
                return RABBIT_BLOCKING;
            }
        }

        return null;
    }

    @Override
    public boolean behave( World world, Rabbit rabbit, State state )
    {
        return isBlocking( state );
    }

    @Override
    public void saveState( Map<String, String> saveState , Rabbit rabbit)
    {
        BehaviourState.addToStateIfTrue(
            saveState, "Blocking.abilityActive", rabbit.abilityActive_blocking
        );
    }

    @Override
    public void restoreFromState( Map<String, String> saveState , Rabbit rabbit )
    {
        rabbit.abilityActive_blocking = BehaviourState.restoreFromState(
            saveState, "Blocking.abilityActive", rabbit.abilityActive_blocking
        );
    }

    public static boolean blockerAt( World world, int nextX, int nextY )
    {
        Rabbit[] rabbits = world.getRabbitsAt( nextX, nextY );
        for ( Rabbit r : rabbits )
        {
            if ( isBlocking( r.state ) )
            {
                return true;
            }
        }
        return false;
    }

    static boolean isBlocking( State s )
    {
        switch ( s ) {
        case RABBIT_BLOCKING:
        case RABBIT_BLOCKING_RISE_RIGHT:
        case RABBIT_BLOCKING_RISE_LEFT:
            return true;
        default:
            return false;
        }
    }
}
