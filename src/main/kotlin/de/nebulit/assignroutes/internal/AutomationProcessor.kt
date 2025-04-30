package de.nebulit.assignroutes.internal

import de.nebulit.common.Processor
import de.nebulit.domain.commands.assignroutes.AssignRoutesCommand
import de.nebulit.events.PickupConfirmedEvent
import mu.KotlinLogging
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.eventhandling.EventHandler
import org.axonframework.queryhandling.QueryGateway
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/*
Boardlink: https://miro.com/app/board/uXjVIE3QaOg=/?moveToWidget=3458764624630607704
*/
@Component
class AutomationProcessor : Processor {
  var logger = KotlinLogging.logger {}

  @Autowired lateinit var commandGateway: CommandGateway
  @Autowired lateinit var queryGateway: QueryGateway
  @Autowired private lateinit var routesApi: RoutesApi

  @EventHandler
  fun on(event: PickupConfirmedEvent) {
    val routes = routesApi.findRoutesForPickup(event.transportId)
    // TODO default routes handling
    commandGateway.sendAndWait<Any>(AssignRoutesCommand(event.transportId, routes))
  }
}
