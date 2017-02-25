import com.baidu.microflow.generator.TaskFactory

val factory = new TaskFactory(job)

val t1 = factory.execRS("date")

job.addTask(t1)