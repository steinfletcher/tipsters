package io.tipsters.domain

import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "regions")
class Region (
        var name: String = "",
        var rank: Int = 0
) : BaseEntity()