package com.chen.hystrix.demo;

import com.netflix.hystrix.*;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Future;

/**
 * 调用合并
 * 命令调用合并允许多个请求合并到一个线程/信号下批量执行。
 *
 * @author chenshenghong
 * @version 1.0
 * @created 2017/5/21
 * @time 下午10:57
 */
public class CollapserCommand extends HystrixCollapser<List<String>, String, Integer> {
    private final Integer key;

    public CollapserCommand(Integer key) {
        this.key = key;
    }

    @Override
    public Integer getRequestArgument() {
        return key;
    }

    @Override
    protected HystrixCommand<List<String>> createCommand(Collection<CollapsedRequest<String, Integer>> requests) {
        //创建返回command对象  
        return new BatchCommand(requests);
    }

    @Override
    protected void mapResponseToRequests(List<String> batchResponse,
                                         Collection<CollapsedRequest<String, Integer>> collapsedRequests) {
        int count = 1;

        for (CollapsedRequest<String, Integer> request : collapsedRequests) {
            //手动匹配请求和响应
            request.setResponse(batchResponse.get(count++));
        }
    }

    private static final class BatchCommand extends HystrixCommand<List<String>> {
        private final Collection<CollapsedRequest<String, Integer>> requests;

        public BatchCommand(Collection<CollapsedRequest<String, Integer>> requests) {
            super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"))
                    .andCommandKey(HystrixCommandKey.Factory.asKey("GetValueForKey")));
            this.requests = requests;
        }

        @Override
        protected List<String> run() throws Exception {
            ArrayList<String> response = new ArrayList<String>();
            for (CollapsedRequest<String, Integer> request : requests) {
                response.add("ValueForKey: " + request.getArgument());
            }
            return response;
        }
    }

    public static void main(String[] args) throws Exception {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        try {
            Future<String> f1 = new CollapserCommand(1).queue();
            Future<String> f2 = new CollapserCommand(2).queue();
            Future<String> f3 = new CollapserCommand(3).queue();
            Future<String> f4 = new CollapserCommand(4).queue();
            System.out.printf("f1 result = " + f1.get());
            System.out.printf("f2 result = " + f2.get());
            System.out.printf("f3 result = " + f3.get());
            System.out.printf("f4 result = " + f4.get());
            System.out.println(HystrixRequestLog.getCurrentRequest().getExecutedCommands().size());

            HystrixCommand<?> command = HystrixRequestLog.getCurrentRequest().getExecutedCommands().toArray(new HystrixCommand<?>[1])[0];
            System.out.println(command.getCommandKey().name());
        } finally {
            context.shutdown();
        }
    }
}
