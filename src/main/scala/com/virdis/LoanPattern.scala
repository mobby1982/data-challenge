package com.virdis

import util.control.Exception._
/**
 * Created by sandeep on 10/31/15.
 */
trait LoanPattern {

  type Closeable = { def close() }

  def using[R <: Closeable, A](resource: R)(f: R => A): A = {
    try {
      f(resource)
    } finally {
      ignoring(classOf[Throwable]) apply {
        resource.close()
      }
    }
  }
}

object loanPattern extends LoanPattern
