package com.KtorPractica.Utils

import com.KtorPractica.domain.Pagination
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.sql.SizedIterable

object PaginationRepository {

    fun <E : Entity<*>, T> paginateEntities(
        page: Int,
        pageSize: Int,
        entities: SizedIterable<E>,
        mapper: (E) -> T
    ): Pagination<T> {
        require(page > 0 && pageSize > 0)
        { "La página y el tamaño de página deben ser mayor que 0" }

        val total = entities.count()
        val totalPages = ((total + pageSize - 1) / pageSize).toInt()

        val items = entities
            .drop((page - 1) * pageSize) // saltar los registros de páginas anteriores
            .take(pageSize)              // tomar solo los de esta página
            .map(mapper)

        return Pagination(
            data = items,
            page = page,
            pageSize = pageSize,
            totalPages = totalPages,
            totalElements = total
        )
    }
}