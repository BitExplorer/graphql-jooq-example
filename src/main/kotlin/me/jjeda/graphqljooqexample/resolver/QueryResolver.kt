package me.jjeda.graphqljooqexample.resolver

import graphql.kickstart.tools.GraphQLQueryResolver
import me.jjeda.graphqljooqexample.domain.Goods
import me.jjeda.graphqljooqexample.service.GoodsService
import org.springframework.stereotype.Component

@Component
class QueryResolver(private val goodsService: GoodsService): GraphQLQueryResolver {

  fun getGoods(id: Int): Goods {
    return goodsService.getGoods(id)
  }
}
