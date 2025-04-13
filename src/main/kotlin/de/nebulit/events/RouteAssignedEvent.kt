package de.nebulit.events

import de.nebulit.common.Event

data class Route(val routeId:String, val from:String, val to: String)

/*
Boardlink: https://miro.com/app/board/uXjVIE3QaOg=/?moveToWidget=3458764624630607722
*/
data class RouteAssignedEvent(
    var transportId:String,
	var route:Route
) : Event
