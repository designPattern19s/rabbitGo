package rabbitescape.engine.behaviours;

import static rabbitescape.engine.ChangeDescription.State.*;
import static rabbitescape.engine.Token.Type.*;

import java.util.Map;

import rabbitescape.engine.*;
import rabbitescape.engine.ChangeDescription.State;

public class Digging extends Behaviour
{
    @Override
    public void cancel(Rabbit rabbit)
    {
        rabbit.stepsOfDigging = 0;
    }

    @Override
    public boolean checkTriggered( Rabbit rabbit, World world )
    {
        BehaviourTools t = new BehaviourTools( rabbit, world );
        return t.pickUpToken( dig );
    }

    @Override
    public void cancel()
    {

    }

    @Override
    public State newState( BehaviourTools t, boolean triggered )
    {
        if ( !triggered && t.rabbit.stepsOfDigging == 0 )
        {
            return null;
        }

        t.rabbit.possiblyUndoSlopeBashHop( t.world );

        if ( t.rabbit.state == RABBIT_DIGGING )
        {
            t.rabbit.stepsOfDigging = 1;
            return RABBIT_DIGGING_2;
        }

        if (
               triggered
            || t.rabbit.stepsOfDigging > 0
        )
        {
            if ( t.rabbit.onSlope && t.blockHere() != null )
            {
                t.rabbit.stepsOfDigging = 1;
                return RABBIT_DIGGING_ON_SLOPE;
            }
            else if ( t.blockBelow() != null )
            {
                if ( t.blockBelow().material == Block.Material.METAL )
                {
                    t.rabbit.stepsOfDigging = 0;
                    return RABBIT_DIGGING_USELESSLY;
                }
                else
                {
                    t.rabbit.stepsOfDigging = 2;
                return RABBIT_DIGGING;
                }
            }
        }

        --t.rabbit.stepsOfDigging;
        return null;
    }

    @Override
    public boolean behave( World world, Rabbit rabbit, State state )
    {
        switch ( state )
        {
            case RABBIT_DIGGING:
            {
                world.changes.removeBlockAt( rabbit.x, rabbit.y + 1 );
                ++rabbit.y;
                return true;
            }
            case RABBIT_DIGGING_ON_SLOPE:
            {
                world.changes.removeBlockAt( rabbit.x, rabbit.y );
                rabbit.onSlope = false;
                return true;
            }
            case RABBIT_DIGGING_2:
            case RABBIT_DIGGING_USELESSLY:
            {
                return true;
            }
            default:
            {
                return false;
            }
        }
    }

    @Override
    public void saveState( Map<String, String> saveState , Rabbit rabbit )
    {
        BehaviourState.addToStateIfGtZero(
            saveState, "Digging.stepsOfDigging", rabbit.stepsOfDigging );
    }


    @Override
    public void restoreFromState( Map<String, String> saveState , Rabbit rabbit )
    {
        rabbit.stepsOfDigging = BehaviourState.restoreFromState(
            saveState, "Digging.stepsOfDigging", rabbit.stepsOfDigging );
    }

    public static boolean isDigging( State state )
    {
        switch ( state )
        {
            case RABBIT_DIGGING:
            case RABBIT_DIGGING_2:
            case RABBIT_DIGGING_ON_SLOPE:
            case RABBIT_DIGGING_USELESSLY:
                return true;
            default:
                return false;
        }
    }

}
