package com.apischanskyi.tryscala.structures

import scala.reflect.ClassTag

/**
 * Created by apischanskyi on 29.05.14.
 */
object Signum {
  def main(args: Array[String]) {
    println(signum(5))
    println(signum(-8))
    println(signum(0))
    println({}.isInstanceOf[Unit])
    println(f({}))
    for (i <- (0 to 10).reverse) println(i)
  }

  def signum(number: Int):Int = {
    if (number > 0) 1
    else if (number < 0) -1
    else 0
  }

  def f[T](v: T)(implicit ev: ClassTag[T]) = ev.toString
}
