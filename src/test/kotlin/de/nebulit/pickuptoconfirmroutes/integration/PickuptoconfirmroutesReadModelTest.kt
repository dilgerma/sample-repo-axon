package de.nebulit.pickuptoconfirmroutes.integration

import de.nebulit.common.support.BaseIntegrationTest
import de.nebulit.common.support.ProjectionFixtureConfiguration
import de.nebulit.common.support.RandomData
import de.nebulit.common.support.awaitUntilAssserted
import de.nebulit.domain.TransportAggregate
import de.nebulit.events.PickupConfirmedEvent
import de.nebulit.pickuptoconfirmroutes.PickupToConfirmRoutesReadModel
import de.nebulit.pickuptoconfirmroutes.PickupToConfirmRoutesReadModelQuery
import org.assertj.core.api.Assertions.assertThat
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.modelling.command.Repository
import org.axonframework.queryhandling.QueryGateway
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

/**
 * Instead of relying on the BaseIntegration Test which relies on @SpringBoot Test,
 * you could use the @ApplicationModule Test Annotation, which focuses on one module ( slice ) only.
 */
class PickuptoconfirmroutesReadModelTest : BaseIntegrationTest() {

  @Autowired private lateinit var commandGateway: CommandGateway

  @Autowired private lateinit var queryGateway: QueryGateway

  @Autowired private lateinit var repository: Repository<TransportAggregate>

  @Test
  fun `Pickuptoconfirmroutes Read Model Test`() {

    val transportId = RandomData.newInstance<String> {}

    // Prepare the fixture to provide an Aggregate
    val fixture =
        ProjectionFixtureConfiguration.aggregateInstance {
          repository.newInstance { TransportAggregate() }
        }
    // prepare the givens ( need to be in the correct order for the aggregate )
    fixture.given(RandomData.newInstance<PickupConfirmedEvent> { this.transportId = transportId })
    // apply everything to the event store
    fixture.apply()

    /**
     * an alternative to this approach is using the Event Gateway.
     *
     * eventGateway.publish(RandomData.newInstance<PickupConfirmedEvent> { this.transportId =
     * transportId })
     *
     * Problem with this - it does not prepare the aggregate correctly, so you could put the system
     * into an invalid state that will never be available in production and write tests for it.
     */
    awaitUntilAssserted {

      // now you can assert on the Read Model
      var readModel =
          queryGateway.query(
              PickupToConfirmRoutesReadModelQuery(transportId),
              PickupToConfirmRoutesReadModel::class.java)
      assertThat(readModel.get()).isNotNull
    }
  }
}
