package de.nebulit.domain.commands.confirmpickup

import de.nebulit.common.Command
import org.axonframework.modelling.command.TargetAggregateIdentifier

/*
Boardlink: https://miro.com/app/board/uXjVIE3QaOg=/?moveToWidget=3458764624630607698
*/
data class ConfirmPickupCommand(
    @TargetAggregateIdentifier var transportId: String,
    var consignee: String,
    var freightForwarder: String,
    var fromDate: String,
    var Goods: String,
    var start: String,
    var target: String
) : Command
