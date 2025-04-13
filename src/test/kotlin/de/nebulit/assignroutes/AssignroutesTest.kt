package de.nebulit.assignroutes

import de.nebulit.common.Event
import de.nebulit.common.support.RandomData
import de.nebulit.domain.TransportAggregate
import de.nebulit.domain.commands.assignroutes.AssignRoutesCommand
import de.nebulit.events.PickupConfirmedEvent
import de.nebulit.events.Route
import de.nebulit.events.RouteAssignedEvent
import org.axonframework.test.aggregate.AggregateTestFixture
import org.axonframework.test.aggregate.FixtureConfiguration
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


/**


Boardlink: https://miro.com/app/board/uXjVIE3QaOg=/?moveToWidget=3458764624633372560
 */
class AssignroutesTest {

    private lateinit var fixture: FixtureConfiguration<TransportAggregate>

    @BeforeEach
    fun setUp() {
        fixture = AggregateTestFixture(TransportAggregate::class.java)
    }

    @Test
    fun `Assignroutes Test`() {

        var transportId: String = RandomData.newInstance<String> {}

        //GIVEN
        val events = mutableListOf<Event>()

        events.add(RandomData.newInstance<PickupConfirmedEvent> {
            this.transportId = transportId
            consignee = RandomData.newInstance { }
            freightForwarder = RandomData.newInstance { }
            fromDate = RandomData.newInstance { }
            goods = RandomData.newInstance { }
            start = RandomData.newInstance { }
            target = RandomData.newInstance { }
        })


        val routes = listOf<Route>(RandomData.newInstance {  })
        //WHEN
        val command = AssignRoutesCommand(
            routes = routes,
            transportId = transportId
        )

        //THEN
        val expectedEvents = mutableListOf<Event>()

        expectedEvents.add(RandomData.newInstance<RouteAssignedEvent> {
            this.transportId = command.transportId
            this.route = routes[0]
//this.Route = ...
        })


        fixture.given(events)
            .`when`(command)
            .expectSuccessfulHandlerExecution()
            .expectEvents(*expectedEvents.toTypedArray())
    }

}
