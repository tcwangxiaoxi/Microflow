package com.baidu.microflow

import com.baidu.microflow.generator.TaskFactory
import com.baidu.microflow.models.Job

/**
  * Created by wangxiaoxi03 on 2017/2/23.
  */
class TestFlow {

  def main(args: Array[String]) {

    val job = new Job("001", "")
    val factory = new TaskFactory(job)

    val t1 = factory.cmd("t1", "date > date.txt")
    val t2 = factory.cmd("t2", "ls > date.txt")
    val t3 = factory.cmd("date > date.txt")
    val t4 = factory.cmd("echo \"wangxiaoxi\" > wang.txt")
    val t5 = factory.cmd("t5", "echo \"t5\" >> wang.txt")




    t1.hql("SELECT * FROM tbl_one")
    t1.param("--sessionconf engine=wing location=PINGO")
    t2.hql("SELECT * FROM tbl_two")
    t2.param("--sessionconf engine=wing location=PINGO")
    t3.hql("SELECT * FROM tbl_three")
    t3.param("--sessionconf engine=wing location=PINGO")
    t4.bash("bash /Users/eddix/run.sh")
    t5.bash("date")
    t6.bash("ls")
    t7.bash("date")

    job.addTask(t1)
    job.addTask(t2)
    job.addTask(t3)
    job.addTask(t4, Set(t1, t2))
    job.addTask(t5, Set(t2, t3))
    job.addTask(t6, Set(t4, t5))
    job.addTask(t7, t6)


  }

}
