package rabbitescape.engine.behaviours;

import static rabbitescape.engine.ChangeDescription.State.*;
import static rabbitescape.engine.Direction.*;
import static rabbitescape.engine.Token.Type.*;

import java.util.Map;

import rabbitescape.engine.*;
import rabbitescape.engine.ChangeDescription.State;

public class Bashing extends Behaviour
{

    @Override
    public void cancel(Rabbit rabbit)
    {
        rabbit.stepsOfBashing = 0;
    }

    @Override
    public boolean checkTriggered( Rabbit rabbit, World world )
    {
        BehaviourTools t = new BehaviourTools( rabbit, world );

        return t.pickUpToken( bash );
    }

    @Override
    public void cancel()
    {

    }

    @Override
    public State newState( BehaviourTools t, boolean triggered )
    {
        if ( triggered || t.rabbit.stepsOfBashing > 0 )
        {
            if (
                   t.isOnUpSlope()
                && t.blockAboveNext() != null
            )
            {
                if (t.blockAboveNext().material == Block.Material.METAL)
                {
                    t.rabbit.stepsOfBashing = 0;
                    return t.rl(
                        RABBIT_BASHING_USELESSLY_RIGHT_UP,
                        RABBIT_BASHING_USELESSLY_LEFT_UP
                        );
                }
                else
                {
                    t.rabbit.stepsOfBashing = 2;
                    return t.rl(
                        RABBIT_BASHING_UP_RIGHT,
                        RABBIT_BASHING_UP_LEFT
                    );
                }
            }
            else if (
                t.isOnUpSlope()
             && t.blockAboveNext() == null
             && triggered
            )
            {
                return t.rl(
                    RABBIT_BASHING_USELESSLY_RIGHT_UP,
                    RABBIT_BASHING_USELESSLY_LEFT_UP
                );
            }
            else if ( t.blockNext() != null )
            {
                if ( t.blockNext().material == Block.Material.METAL )
                {
                    t.rabbit.stepsOfBashing = 0;
                    return t.rl(
                        RABBIT_BASHING_USELESSLY_RIGHT,
                        RABBIT_BASHING_USELESSLY_LEFT
                    );
                }
                else
                {
                    t.rabbit.stepsOfBashing = 2;
                    return t.rl(
                        RABBIT_BASHING_RIGHT,
                        RABBIT_BASHING_LEFT
                    );
                }
            }
            else if ( triggered )
            {
                return t.rl(
                    RABBIT_BASHING_USELESSLY_RIGHT,
                    RABBIT_BASHING_USELESSLY_LEFT
                );
            }
        }
        --t.rabbit.stepsOfBashing;
        return null;
    }

    @Override
    public boolean behave( World world, Rabbit rabbit, State state )
    {

        switch ( state )
        {
            case RABBIT_BASHING_RIGHT:
            case RABBIT_BASHING_LEFT:
            {
                rabbit.slopeBashHop = false;
                world.changes.removeBlockAt( destX( rabbit ), rabbit.y );
                return true;
            }
            case RABBIT_BASHING_UP_RIGHT:
            case RABBIT_BASHING_UP_LEFT:
            {
                world.changes.removeBlockAt( destX( rabbit ), rabbit.y - 1 );
                rabbit.slopeBashHop = true;
                rabbit.y -= 1;
                return true;
            }
            case RABBIT_BASHING_USELESSLY_RIGHT:
            case RABBIT_BASHING_USELESSLY_LEFT:
            {
                rabbit.slopeBashHop = false;
                return true;
            }
            case RABBIT_BASHING_USELESSLY_RIGHT_UP:
            case RABBIT_BASHING_USELESSLY_LEFT_UP:
            {
                rabbit.slopeBashHop = true;
                rabbit.y -= 1;
                return true;
            }
            default:
            {
                rabbit.slopeBashHop = false;
                return false;
            }
        }
    }

    private int destX( Rabbit rabbit )
    {
        return ( rabbit.dir == RIGHT ) ? rabbit.x + 1 : rabbit.x - 1;
    }

    @Override
    public void saveState( Map<String, String> saveState , Rabbit rabbit)
    {
        BehaviourState.addToStateIfGtZero(
            saveState, "Bashing.stepsOfBashing", rabbit.stepsOfBashing
        );
    }

    @Override
    public void restoreFromState( Map<String, String> saveState , Rabbit rabbit )
    {
        rabbit.stepsOfBashing = BehaviourState.restoreFromState(
            saveState, "Bashing.stepsOfBashing", rabbit.stepsOfBashing
        );

        if ( rabbit.stepsOfBashing > 0 )
        {
            ++rabbit.stepsOfBashing;
        }
    }
}
