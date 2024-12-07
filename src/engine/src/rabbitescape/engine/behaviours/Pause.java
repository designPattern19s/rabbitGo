package rabbitescape.engine.behaviours;

import rabbitescape.engine.ChangeDescription.State;
import static rabbitescape.engine.ChangeDescription.State.*;
import static rabbitescape.engine.Token.Type.*;

import rabbitescape.engine.*;

import java.util.Map;

public class Pause extends Behaviour
{
    static final int PAUSING_TIME = 5;  // 총 멈추는 시간

    int pausingCnt = 0;

    private static Pause instance;

    private Pause(){}

    public static Pause getInstance() {
        if (instance == null) {
            instance = new Pause();
        }
        return instance;
    }

    @Override
    public void cancel()
    {
        pausingCnt = 0;
    }

    @Override
    public void getVariables(RabbitBehaviourVariables vars) {
        pausingCnt = vars.pausingCnt;
    }

    @Override
    public void saveVariables(RabbitBehaviourVariables vars)
    {
        vars.pausingCnt = pausingCnt;
    }

    @Override
    public boolean checkTriggered( Rabbit rabbit, World world )
    {
        BehaviourTools t = new BehaviourTools( rabbit, world );
        boolean isTriggered = t.pickUpToken( pause );
        if (isTriggered) {
            pausingCnt = PAUSING_TIME;  // 트리거가 발생하면 정해진 시간 만큼 cnt 설정
        }
        return isTriggered;
    }

    @Override
    public State newState(
        BehaviourTools t,
        boolean triggered
    )
    {
        if ( pausingCnt > 0 || triggered )  // cnt가 0이상이면 계속 실행
        {
            t.rabbit.possiblyUndoSlopeBashHop( t.world );

            pausingCnt--;  // 한 번 진행될 때 마다 cnt 감소

            Block here = t.blockHere();
            // PAUSING State값
            if( BehaviourTools.isRightRiseSlope( here ) )
            {
                return RABBIT_PAUSING_RISE_RIGHT;
            }
            else if ( BehaviourTools.isLeftRiseSlope( here ) )
            {
                return RABBIT_PAUSING_RISE_LEFT;
            }
            else
            {
                return RABBIT_PAUSING;
            }
        }

        return null;
    }

    @Override
    public boolean behave(
        World world,
        Rabbit rabbit,
        State state
    )
    {
        return false;  // PAUSE는 잠시 멈추는 것이므로 behave 없음
    }

    @Override
    public void saveState( Map<String, String> saveState )
    {
        BehaviourState.addToStateIfGtZero(
            saveState, "Pause.abilityActive", pausingCnt
        );
    }

    @Override
    public void restoreFromState( Map<String, String> saveState )
    {
        pausingCnt = BehaviourState.restoreFromState(
            saveState, "Pause.abilityActive", pausingCnt
        );
    }
}
