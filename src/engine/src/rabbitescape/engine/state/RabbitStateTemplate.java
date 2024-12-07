package rabbitescape.engine.state;

import rabbitescape.engine.Rabbit;
import rabbitescape.engine.ChangeDescription;

public abstract class RabbitStateTemplate {
    public boolean checkState(Rabbit rabbit) {
        if (rabbit == null || rabbit.state == null) {
            return false;
        }
        return doCheck(rabbit.state);
    }

    protected abstract boolean doCheck(ChangeDescription.State state);
}