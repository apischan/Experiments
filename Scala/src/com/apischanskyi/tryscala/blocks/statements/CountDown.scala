package com.apischanskyi.tryscala.blocks.statements

/**
 * Created by apischanskyi on 01.06.14.
 */
object CountDown {
  def main(args: Array[String]) {
//    countDown(10)
    countdownRecursive(10)
  }

  def countDown(n: Int) {
    for (number <- (0 to n).reverse) println(number)
  }

  def countdownRecursive(n: Int):Int = {
    println(n)
    if (n == 0) n
    else countdownRecursive(n-1)
  }
}
