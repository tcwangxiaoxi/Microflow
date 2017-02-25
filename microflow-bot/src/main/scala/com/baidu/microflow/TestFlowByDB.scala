package com.baidu.microflow

import com.baidu.microflow.generator.TaskFactory
import com.baidu.microflow.models.Job

/**
  * Created by wangxiaoxi03 on 2017/2/23.
  */
class TestFlowByDB {

  def main(args: Array[String]) {

    val job = new Job("", "")
    val factory = new TaskFactory(job)

    val t1 = factory.execRS("date")

    job.addTask(t1)

    Thread.currentThread().setContextClassLoader()

  }
}
