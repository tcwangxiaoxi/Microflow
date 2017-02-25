import com.baidu.microflow.generator.TaskFactory

val factory = new TaskFactory(job)

val t1 = factory.cmd("t1", "date > date.txt")
val t2 = factory.cmd("t2", "ls > date.txt")
val t3 = factory.cmd("date > date.txt")
val t4 = factory.cmd("echo \"wangxiaoxi\" > wang.txt")
val t5 = factory.cmd("t5", "echo \"t5\" >> wang.txt")

job.addTask(t1)
job.addTask(t2)
job.addTask(t3, Set(t1, t2))
job.addTask(t4, t1)
job.addTask(t5, Set(t3, t4))