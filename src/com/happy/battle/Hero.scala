package com.happy.battle

import java.util.logging.Logger

import com.scala.big.happy.battle.BattleProgress

import scala.util.Random

/**
  * 人物【英雄】类
  * 当前比较简单，只定义了生命值
  * 还可以定义力量，速度，幸运等
  *
  */
class Hero {
  private var name = ""

  var health: Int = 300 //初始生命值

  private val fist: Int = 25 //基础攻击，拳头伤害

  //可用武器库，作战时每种武器可使用1次
  private var weapons = collection.mutable.ArrayBuffer[Weapon](Sticks,
    LongSticks, BigKnife, Brick, Java, Scala, Python, Spark, Code, Boom)

  //基础武器库，用于特殊武器的随机调用
  val BaseWeapons: List[Weapon] = List[Weapon](Code, Sticks, LongSticks)

  //当前血量
  def current(): Int = health

  override def toString: String = {
    this.name
  }

  //基础攻击
  def useFist(hero: Hero): Unit = {
    println("使用小拳拳❥(^_-)*** -" + fist)
    hero.health = hero.health - this.fist
  }

  //使用武器
  def useWeapon(what: Weapon, hero: Hero): Unit = {
    println("拿出武器：" + what)
    what.effect(this, hero)
    hero.health = hero.health - what.damage
  }

  //使用法术
  def useMagic(what: Magic, hero: Hero): Unit = {
    val canUse = Random.nextDouble()
    what.used += 1
    if (what.used <= what.maxUse) {
      println("使用法术：" + what)
      if (canUse < what.maybe) {
        this.health = this.health + what.cure
        hero.health = hero.health - what.damage
        what.effect(this, hero)
      } else {
        println("o(╯□╰)忘记咒语，施法失败~！")
      }
    }

  }

  //一次主动攻击，当有人物血量<=0时，返回false
  def attack(hero: Hero): Boolean = {
    if (this.weapons.nonEmpty) {
      val num = Random.nextInt(weapons.size)
      this.useWeapon(weapons(num), hero)
      this.weapons = weapons.-(weapons(num))
    } else {
      this.useFist(hero)
    }
    if (hero.current() <= 0) {
      LifeValueTo1.reAlive(hero)
    }
    println("对手的血量还有" + hero.current())
    if (this.current() > 0 && hero.current() > 0) true else false
  }

  /**
    * 一场分出胜负的战斗
    *
    * @param hero 对手英雄
    */
  def battle(hero: Hero): Hero = {
    val logger = Logger.getLogger("BATTLE")
    var isAlive = true
    var i = 0
    var winner = this
    while (isAlive) {
      i += 1
      logger.info(s"###########第${i}回合############")
      println("左方出手：→_→ →_→ ")
      isAlive = this.attack(hero)
      println()
      if (isAlive) {
        println("右方出手：←_← ←_← ")
        isAlive = hero.attack(this)
        BattleProgress.printShow
        if (!isAlive) {
          winner = hero
          logger.info(s"右方${winner.name}胜利！")
        }
      } else {
        logger.info(s"左方${winner.name}胜利！")
      }
      Thread.sleep(1500)
    }
    winner
  }
}

object Hero {
  def apply(name: String): Hero = {
    val hero = new Hero()
    hero.name = name
    hero
  }
}


