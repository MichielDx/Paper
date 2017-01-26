import akka.actor.*;
import akka.japi.pf.DeciderBuilder;
import akka.japi.pf.ReceiveBuilder;
import scala.concurrent.duration.Duration;

import static akka.actor.SupervisorStrategy.*;

public class Supervisor extends AbstractLoggingActor {

    public static final OneForOneStrategy STRATEGY = new OneForOneStrategy(
            10,
            Duration.create("10 seconds"),
            DeciderBuilder
                    .match(RuntimeException.class, ex -> restart())
                    .build()
    );


    {

        final ActorRef child2 = getContext().actorOf(Builder.props(), "child2");

        final ActorRef child1 = getContext().actorOf(Worker.props(), "child");

        receive(ReceiveBuilder
                .matchAny(any -> getContext().getChildren().forEach(child-> child.forward(any, getContext())))
                .build()
        );
    }

    @Override
    public SupervisorStrategy supervisorStrategy() {
        return STRATEGY;
    }

    public static Props props() {
        return Props.create(Supervisor.class);
    }
}
