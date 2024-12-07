package rabbitescape.engine;

import rabbitescape.engine.Behaviour;
import rabbitescape.engine.behaviours.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BehaviourFactory {
    private static final List<Behaviour> behaviours = new ArrayList<>();
    private static final Falling FALLING = new Falling();

    static {
        register(new Exploding());
        register(new OutOfBounds());
        register(new Burning());
        register(new Drowning());
        register(new RabbotCrash());
        register(FALLING);
        register(new Exiting());
        register(new Brollychuting());
        register(new Climbing());
        register(new Bashing());
        register(new Digging());
        register(new Bridging());
        register(new Blocking());
        register(new RabbotWait());
        register(new Walking());
    }

    private BehaviourFactory() {}

    private static void register(Behaviour behaviour) {
        behaviours.add(behaviour);
    }

    public static List<Behaviour> createBehaviours() {
        return Collections.unmodifiableList(behaviours);
    }

    public static Falling getFallingBehavior(){
        return FALLING;
    }
}