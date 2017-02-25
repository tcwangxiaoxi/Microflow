namespace java com.baidu.microflow.generated.thrift

# 结构名加MF前缀，以区别JMS的Message类和程序内的Job类
# 变量名不加MF前缀

struct MFJob {
    1:required i64 jobId,
    2:required string jobName,
    3:required string jobScript,
}

struct MFMessage {
    1:required i64 messageId,
    2:optional MFJob job,
}

service MicroflowBotService {
    string ruok(),

    /**
     * Cancel a MFJob
     */
    bool cancelJob(1:i64 jobId)
}
