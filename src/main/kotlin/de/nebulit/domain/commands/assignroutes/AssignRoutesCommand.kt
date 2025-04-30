package de.nebulit.domain.commands.assignroutes

import de.nebulit.common.Command
import de.nebulit.events.Route
import kotlin.collections.List
import org.axonframework.modelling.command.TargetAggregateIdentifier

/*
Boardlink: https://miro.com/app/board/uXjVIE3QaOg=/?moveToWidget=3458764624630607708
*/
data class AssignRoutesCommand(
    @TargetAggregateIdentifier var transportId: String,
    var routes: List<Route>
) : Command
