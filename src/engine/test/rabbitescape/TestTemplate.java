package rabbitescape;

import rabbitescape.engine.*;
import rabbitescape.engine.textworld.TextWorldManip;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class TestTemplate {
    private Rabbit rabbit;
    private World world;
    private BehaviourTools behaviourTools;

    @Before
    public void setup() {
        // Rabbit 및 World 초기화
        rabbit = new RabbitTest(0, 0, Direction.RIGHT, Rabbit.Type.RABBIT);
        world = TextWorldManip.createWorld(
            "#####"
        );

        // 싱글톤의 초기화 메서드를 호출해 상태 재설정
        behaviourTools = BehaviourTools.getInstance(rabbit, world);
        behaviourTools.initialize(rabbit, world);
    }

    @Test
    public void testRabbitIsFalling() {
        rabbit.state = ChangeDescription.State.RABBIT_FALLING;
        assertTrue(behaviourTools.rabbitIsFalling());
    }

    @Test
    public void testRabbitIsClimbing() {
        rabbit.state = ChangeDescription.State.RABBIT_CLIMBING_LEFT_START;
        assertTrue(behaviourTools.rabbitIsClimbing());
    }

    @Test
    public void testRabbitIsBashing() {
        rabbit.state = ChangeDescription.State.RABBIT_BASHING_RIGHT;
        assertTrue(behaviourTools.rabbitIsBashing());
    }
}