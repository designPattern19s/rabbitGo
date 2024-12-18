package rabbitescape.engine.behaviours;

import static rabbitescape.engine.CellularDirection.DOWN;
import static rabbitescape.engine.CellularDirection.UP;

import rabbitescape.engine.*;
import rabbitescape.engine.ChangeDescription.State;

public class Drowning extends Behaviour
{
    private static Drowning instance;

    private Drowning(){}

    public static Drowning getInstance() {
        if (instance == null) {
            instance = new Drowning();
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
            return false;  // Rabbots don't drown
        }

        int yCoordinate = rabbit.y;
        CellularDirection directionToCheck = UP;
        if ( rabbit.onSlope )
        {
            // The rabbit's head is at the bottom of the cell above.
            yCoordinate = rabbit.y - 1;
            directionToCheck = DOWN;
        }
        // TODO Find out why the rabbit's y coordinate is allowed to be
        // larger than the size of the world (see solution for easy-12).
        if ( yCoordinate < 0 || yCoordinate >= world.size.height )
        {
            return false;
        }
        for ( WaterRegion waterRegion :
              world.waterTable.getItemsAt( rabbit.x, yCoordinate ) )
        {
            if ( waterRegion.isConnected( directionToCheck ) )
            {
                return ( waterRegion.getContents() >= waterRegion.capacity );
            }
        }
        return false;
    }

    @Override
    public State newState(
        BehaviourTools t,
        boolean triggered )
    {
        return ( triggered ? State.RABBIT_DROWNING : null );
    }

    @Override
    public boolean behave( World world, Rabbit rabbit, State state )
    {
        switch ( state )
        {
        case RABBIT_DROWNING:
            world.changes.killRabbit( rabbit );
            return true;
        default:
            return false;
        }
    }
}
