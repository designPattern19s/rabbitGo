package rabbitescape.engine;

import rabbitescape.engine.behaviours.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BehaviourFactory {
    private static final Exploding EXPLODING = new Exploding();
    private static final OutOfBounds OUT_OF_BOUNDS = new OutOfBounds();
    private static final Burning BURNING = new Burning();
    private static final Drowning DROWNING = new Drowning();
    private static final RabbotCrash RABBOT_CRASH = new RabbotCrash();
    private static final Falling FALLING = new Falling();
    private static final Exiting EXITING = new Exiting();
    private static final Brollychuting BROLLYCHUTING = new Brollychuting();
    private static final Climbing CLIMBING = new Climbing();
    private static final Bashing BASHING = new Bashing();
    private static final Digging DIGGING = new Digging();
    private static final Bridging BRIDGING = new Bridging();
    private static final Blocking BLOCKING = new Blocking();
    private static final RabbotWait RABBOT_WAIT = new RabbotWait();
    private static final Walking WALKING = new Walking();

    private BehaviourFactory() {
        // Private constructor to prevent instantiation
    }

    public static List<Behaviour> createBehaviours() {
        List<Behaviour> behaviours = new ArrayList<>();

        behaviours.add(EXPLODING);
        behaviours.add(OUT_OF_BOUNDS);
        behaviours.add(BURNING);
        behaviours.add(DROWNING);
        behaviours.add(RABBOT_CRASH);
        behaviours.add(FALLING);
        behaviours.add(EXITING);
        behaviours.add(BROLLYCHUTING);
        behaviours.add(CLIMBING);
        behaviours.add(BASHING);
        behaviours.add(DIGGING);
        behaviours.add(BRIDGING);
        behaviours.add(BLOCKING);
        behaviours.add(RABBOT_WAIT);
        behaviours.add(WALKING);

        return Collections.unmodifiableList(behaviours); // Ensure immutability
    }
}