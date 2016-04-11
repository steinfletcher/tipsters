package io.tipsters.domain

import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Type
import java.io.Serializable
import java.util.*
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class BaseEntity : Serializable {
    @Id
    @GenericGenerator(name = "uuid-gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid-gen")
    @Type(type = "pg-uuid")
    var id: UUID = UUID.randomUUID()
        private set

    fun initNew() {
        this.id = UUID.randomUUID()
    }
}
