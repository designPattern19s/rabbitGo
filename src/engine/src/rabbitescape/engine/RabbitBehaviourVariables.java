package rabbitescape.engine;

import rabbitescape.engine.behaviours.Bridging;

public class RabbitBehaviourVariables
{
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
    public int stepsOfBashing = 0;

    public int smallSteps = 0;
    public int bigSteps = 0;
    public Bridging.BridgeType bridgeType = Bridging.BridgeType.ALONG;

    // tempBlocking
    public int blockingCnt = 0;

    // pause
    public int pausingCnt = 0;

    //blocking
    public boolean abilityActive_blocking = false;

    public RabbitBehaviourVariables(int fatalHeight) {
        this.fatalHeight = fatalHeight;
    }
}