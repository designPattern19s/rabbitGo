package rabbitescape.engine.behaviours;

import static rabbitescape.engine.ChangeDescription.State.*;
import static rabbitescape.engine.Token.Type.brolly;

import java.util.Map;

import rabbitescape.engine.Behaviour;
import rabbitescape.engine.BehaviourState;
import rabbitescape.engine.BehaviourTools;
import rabbitescape.engine.Block;
import rabbitescape.engine.ChangeDescription.State;
import rabbitescape.engine.Rabbit;
import rabbitescape.engine.World;

public class Brollychuting extends Behaviour
{

    @Override
    public State newState( BehaviourTools t, boolean triggered )
    {
        if ( triggered )
        {
            t.rabbit.hasAbility_brolly = true;
        }

        if( !t.rabbit.hasAbility_brolly )
        {
            return null;
        }

        if ( t.rabbit.abilityActive_climbing )
        {
            return null;
        }

        Block below = t.blockBelow();

        if ( t.isFlat( below ) )
        {
            return null;
        }

        if (
            t.rabbit.onSlope
         && !t.blockHereJustRemoved()
        )
        {
            return null;
        }

        if ( below != null )
        {
            if ( t.isUpSlope( below ) )
            {
                return t.rl(
                    RABBIT_FALLING_1_ONTO_RISE_RIGHT,
                    RABBIT_FALLING_1_ONTO_RISE_LEFT
                );
            }
            else // Must be a slope in the opposite direction
            {
                return t.rl(
                    RABBIT_FALLING_1_ONTO_LOWER_RIGHT,
                    RABBIT_FALLING_1_ONTO_LOWER_LEFT
                );
            }
        }

        return RABBIT_BROLLYCHUTING;
    }

    @Override
    public boolean behave( World world, Rabbit rabbit, State state )
    {
        if ( state == RABBIT_BROLLYCHUTING )
        {
            rabbit.y = rabbit.y + 1;
            return true;
        }
        return false;
    }

    @Override
    public void cancel( Rabbit rabbit )
    {

    }

    public boolean hasBrolly(Rabbit rabbit)
    {
        return rabbit.hasAbility_brolly;
    }

    @Override
    public boolean checkTriggered( Rabbit rabbit, World world )
    {
        BehaviourTools t = new BehaviourTools( rabbit, world );

        if ( !rabbit.hasAbility_brolly && t.pickUpToken( brolly, true ) )
        {
            return true;
        }

        if( !rabbit.hasAbility_brolly )
        {
            return false;
        }

        if ( rabbit.abilityActive_climbing || rabbit.stepsOfDigging > 2 )
        {
            return false;
        }

        if ( t.isFlat( t.blockBelow() ) )
        {
            return false;
        }

        if (
               rabbit.onSlope
            && !t.blockHereJustRemoved()
        )
        {
            return false;
        }

        return true;
    }

    @Override
    public void cancel()
    {
    }

    @Override
    public void saveState( Map<String, String> saveState , Rabbit rabbit )
    {
        BehaviourState.addToStateIfTrue(
            saveState, "Brollychuting.hasAbility", rabbit.hasAbility_brolly
        );

    }

    @Override
    public void restoreFromState( Map<String, String> saveState , Rabbit rabbit )
    {
        rabbit.hasAbility_brolly = BehaviourState.restoreFromState(
            saveState, "Brollychuting.hasAbility", rabbit.hasAbility_brolly
        );

    }
}
