package de.nebulit.pickuptoconfirmroutes

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

data class PickupToConfirmRoutesReadModelQuery(val transportId: String)

/*
Boardlink: https://miro.com/app/board/uXjVIE3QaOg=/?moveToWidget=3458764624630607706
*/
@Entity
class PickupToConfirmRoutesReadModelEntity {
  @Id @Column(name = "transportId") var transportId: String? = null
}

data class PickupToConfirmRoutesReadModel(val data: PickupToConfirmRoutesReadModelEntity)
