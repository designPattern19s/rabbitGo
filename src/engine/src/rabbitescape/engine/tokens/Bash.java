package rabbitescape.engine.tokens;

import rabbitescape.engine.Token;
import rabbitescape.engine.World;

import java.util.Locale;

public class Bash extends Token
{

    public Bash( int x, int y, Type type )
    {
        super( x, y, type );
    }

    public Bash( int x, int y, Type type, World world )
    {
        super( x, y, type, world );
    }

    @Override
    public String stateName()
    {
        System.out.println("toto token_" + type.name() + state.name().toLowerCase( Locale.ENGLISH ));

        return "token_" + type.name() + state.name().toLowerCase( Locale.ENGLISH );
    }
}
