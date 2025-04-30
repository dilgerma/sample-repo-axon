package de.nebulit.events

import de.nebulit.common.Event

/*
Boardlink: https://miro.com/app/board/uXjVIE3QaOg=/?moveToWidget=3458764624630607721
*/
data class PickupConfirmedEvent(
    var transportId: String,
    var consignee: String,
    var freightForwarder: String,
    var fromDate: String,
    var goods: String,
    var start: String,
    var target: String
) : Event
