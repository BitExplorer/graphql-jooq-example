package example.service

import example.domain.Goods
import example.repository.GoodsRepository
import org.springframework.stereotype.Service

@Service
class GoodsService(private val goodsRepository: GoodsRepository) {

  fun getGoods(id: Int): Goods {
    return goodsRepository.findById(id)
  }
}
