package me.jjeda.graphqljooqexample.service

import me.jjeda.graphqljooqexample.domain.Goods
import me.jjeda.graphqljooqexample.repository.GoodsRepository
import org.springframework.stereotype.Service

@Service
class GoodsService(private val goodsRepository: GoodsRepository) {

  fun getGoods(id: Int): Goods {
    return goodsRepository.findById(id)
  }
}
