package example.resolver

import graphql.kickstart.tools.GraphQLQueryResolver
import example.domain.Goods
import example.service.GoodsService
import org.springframework.stereotype.Component

@Component
class QueryResolver(private val goodsService: GoodsService): GraphQLQueryResolver {

  fun getGoods(id: Int): Goods {
    return goodsService.getGoods(id)
  }
}
