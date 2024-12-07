package rabbitescape.engine;

import static rabbitescape.engine.ChangeDescription.State.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rabbitescape.engine.ChangeDescription.State;
import rabbitescape.engine.behaviours.*;

public class Rabbit extends Thing implements Comparable<Rabbit>
{
    public static enum Type
    {
        RABBIT,
        RABBOT
    }

    public final static int NOT_INDEXED = 0;
    private final List<Behaviour> behaviours;
    private final List<Behaviour> behavioursTriggerOrder;

    public int index;

    private Falling falling;

    public Direction dir;
    public boolean onSlope;
    /** Rabbits move up 1 cell to bash from a slope.
     *  Keep a note, so it can be undone.  */
    public boolean slopeBashHop = false;
    public final Type type;

    public RabbitBehaviourVariables behaviourVariables;

    public Rabbit( int x, int y, Direction dir, Type type )
    {
        super( x, y, RABBIT_WALKING_LEFT );
        this.dir = dir;
        this.onSlope = false;
        this.type = type;
        behaviours = new ArrayList<>();
        behavioursTriggerOrder = new ArrayList<>();
        createBehaviours();
        index = NOT_INDEXED;
        behaviourVariables = new RabbitBehaviourVariables(getFatalHeight());
    }

    private void createBehaviours()
    {
        Climbing climbing = Climbing.getInstance();
        Digging digging = Digging.getInstance();
        Exploding exploding = Exploding.getInstance();
        Burning burning = Burning.getInstance();
        OutOfBounds outOfBounds = OutOfBounds.getInstance();
        Drowning drowning = Drowning.getInstance();
        Exiting exiting = Exiting.getInstance();
        Brollychuting brollychuting = Brollychuting.getInstance();
        falling = Falling.getInstance();
        Bashing bashing = Bashing.getInstance();
        Bridging bridging = Bridging.getInstance();
        Blocking blocking = Blocking.getInstance();
        Walking walking = Walking.getInstance();
        RabbotCrash rabbotCrash = RabbotCrash.getInstance();
        RabbotWait rabbotWait = RabbotWait.getInstance();
        Pause pause = new Pause();
        TempBlocking tempBlocking = new TempBlocking();

        behavioursTriggerOrder.add( exploding );
        behavioursTriggerOrder.add( outOfBounds );
        behavioursTriggerOrder.add( burning );
        behavioursTriggerOrder.add( drowning );
        behavioursTriggerOrder.add( rabbotCrash );
        behavioursTriggerOrder.add( falling );
        behavioursTriggerOrder.add( exiting );
        behavioursTriggerOrder.add( brollychuting );
        behavioursTriggerOrder.add( climbing );
        behavioursTriggerOrder.add( bashing );
        behavioursTriggerOrder.add( digging );
        behavioursTriggerOrder.add( bridging );
        behavioursTriggerOrder.add( blocking );
        behavioursTriggerOrder.add( rabbotWait );
        behavioursTriggerOrder.add( pause );
        behavioursTriggerOrder.add( tempBlocking );
        behavioursTriggerOrder.add( walking );

        behaviours.add( exploding );
        behaviours.add( outOfBounds );
        behaviours.add( burning );
        behaviours.add( drowning );
        behaviours.add( rabbotCrash );
        behaviours.add( falling );
        behaviours.add( exiting );
        behaviours.add( brollychuting );
        behaviours.add( bashing );
        behaviours.add( digging );
        behaviours.add( bridging );
        behaviours.add( blocking );
        behaviours.add( climbing );
        behaviours.add( rabbotWait );
        behaviours.add( pause );
        behaviours.add( tempBlocking );
        behaviours.add( walking );

        assert behavioursTriggerOrder.size() == behaviours.size();
    }

    public boolean isFallingToDeath()
    {
        return falling.isFallingToDeath();
    }

