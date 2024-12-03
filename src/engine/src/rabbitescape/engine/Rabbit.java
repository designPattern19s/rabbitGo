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

    public Direction dir;
    public boolean onSlope;
    /** Rabbits move up 1 cell to bash from a slope.
     *  Keep a note, so it can be undone.  */
    public boolean slopeBashHop = false;
    public final Type type;


    //climbing
    public boolean hasAbility_climbing = false;
    public boolean abilityActive_climbing = false;
    //digging
    public int stepsOfDigging;
    //brollychuting
    public boolean hasAbility_brolly = false;

    //falling
    public int heightFallen = 0;
    public int fatalHeight;

    //bashing
    public int stepsOfBashing;
    //bridging
    public enum BridgeType
    {
        ALONG,
        UP,
        DOWN_UP
    }

    public int smallSteps = 0;
    public int bigSteps = 0;
    public Rabbit.BridgeType bridgeType = BridgeType.ALONG;

    //blocking
    public boolean abilityActive_blocking = false;


    private static final Climbing climbing = new Climbing();
    private static final     Digging digging = new Digging();
    private static final     Exploding exploding = new Exploding();
    private static final     Burning burning = new Burning();
    private static final     OutOfBounds outOfBounds = new OutOfBounds();
    private static final     Drowning drowning = new Drowning();
    private static final     Exiting exiting = new Exiting();
    private static final     Brollychuting brollychuting = new Brollychuting();
    private static final     Falling falling = new Falling();
    private static final     Bashing bashing = new Bashing();
    private static final     Bridging bridging = new Bridging();
    private static final     Blocking blocking = new Blocking();
    private static final     Walking walking = new Walking();
    private static final     RabbotCrash rabbotCrash = new RabbotCrash();
    private static final     RabbotWait rabbotWait = new RabbotWait();



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
        fatalHeight = getFatalHeight();
    }

    private void createBehaviours()
    {
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
        behaviours.add( walking );

        assert behavioursTriggerOrder.size() == behaviours.size();
    }

    public boolean isFallingToDeath(Rabbit rabbit)
    {
        return falling.isFallingToDeath(rabbit);
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
            behaviour.triggered = behaviour.checkTriggered( this, world );
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
                behaviour.cancel();
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
            boolean handled = behaviour.behave( world, this, state );
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
            behaviour.saveState( ret, this );
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
            behaviour.restoreFromState( state, this );
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
