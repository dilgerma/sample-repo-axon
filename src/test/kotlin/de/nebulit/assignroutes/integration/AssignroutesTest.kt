package de.nebulit.assignroutes.integration

import de.nebulit.common.support.BaseIntegrationTest
import de.nebulit.common.support.RandomData
import de.nebulit.common.support.awaitUntilAssserted
import de.nebulit.domain.commands.confirmpickup.ConfirmPickupCommand
import de.nebulit.events.RouteAssignedEvent
import org.axonframework.commandhandling.gateway.CommandGateway
import org.junit.jupiter.api.Test
import de.nebulit.common.support.StreamAssertions
import org.springframework.beans.factory.annotation.Autowired
import org.assertj.core.api.Assertions.assertThat
import java.util.*

/**


Boardlink: https://miro.com/app/board/uXjVIE3QaOg=/?moveToWidget=3458764624633372560
*/
class AssignroutesTest : BaseIntegrationTest() {

    @Autowired
    private lateinit var commandGateway: CommandGateway

    @Autowired
    private lateinit var streamAssertions: StreamAssertions

    @Test
    fun `Assignroutes Test`() {

        val transportId = RandomData.newInstance<String> {}
        
        
        
        var confirmPickupCommand = RandomData.newInstance<ConfirmPickupCommand>{
            this.transportId = transportId
        }
       
        commandGateway.sendAndWait<Any>(confirmPickupCommand)
        
        

        
          awaitUntilAssserted {
           streamAssertions.assertEvent(transportId.toString()) { it is RouteAssignedEvent}
           }
        

    }

}
