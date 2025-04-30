package de.nebulit.assignroutes.internal

import de.nebulit.events.Route
import org.springframework.stereotype.Component

@Component
class RoutesApi {

  fun findRoutesForPickup(transportId: String): List<Route> {
    // very simplified view, no transport legs, nothing
    return listOf(Route("1", "munich", "glasgow"), Route("1", "munich", "eaglesham"))
  }
}
