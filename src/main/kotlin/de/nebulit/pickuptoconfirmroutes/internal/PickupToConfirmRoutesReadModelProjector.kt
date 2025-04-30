package de.nebulit.pickuptoconfirmroutes.internal

import de.nebulit.events.PickupConfirmedEvent
import de.nebulit.pickuptoconfirmroutes.PickupToConfirmRoutesReadModelEntity
import org.axonframework.eventhandling.EventHandler
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component

interface PickupToConfirmRoutesReadModelRepository :
    JpaRepository<PickupToConfirmRoutesReadModelEntity, String>

/*
Boardlink: https://miro.com/app/board/uXjVIE3QaOg=/?moveToWidget=3458764624630607706
*/
@Component
class PickupToConfirmRoutesReadModelProjector(
    var repository: PickupToConfirmRoutesReadModelRepository
) {

  @EventHandler
  fun on(event: PickupConfirmedEvent) {
    // throws exception if not available (adjust logic)
    val entity =
        this.repository.findById(event.transportId).orElse(PickupToConfirmRoutesReadModelEntity())
    entity.apply { transportId = event.transportId }.also { this.repository.save(it) }
  }
}
