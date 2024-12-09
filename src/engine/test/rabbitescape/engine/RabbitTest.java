package rabbitescape.engine;

import java.util.List;

public class RabbitTest extends Rabbit{
    public RabbitTest( int x, int y, Direction dir, Type type )
    {
        super( x, y, dir, type );
    }

    public Behaviour isTriggered (World world) {
        for ( Behaviour behaviour : behavioursTriggerOrder )
        {
            behaviour.getVariables( behaviourVariables );
            behaviour.triggered = behaviour.checkTriggered( this, world );
            behaviour.saveVariables( behaviourVariables );
            if( behaviour.triggered )
                return behaviour;
        }

        return null;
    }

    public List<Behaviour> getBehaviours()
    {
        return behaviours;
    }
}
