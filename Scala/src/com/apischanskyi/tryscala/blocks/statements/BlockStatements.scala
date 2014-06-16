package com.apischanskyi.tryscala.blocks.statements

import math.sqrt

/**
 * Created by andrii.pischanskyi on 4/4/14.
 */
object BlockStatements {
  def main(args: Array[String]) {
    val x0 = 2
    val x = 4
    val y0 = 5
    val y = 7
    val distance = {val dx = x - x0; val dy = y - y0; sqrt(dx*dx + dy*dy)} // could be useful for initialization
    println(distance)
  }
}
