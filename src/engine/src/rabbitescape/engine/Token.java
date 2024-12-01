package rabbitescape.engine;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import rabbitescape.engine.err.RabbitEscapeException;

import static rabbitescape.engine.AbState.*;

public class Token extends Thing
{
    protected AbState state;

    public static class UnknownType extends RabbitEscapeException
    {
        public final Type type;

        // FIXME
        public UnknownType( Type type )
        {
            this.type = type;
        }

        private static final long serialVersionUID = 1L;
    }

    public static enum Type
    {
        bash,
        dig,
        bridge,
        block,
        climb,
        explode,
        brolly,
        slower,
        pause,
        testdig,
        temp_block
    }

    public final Type type;

    public Token( int x, int y, Type type )
    {
        super( x, y, null );
        this.state = switchType( type, false, false, true );
        this.type = type;
    }

    public Token( int x, int y, Type type, World world )
    {
        this( x, y, type );
        boolean onSlope = BehaviourTools.isSlope( world.getBlockAt( x, y ) );
        // Can't use calcNewState here since we have just been created, so
        // can't be moving (until a time step passes).
        this.state = switchType( type, false, false, onSlope );
    }

    private static AbState switchType(
        Type type,
        boolean moving,
        boolean slopeBelow,
        boolean onSlope
    )
    {
        return chooseState( moving, slopeBelow, onSlope, FALLING, STILL, FALL_TO_SLOPE, ON_SLOPE );
    }

    private static AbState chooseState(
        boolean moving,
        boolean slopeBelow,
        boolean onSlope,
        AbState falling,
        AbState onFlat,
        AbState fallingToSlope,
        AbState onSlopeState
    )
    {
        if ( onSlope )
        {
            return onSlopeState;
        }
        if ( !moving )
        {
            return onFlat;
        }
        if ( slopeBelow )
        {
            return fallingToSlope;
        }
        return falling;
    }

    @Override
    public void calcNewState( World world )
    {
        Block onBlock = world.getBlockAt( x, y );
        Block belowBlock = world.getBlockAt( x, y + 1 );
        boolean still = (
            BehaviourTools.s_isFlat( belowBlock )
                || ( onBlock != null )
                || BridgeTools.someoneIsBridgingAt( world, x, y )
        );

        state = switchType( type, !still,
            BehaviourTools.isSlope( belowBlock ),
            BehaviourTools.isSlope( onBlock ) );
    }

    @Override
    public void step( World world )
    {
        switch ( state )
        {
            case FALLING:
            case FALL_TO_SLOPE:
            {
                ++y;

                if ( y >= world.size.height )
                {
                    world.changes.removeToken( this );
                }

                return;
            }
            default:
                // Nothing to do
        }
    }

    @Override
    public Map<String, String> saveState( boolean runtimeMeta )
    {
        return new HashMap<String, String>();
    }

    @Override
    public void restoreFromState( Map<String, String> state )
    {
    }

    public static String name( Type ability )
    {
        String n = ability.name();
        return n.substring( 0, 1 ).toUpperCase() + n.substring( 1 );
    }

    @Override
    public String overlayText()
    {
        return type.toString();
    }

    @Override
    public String stateName()
    {
        System.out.println("toto token_" + type.name() + "_" + state.name().toLowerCase( Locale.ENGLISH ));

        return "token_" + type.name() + "_" + state.name().toLowerCase( Locale.ENGLISH );
    }
}
