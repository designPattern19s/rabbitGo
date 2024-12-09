package rabbitescape.engine;

import org.junit.Test;
import rabbitescape.engine.behaviours.Pausing;
import rabbitescape.engine.behaviours.TempBlocking;
import rabbitescape.engine.textworld.TextWorldManip;

import static org.junit.Assert.assertEquals;

public class TestTokens
{
    @Test
    public void checkTempBlockingTriggered() {
        //given
        RabbitTest rabbit = new RabbitTest( 1, 1, Direction.RIGHT, Rabbit.Type.RABBIT);
        World world = TextWorldManip.createWorld(  "   ",
            "#o#",
            "###",
            "###",
            "   ",
            "   ",
            "###" );
        //when
        Behaviour b = rabbit.isTriggered( world );

        //then
        assertEquals(true, b instanceof TempBlocking );
    }

    @Test
    public void checkPauseTriggered() {
        //given
        RabbitTest rabbit = new RabbitTest( 1, 1, Direction.RIGHT, Rabbit.Type.RABBIT);
        World world = TextWorldManip.createWorld(  "   ",
            "#u#",
            "###",
            "###",
            "   ",
            "   ",
            "###" );
        //when
        Behaviour b = rabbit.isTriggered( world );

        //then
        assertEquals(true, b instanceof Pausing );
    }

    @Test
    public void checkRemoveToken() {
        //given
        World world = TextWorldManip.createWorld(  "   ",
            "#u#",
            "###",
            "###",
            "   ",
            "   ",
            "###" );

        //when
        world.abilities.put(Token.Type.erase, 2);
        world.changes.addToken( 1, 1, Token.Type.erase );

        //then
        assertEquals( new Integer( 1 ), new Integer( world.abilities.get( Token.Type.erase )) );
    }

    @Test
    public void checkRemoveTokenWhenNoToken() {
        //given
        World world = TextWorldManip.createWorld(  "   ",
            "#u#",
            "###",
            "###",
            "   ",
            "   ",
            "###" );

        //when
        world.abilities.put(Token.Type.erase, 2);
        world.changes.addToken( 1, 1, Token.Type.erase );

        //then
        assertEquals( new Integer( 1 ), new Integer( world.abilities.get( Token.Type.erase )) );
    }
}
