package rabbitescape.engine;

import rabbitescape.engine.behaviours.*;

import java.util.ArrayList;
import java.util.List;

public class BehaviourFactory {

    public static List<Behaviour> createRabbitBehaviours() {
        List<Behaviour> behaviours = new ArrayList<>();

        behaviours.add(Exploding.getInstance());
        behaviours.add(OutOfBounds.getInstance());
        behaviours.add(Burning.getInstance());
        behaviours.add(Drowning.getInstance());
        behaviours.add(RabbotCrash.getInstance());
        behaviours.add(Falling.getInstance());
        behaviours.add(Exiting.getInstance());
        behaviours.add(Brollychuting.getInstance());
        behaviours.add(Climbing.getInstance());
        behaviours.add(Bashing.getInstance());
        behaviours.add(Digging.getInstance());
        behaviours.add(Bridging.getInstance());
        behaviours.add(Blocking.getInstance());
        behaviours.add(RabbotWait.getInstance());

        // add new Token
        behaviours.add(Pause.getInstance());
        behaviours.add(TempBlocking.getInstance());

        // last
        behaviours.add(Walking.getInstance());

        return behaviours;
    }


}