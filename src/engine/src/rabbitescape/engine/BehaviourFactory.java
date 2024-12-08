package rabbitescape.engine;

import rabbitescape.engine.behaviours.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BehaviourFactory {
    private int fatalHeight;
    private Falling falling;

    public BehaviourFactory(int fatalHeight) {
        this.fatalHeight = fatalHeight;
    }

    public List<Behaviour> createBehaviours() {
        List<Behaviour> behaviours = new ArrayList<>();

        Climbing climbing = new Climbing();
        Digging digging = new Digging();
        Exploding exploding = new Exploding();
        Burning burning = new Burning();
        OutOfBounds outOfBounds = new OutOfBounds();
        Drowning drowning = new Drowning();
        Exiting exiting = new Exiting();
        Brollychuting brollychuting = new Brollychuting(climbing, digging);
        falling = new Falling(climbing, brollychuting, fatalHeight);
        Bashing bashing = new Bashing();
        Bridging bridging = new Bridging();
        Blocking blocking = new Blocking();
        Walking walking = new Walking();
        RabbotCrash rabbotCrash = new RabbotCrash();
        RabbotWait rabbotWait = new RabbotWait();
        Pause pause = new Pause();
        TempBlocking tempBlocking = new TempBlocking();

        // 추가할 Behaviour의 순서에 맞게 리스트에 추가
        behaviours.add(exploding);
        behaviours.add(outOfBounds);
        behaviours.add(burning);
        behaviours.add(drowning);
        behaviours.add(rabbotCrash);
        behaviours.add(falling);
        behaviours.add(exiting);
        behaviours.add(brollychuting);
        behaviours.add(climbing);
        behaviours.add(bashing);
        behaviours.add(digging);
        behaviours.add(bridging);
        behaviours.add(blocking);
        behaviours.add(rabbotWait);

        // add new tokens
        behaviours.add(pause);
        behaviours.add(tempBlocking);

        // last
        behaviours.add(walking);

        return behaviours;
    }

    public Falling getFalling() {
        return falling;
    }
}