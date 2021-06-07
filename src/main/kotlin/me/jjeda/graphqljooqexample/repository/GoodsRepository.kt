package me.jjeda.graphqljooqexample.repository

import me.jjeda.graphqljooqexample.domain.Goods
import org.jooq.DSLContext
import org.jooq.Record
import org.jooq.exception.NoDataFoundException
import org.jooq.generated.tables.Goods.GOODS
import org.springframework.stereotype.Repository

@Repository
class GoodsRepository(private val dslContext: DSLContext) {
  fun findById(id: Int): Goods {
    return dslContext.fetchOne(GOODS, GOODS.ID.eq(id))?.toEntity() ?: throw NoDataFoundException()
  }

  fun Record.toEntity(): Goods {
    return Goods(this[GOODS.ID], this[GOODS.NAME])
  }
}