    @Override
    public void calcNewState( World world )
    {
        for ( Behaviour behaviour : behavioursTriggerOrder )
        {
            behaviour.triggered = false;
        }

        for ( Behaviour behaviour : behavioursTriggerOrder )
        {
            behaviour.getVariables( behaviourVariables );
            behaviour.triggered = behaviour.checkTriggered( this, world );
            behaviour.saveVariables( behaviourVariables );
            if ( behaviour.triggered )
            {
                cancelAllBehavioursExcept( behaviour );
            }
        }

        boolean done = false;
        for ( Behaviour behaviour : behaviours )
        {

            State thisState = behaviour.newState(
                new BehaviourTools( this, world ), behaviour.triggered );

            if ( thisState != null && !done )
            {
                state = thisState;
                done = true;
            }
        }

    }

    private void cancelAllBehavioursExcept( Behaviour exception )
    {
        for ( Behaviour behaviour : behaviours )
        {
            if ( behaviour != exception )
            {
                behaviour.getVariables( behaviourVariables );
                behaviour.cancel();
                behaviour.saveVariables( behaviourVariables );
            }
        }
    }

    public void possiblyUndoSlopeBashHop( World world )
    {
        if ( !slopeBashHop )
        {
            return;
        }
        BehaviourTools t = new BehaviourTools( this, world );
        if ( t.blockHere() != null ||
            !BehaviourTools.isSlope( t.blockBelow() ) )
        {
            return;
        }
        ++y;
        slopeBashHop = false;
    }

    @Override
    public void step( World world )
    {
        for ( Behaviour behaviour : behaviours )
        {
            behaviour.getVariables( behaviourVariables );
            boolean handled = behaviour.behave( world, this, state );
            behaviour.saveVariables( behaviourVariables );
            if ( handled )
            {
                break;
            }
        }
    }

    @Override
    public Map<String, String> saveState( boolean runtimeMeta )
    {
        Map<String, String> ret = new HashMap<String, String>();
        if ( !runtimeMeta )
        {
            return ret;
        }

        BehaviourState.addToStateIfGtZero( ret, "index", index );
        BehaviourState.addToStateIfTrue( ret, "onSlope", onSlope );

        for ( Behaviour behaviour : behaviours )
        {
            behaviour.getVariables( behaviourVariables );
            behaviour.saveState( ret );
            behaviour.saveVariables( behaviourVariables );
        }

        return ret;
    }

    @Override
    public void restoreFromState( Map<String, String> state )
    {
        index = BehaviourState.restoreFromState( state, "index", -1 );

        onSlope = BehaviourState.restoreFromState(
            state, "onSlope", false
        );

        for ( Behaviour behaviour : behaviours )
        {
            behaviour.getVariables( behaviourVariables );
            behaviour.restoreFromState( state );
            behaviour.saveVariables( behaviourVariables );
        }
    }

    @Override
    public String overlayText()
    {
        String fmt;
        switch ( dir )
        {
        case LEFT:
            fmt = "<[%d] ";
            break;
        case RIGHT:
            fmt = " [%d]>";
            break;
        default:
            throw new RuntimeException( "Rabbit should only be left or right");
        }
        return String.format( fmt, index ) ;
    }

    @Override
    public int compareTo( Rabbit r )
    {
        return this.index - r.index;
    }

    @Override
    public boolean equals( Object o )
    {
        if ( null == o || !( o instanceof Rabbit ) )
        {
            return false;
        }
        return ( (Rabbit)o ).index == this.index;
    }

    @Override
    public int hashCode()
    {
        return index;
    }

    @Override
    public String stateName()
    {
        String normalName = super.stateName();
        if ( type == Type.RABBIT )
        {
            return normalName;
        }
        else
        {
            return normalName.replaceFirst(
                "^rabbit", type.name().toLowerCase() );
        }
    }

    /** Rabbots can fall further than rabbits. */
    private int getFatalHeight()
    {
        return ( type == Type.RABBIT ? 4 : 5 );
    }
}
