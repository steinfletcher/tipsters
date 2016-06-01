package io.tipsters.domain

import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "competitions")
class Competition (
        var name: String = "",
        var rank: Int = 0,
        var active: Boolean = true,
        @ManyToOne @JoinColumn(name = "country_id") var country: Country) : BaseEntity() {
    constructor() : this("", 0, false, Country())
}
