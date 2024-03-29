package com.happy.battle

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

/**
  * 武器基类
  * 定义武器类型
  */
class Weapon {
  var damage = 0

  //武器附加效果
  def effect(hero: Hero, hero2: Hero): Unit = {
    //do something
  }
}

object Code extends Weapon {
  this.damage = 99

  override def toString: String = {
    "抛出一行【代码】：急急如律令 ╭(╯^╰)╮ Run："
  }

  override def effect(hero: Hero, hero2: Hero): Unit = {
    this.damage = Random.nextInt(99)
    if (this.damage > 50) {
      println(s"✿✿ヽ(°▽°)ノ✿人品爆发，造成大量伤害：【${this.damage}】")
    } else {
      println(s"o(╥﹏╥)o代码出了问题，只造成一点伤害：【${this.damage}】")
    }
  }
}


object Sticks extends Weapon {
  this.damage = 30

  override def toString: String = {
    "【短棍】出击！" + this.damage
  }
}


object LongSticks extends Weapon {
  this.damage = 40

  override def toString: String = {
    "【长棍】更容易打到人！" + this.damage
  }
}


object BigKnife extends Weapon {
  this.damage = 0

  override def toString: String = {
    "武功再高，也怕【菜刀】！"
  }

  override def effect(hero: Hero, hero2: Hero): Unit = {
    this.damage = hero2.current() / 2
    println(s"~一刀半管血，问你怕不怕？##${this.damage}##")
  }
}


object Brick extends Weapon {
  this.damage = 30
  val num = 1

  override def toString: String = {
    "一【砖头】下去" + this.damage
  }

  override def effect(hero: Hero, hero2: Hero): Unit = {
    if (Random.nextDouble() > 0.5) {
      hero2.status = num
      println(s"~砸晕对手(✖人✖) ##${this.num}回合!##")
    }
  }

}


object Boom extends Weapon {
  val baseDamage = 100
  this.damage = 100

  override def toString: String = {
    "一颗【LC-100炸弹】!" + this.damage
  }

  override def effect(hero: Hero, hero2: Hero): Unit = {
    this.damage = baseDamage
    val bingo = Random.nextDouble()
    if (bingo >= 0.3 && bingo <= 0.7) {
      println("\"没有什么一发解决不了的问题!\"\n\"如果有，那就再来一发！\"（命中要害，伤害翻倍！）")
      this.damage = baseDamage * 2
    }
  }

}


object Java extends Weapon {
  this.damage = -50

  override def toString: String = {
    "扔出一本《【JAVA】编程思想》！\n不料对方[空手接白书]，阅读后血量增加了[50]！o(╯□╰)o"
  }
}


object Scala extends Weapon {
  this.damage = 10
  val cure = 90

  override def toString: String = {
    s"学完《快学【Scala】》，修改了自己的血量+${this.cure}！O(∩_∩)O哈哈~ \n对方精神损失：" + this.damage
  }

  override def effect(hero: Hero, hero2: Hero): Unit = {
    hero.lifeValue = hero.lifeValue + this.cure
    println(s"嘿嘿(*^▽^*)，发现修改血量的代码在第7行~~我悄悄把自己血量改成【${hero.current()}】")
  }
}


object Spark extends Weapon {
  val nums = 3

  override def toString: String = {
    "使用【SPARK】分布式系统，导弹三连发！"
  }

  override def effect(hero: Hero, hero2: Hero): Unit = {
    val skills = ArrayBuffer[Int]()
    for (i <- 1 to Spark.nums) {
      println(s"【Spark】分布式计算引擎的第${i}次攻击↓")
      val spark = Random.nextInt(hero.BaseWeapons.size)
      skills.+=(spark)
      hero.useWeapon(hero.BaseWeapons(spark), hero2)
    }
    if (skills.distinct.size == 1) {
      print("***三点一线，加送炸弹***Boom！！！\n")
      hero.useWeapon(Boom, hero2)
    }
  }
}


object Python extends Weapon {
  this.damage = 80
  val selfDamage: Int = this.damage / 2

  override def toString: String = {
    "人生苦短，我用【Python】~" + this.damage
  }

  override def effect(hero: Hero, hero2: Hero): Unit = {
    hero.lifeValue = hero.lifeValue - this.selfDamage
    if (hero.lifeValue <= 0) {
      hero.lifeValue = 1
    }
    println(s"[杀敌一千，自损八百！]~ \n自己还剩【${hero.current()}】血量！")
  }
}