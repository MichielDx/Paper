import akka.actor.*;
import akka.japi.pf.DeciderBuilder;
import scala.concurrent.duration.Duration;

import java.util.ArrayList;
import java.util.List;

import static akka.actor.SupervisorStrategy.*;

public class Supervisor extends UntypedActor {
    private List<ActorRef> children;

    public Supervisor() {
        this.children = new ArrayList<>();
        final ActorRef child2 = getContext().actorOf(Builder.props(), "child2");
        final ActorRef child1 = getContext().actorOf(Worker.props(), "child");
        this.children.add(child1);
        this.children.add(child2);
    }

    public static final OneForOneStrategy STRATEGY = new OneForOneStrategy(
            10,
            Duration.create("10 seconds"),
            DeciderBuilder
                    .match(RuntimeException.class, ex -> restart())
                    .build()
    );


    /*{

        final ActorRef child2 = getContext().actorOf(Builder.props(), "child2");

        final ActorRef child1 = getContext().actorOf(Worker.props(), "child");

        receive(ReceiveBuilder
                .matchAny(any -> getContext().getChildren().forEach(child-> child.forward(any, getContext())))
                .build()
        );
    }*/

    @Override
    public void onReceive(Object message) throws Throwable {
        children.forEach(child -> child.tell(message,getSelf()));
    }

    @Override
    public SupervisorStrategy supervisorStrategy() {
        return STRATEGY;
    }

    public static Props props() {
        return Props.create(Supervisor.class);
    }
}
