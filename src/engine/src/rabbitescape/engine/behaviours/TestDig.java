package rabbitescape.engine.behaviours;

import rabbitescape.engine.*;

import java.util.Map;

import static rabbitescape.engine.Token.Type.testdig;

public class TestDig extends Behaviour
{
    public boolean abilityActive = false;

    @Override
    public void cancel()
    {
        abilityActive = false;
    }

    @Override
    public boolean checkTriggered( Rabbit rabbit, World world )
    {
        BehaviourTools t = new BehaviourTools( rabbit, world );
        return t.pickUpToken( testdig );
    }

    @Override
    public ChangeDescription.State newState(
        BehaviourTools t,
        boolean triggered
    )
    {
        if ( abilityActive || triggered )
        {
            if ( t.rabbit.dir == Direction.RIGHT )
                t.rabbit.dir = Direction.LEFT;
            else if ( t.rabbit.dir == Direction.LEFT )
                t.rabbit.dir = Direction.RIGHT;
            return null;
        }
        return null;
    }

    @Override
    public boolean behave(
        World world,
        Rabbit rabbit,
        ChangeDescription.State state
    )
    {
        Walking walking = new Walking();

        return walking.behave(world, rabbit, state );
    }

    @Override
    public void saveState( Map<String, String> saveState )
    {
        BehaviourState.addToStateIfTrue(
            saveState, "Blocking.abilityActive", abilityActive
        );
    }

    @Override
    public void restoreFromState( Map<String, String> saveState )
    {
        abilityActive = BehaviourState.restoreFromState(
            saveState, "Blocking.abilityActive", abilityActive
        );
    }

}

