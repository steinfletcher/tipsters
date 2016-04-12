package io.tipsters.domain

import javax.persistence.*

@Entity
@Table(name = "countries")
class Country(
        var name: String = "",
        var rank: Int = 0,
        @OneToMany(fetch = FetchType.LAZY, cascade = arrayOf(CascadeType.ALL), mappedBy = "country")
            var competitions: List<Competition> = arrayListOf()
) : BaseEntity() {
}
