package de.nebulit.pickuptoconfirmroutes.internal

import de.nebulit.pickuptoconfirmroutes.PickupToConfirmRoutesReadModel
import de.nebulit.pickuptoconfirmroutes.PickupToConfirmRoutesReadModelQuery
import java.util.concurrent.CompletableFuture
import mu.KotlinLogging
import org.axonframework.queryhandling.QueryGateway
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

/*
Boardlink: https://miro.com/app/board/uXjVIE3QaOg=/?moveToWidget=3458764624630607706
*/
@RestController
class PickuptoconfirmroutesRessource(private var queryGateway: QueryGateway) {

  var logger = KotlinLogging.logger {}

  @CrossOrigin
  @GetMapping("/pickuptoconfirmroutes/{id}")
  fun findReadModel(
      @PathVariable("id") transportId: String
  ): CompletableFuture<PickupToConfirmRoutesReadModel> {
    return queryGateway.query(
        PickupToConfirmRoutesReadModelQuery(transportId),
        PickupToConfirmRoutesReadModel::class.java)
  }
}
