package com.happy.battle

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
  def effect(self: Hero, other: Hero) = {
    //do something
  }
}

/**
  * @note 回天术
  * @todo 将使用者的生命值设置为1
  *
  */
object LifeValue1 extends Magic {
  override val maybe: Double = 0.9

  override def toString: String = {"【回天术】(对方)!!!"}
  override def effect(self: Hero, other: Hero): Unit = {
    if (self.current() <= 0) self.health = 1
    println("（对方）差一丢丢就要挂了！~ (ｷ｀ﾟДﾟ´)")
  }
}

