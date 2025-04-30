package de.nebulit.domain

import de.nebulit.domain.commands.assignroutes.AssignRoutesCommand
import de.nebulit.domain.commands.confirmpickup.ConfirmPickupCommand
import de.nebulit.events.PickupConfirmedEvent
import de.nebulit.events.RouteAssignedEvent
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateCreationPolicy
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.modelling.command.CreationPolicy
import org.axonframework.spring.stereotype.Aggregate

@Aggregate
class TransportAggregate {

  @AggregateIdentifier var transportId: String? = null

  @CreationPolicy(AggregateCreationPolicy.CREATE_IF_MISSING)
  @CommandHandler
  fun handle(command: ConfirmPickupCommand) {

    AggregateLifecycle.apply(
        PickupConfirmedEvent(
            transportId = command.transportId,
            consignee = command.consignee,
            freightForwarder = command.freightForwarder,
            fromDate = command.fromDate,
            start = command.start,
            target = command.target,
            goods = ""))
  }

  @EventSourcingHandler
  fun on(event: PickupConfirmedEvent) {
    // handle event
    transportId = event.transportId
  }

  @CommandHandler
  fun handle(command: AssignRoutesCommand) {
    AggregateLifecycle.apply(RouteAssignedEvent(command.transportId, command.routes.get(0)))
  }

  @EventSourcingHandler
  fun on(event: RouteAssignedEvent) {
    // handle event
    transportId = event.transportId
  }
}
