package rabbitescape.engine.behaviours;

import rabbitescape.engine.*;

public class Slower extends Behaviour
{
    @Override
    public ChangeDescription.State newState(
        BehaviourTools t,
        boolean triggered
    )
    {
        return null;
    }

    @Override
    public boolean behave(
        World world,
        Rabbit rabbit,
        ChangeDescription.State state
    )
    {
        return false;
    }

    @Override
    public boolean checkTriggered( Rabbit rabbit, World world )
    {
        return false;
    }

    @Override
    public void cancel()
    {

    }
}
