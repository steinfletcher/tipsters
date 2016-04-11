package io.tipsters.domain

import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "competitions")
class Competition (
        var name: String = "",
        var priority: Int = 0,
        @ManyToOne @JoinColumn(name = "country_id") var country: Country) : BaseEntity() {
    constructor() : this("", 0, Country())
}
