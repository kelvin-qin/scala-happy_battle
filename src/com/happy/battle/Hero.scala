package com.happy.battle

import scala.util.Random

/**
  * 主角【英雄】类
  * 当前比较简单，只定义了生命值
  * 还可以定义力量，速度，幸运等
  *
  */
class Hero(val name: String) {

  private var usedReAlive = false

  var lifeValue: Int = 300

  var magicValue = 0

  val speed = 10

  val agility = 0

  val strength = 0

  val intelligence = 0

  val heroType = "[]"

  val heroProfession = "[Coder]"

  var status = 0
  val buffStatus: Map[Buff, Int] = Map()
  val deBuffStatus: Map[Buff, Int] = Map()

  private val fist: Int = 25 + strength

  //可用武器库
  private var weapons = collection.mutable.ArrayBuffer[Weapon](Sticks,
    LongSticks, BigKnife, Brick, Java, Scala, Python, Spark, Code, Boom)

  //基础武器库，用于特殊武器的随机调用
  val BaseWeapons: List[Weapon] = List[Weapon](Sticks, LongSticks, Code)


  //当前血量
  def current(): Int = lifeValue

  override def toString: String = {
    "【" + this.name + "】"
  }

  //基础攻击
  def useFist(hero: Hero): Unit = {
    println("使用小拳拳❥(^_-)*** -" + fist)
    hero.lifeValue = hero.lifeValue - this.fist
  }

  //使用武器
  def useWeapon(what: Weapon, hero: Hero): Unit = {
    println("拿出[武器]：" + what)
    what.effect(this, hero)
    hero.lifeValue = hero.lifeValue - what.damage
  }

  //使用法术
  def useMagic(what: Magic, hero: Hero): Unit = {
    val canUse = Random.nextDouble()
    if (hero.magicValue <= what.maxUse) {
      println("使用[法术]：" + what)
      if (canUse < what.maybe) {
        this.lifeValue = this.lifeValue + what.cure
        hero.lifeValue = hero.lifeValue - what.damage
        what.effect(this, hero)
      } else {
        println("忘记咒语，施法失败~o(╯□╰)！")
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
    if (!hero.usedReAlive) {
      hero.usedReAlive = LifeValueTo1.reAlive(hero)
    }
    println("对手的血量还有" + hero.current())
    if (this.current() > 0 && hero.current() > 0) true else false
  }
}

object Hero {
  def apply(name: String, life: Int = 300): Hero = {
    val hero = new Hero(name)
    if (life > 100) {
      hero.lifeValue = life
    } else {
      hero.lifeValue = 300
    }
    hero
  }
}


