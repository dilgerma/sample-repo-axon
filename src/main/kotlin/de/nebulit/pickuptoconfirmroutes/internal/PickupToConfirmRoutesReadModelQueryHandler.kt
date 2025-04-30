package de.nebulit.pickuptoconfirmroutes.internal

import de.nebulit.pickuptoconfirmroutes.PickupToConfirmRoutesReadModel
import de.nebulit.pickuptoconfirmroutes.PickupToConfirmRoutesReadModelQuery
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

/*
Boardlink: https://miro.com/app/board/uXjVIE3QaOg=/?moveToWidget=3458764624630607706
*/
@Component
class PickupToConfirmRoutesReadModelQueryHandler(
    private val repository: PickupToConfirmRoutesReadModelRepository
) {

  @QueryHandler
  fun handleQuery(query: PickupToConfirmRoutesReadModelQuery): PickupToConfirmRoutesReadModel? {

    if (!repository.existsById(query.transportId)) {
      return null
    }
    return PickupToConfirmRoutesReadModel(repository.findById(query.transportId).get())
  }
}
