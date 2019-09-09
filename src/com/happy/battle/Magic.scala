package com.happy.battle

import scala.util.Random

/**
  * 魔法基类
  * 定义人物的法术、技能
  */
class Magic {
  var damage = 0 //伤害
  var cure = 0 //治疗
  val maxUse = 1 //最大使用次数
  var used = 0 //已使用次数
  val maybe = 0.1 //触发概率
  val order = false //使用顺序：是否先于武器使用
  //魔法效果
  def effect(self: Hero, other: Hero): Unit = {
    //do something
  }
}

/**
  * @note 回天术
  * @todo 将使用者的生命值设置为1
  *
  */
object LifeValueTo1 extends Magic {
  override val maybe: Double = 0.9

  override def toString: String = {
    "【回天术】!!!"
  }

  def reAlive(hero: Hero): Unit = {
    this.used+=1
    val canUse = Random.nextDouble()
    if (this.used <= this.maxUse) {
      println("对手使用法术：" + this)
      if (canUse < this.maybe) {
        hero.health = 1
        println("可惜，对手差一丢丢就挂啦！")
      } else {
        println("o(╯□╰)忘记咒语，施法失败~！")
      }
    }

  }
}

/**
  * @note 挖东墙补西墙
  * @todo 对对方造成伤害，并将提升自身造成伤害值80%的生命
  *
  */
object StealLife extends Magic {
  override val maybe: Double = 0.8
  this.damage = 50

  override def toString: String = {
    "【吸星大法】!!!"
  }

  override def effect(self: Hero, other: Hero): Unit = {
    self.health = self.health + (this.damage * 0.8).toInt
    println("挖东墙补西墙，欧嘞！~ (ｷ｀ﾟДﾟ´)")
  }
}

