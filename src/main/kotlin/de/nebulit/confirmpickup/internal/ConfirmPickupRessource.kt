package de.nebulit.confirmpickup.internal

import de.nebulit.domain.commands.confirmpickup.ConfirmPickupCommand
import java.util.concurrent.CompletableFuture
import mu.KotlinLogging
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

data class ConfirmPickupPayload(
    var transportId: String,
    var consignee: String,
    var freightForwarder: String,
    var fromDate: String,
    var Goods: String,
    var start: String,
    var target: String
)

/*
Boardlink: https://miro.com/app/board/uXjVIE3QaOg=/?moveToWidget=3458764624630607698
*/
@RestController
class ConfirmPickupRessource(private var commandGateway: CommandGateway) {

  var logger = KotlinLogging.logger {}

  @CrossOrigin
  @PostMapping("/debug/confirmpickup")
  fun processDebugCommand(
      @RequestParam transportId: String,
      @RequestParam consignee: String,
      @RequestParam freightForwarder: String,
      @RequestParam fromDate: String,
      @RequestParam Goods: String,
      @RequestParam start: String,
      @RequestParam target: String
  ): CompletableFuture<Any> {
    return commandGateway.send(
        ConfirmPickupCommand(
            transportId, consignee, freightForwarder, fromDate, Goods, start, target))
  }

  @CrossOrigin
  @PostMapping("/confirmpickup/{id}")
  fun processCommand(
      @PathVariable("id") aggregateId: java.util.UUID,
      @RequestBody payload: ConfirmPickupPayload
  ): CompletableFuture<Any> {
    return commandGateway.send(
        ConfirmPickupCommand(
            transportId = payload.transportId,
            consignee = payload.consignee,
            freightForwarder = payload.freightForwarder,
            fromDate = payload.fromDate,
            Goods = payload.Goods,
            start = payload.start,
            target = payload.target))
  }
}
