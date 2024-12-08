package rabbitescape.engine.behaviours;

import static rabbitescape.engine.ChangeDescription.State.*;
import static rabbitescape.engine.Token.Type.brolly;

import java.util.Map;

import rabbitescape.engine.*;
import rabbitescape.engine.ChangeDescription.State;

public class Brollychuting extends Behaviour
{
    private static Brollychuting instance;
    boolean hasAbility = false;
    boolean climbingAbilityActivate = false;
    int diggingStepsOfDigging = 0;

    private Brollychuting(){ }

    public static Brollychuting getInstance() {
        if (instance == null) {
            instance = new Brollychuting();
        }
        return instance;
    }

    public void getVariables( RabbitBehaviourVariables vars) {
        hasAbility = vars.hasAbility_brolly;
        climbingAbilityActivate = vars.abilityActive_climbing;
        diggingStepsOfDigging = vars.stepsOfDigging;
    }

    public void saveVariables(RabbitBehaviourVariables vars)
    {
        vars.hasAbility_brolly = hasAbility;
        vars.abilityActive_climbing = climbingAbilityActivate;
        vars.stepsOfDigging = diggingStepsOfDigging;
    }

    @Override
    public State newState( BehaviourTools t, boolean triggered )
    {
        if ( triggered )
        {
            hasAbility = true;
        }

        if( !hasAbility )
        {
            return null;
        }

        if ( climbingAbilityActivate )
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

    public boolean hasBrolly()
    {
        return hasAbility;
    }

    @Override
    public boolean checkTriggered( Rabbit rabbit, World world )
    {
        BehaviourTools t = BehaviourTools.getInstance( rabbit, world );
        t.initialize( rabbit, world );

        if ( !hasAbility && t.pickUpToken( brolly, true ) )
        {
            return true;
        }

        if( !hasAbility )
        {
            return false;
        }

        if ( climbingAbilityActivate || diggingStepsOfDigging > 2 )
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
    public void saveState( Map<String, String> saveState )
    {
        BehaviourState.addToStateIfTrue(
            saveState, "Brollychuting.hasAbility", hasAbility
        );

    }

    @Override
    public void restoreFromState( Map<String, String> saveState )
    {
        hasAbility = BehaviourState.restoreFromState(
            saveState, "Brollychuting.hasAbility", hasAbility
        );

    }
}
