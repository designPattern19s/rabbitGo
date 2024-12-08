package rabbitescape.engine.behaviours;

import static rabbitescape.engine.ChangeDescription.State.*;
import static rabbitescape.engine.Token.Type.*;

import java.util.Map;

import rabbitescape.engine.*;
import rabbitescape.engine.ChangeDescription.State;

public class TempBlocking extends Behaviour
{
    static final int BLOCKING_TIME = 5;  // 총 멈추는 시간

    int blockingCnt = 0;


    @Override
    public void cancel()
    {
        blockingCnt = 0;
    }


    @Override
    public boolean checkTriggered( Rabbit rabbit, World world )
    {
        BehaviourTools t = BehaviourTools.getInstance( rabbit, world );
        t.initialize( rabbit, world );
        boolean isTriggered = t.pickUpToken( tempBlock );
        if (isTriggered) {
            blockingCnt = BLOCKING_TIME;  // 트리거가 발생하면 정해진 시간 만큼 cnt 설정
        }
        return isTriggered;
    }

    @Override
    public State newState( BehaviourTools t, boolean triggered )
    {
        if ( blockingCnt > 0 || triggered )  // cnt가 0이상이면 계속 실행
        {
            t.rabbit.possiblyUndoSlopeBashHop( t.world );
            blockingCnt--;  // 한 번 진행될 때 마다 cnt 감소
            Block here = t.blockHere();
            if( BehaviourTools.isRightRiseSlope( here ) )
            {
                return RABBIT_BLOCKING_RISE_RIGHT;
            }
            else if ( BehaviourTools.isLeftRiseSlope( here ) )
            {
                return RABBIT_BLOCKING_RISE_LEFT;
            }
            else
            {
                return RABBIT_BLOCKING;
            }
        }

        return null;
    }

    @Override
    public boolean behave( World world, Rabbit rabbit, State state )
    {
        return isBlocking( state );
    }

    @Override
    public void saveState( Map<String, String> saveState )
    {
        BehaviourState.addToStateIfGtZero(
            saveState, "TempBlocking.abilityActive", blockingCnt
        );
    }

    @Override
    public void restoreFromState( Map<String, String> saveState )
    {
        blockingCnt = BehaviourState.restoreFromState(
            saveState, "TempBlocking.abilityActive", blockingCnt
        );
    }

    static boolean isBlocking( State s )
    {
        switch ( s ) {
            case RABBIT_BLOCKING:
            case RABBIT_BLOCKING_RISE_RIGHT:
            case RABBIT_BLOCKING_RISE_LEFT:
                return true;
            default:
                return false;
        }
    }
}

