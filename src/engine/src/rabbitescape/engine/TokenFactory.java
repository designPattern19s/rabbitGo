package rabbitescape.engine;

import rabbitescape.engine.behaviours.TestDig;
import rabbitescape.engine.tokens.Bash;

public class TokenFactory
{
    public Token createToken( int x, int y, Token.Type type, World world ) {
        Token token;

        switch (type) {
            case bash:
                token = new Bash( x, y, type, world );
            default:
                token = null;  // FIXME
        }
        return token;
    }

    public Token createToken( int x, int y, Token.Type type ) {
        Token token;

        switch (type) {
            case bash:
                token = new Bash( x, y, type );
            default:
                token = null;  // FIXME
        }
        return token;
    }
}
