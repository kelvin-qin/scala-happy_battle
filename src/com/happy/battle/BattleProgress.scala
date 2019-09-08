package com.scala.big.happy.battle

import java.text.DecimalFormat

class BattleProgress {
  /**
    * 进度条长度
    */
  private var barLen = 0

  /**
    * 用于进度条显示的字符
    */
  private var showChar: String = "#"

  private val formater = new DecimalFormat("#.##%")

  /**
    * 显示进度条
    */
  def show(value: Int): Unit = {
    if (value < 0 || value > 100) return
    reset()
    // 比例
    val rate = (value * 1.0 / 100).toFloat
    // 比例*进度条总长度=当前长度
    draw(barLen, rate)
    if (value == 100L) afterComplete()
  }

  /**
    * 画指定长度个showChar
    */
  private def draw(barLen: Int, rate: Float): Unit = {
    val len = (rate * barLen).toInt
    System.out.print("中场休息: ")
    var num = 0
    while (num < len) {
      System.out.print("=")
      num += 1
    }
    var num2 = 0
    while (num2 < (barLen - len)) {
      System.out.print(" ")
      num2 += 1
    }
    System.out.print(" |" + format(rate))
  }


  /**
    * 光标移动到行首
    */
  private def reset(): Unit = {
    System.out.print('\r')
  }

  /**
    * 完成后换行
    */
  private def afterComplete(): Unit = {
    System.out.print('\n')
  }

  private def format(num: Float) = formater.format(num)
}

object BattleProgress {

  def apply(barLen: Int, showChar: String): BattleProgress = {
    val bp = new BattleProgress()
    bp.barLen = barLen
    bp.showChar = "="
    bp
  }


  def printShow = {
    for (i <- 1 to 100) {
      BattleProgress(50, "=").show(i)
      Thread.sleep(50)
    }
  }

}
