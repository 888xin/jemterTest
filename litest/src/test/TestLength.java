package test;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

/**
 * Created by Lifeix on 14-11-18.
 */
public class TestLength extends AbstractJavaSamplerClient{

    private SampleResult results ;
    private String testStr ;

    @Override
    public void setupTest(JavaSamplerContext context) {
        results = new SampleResult() ;
        testStr = context.getParameter("testString","") ;
        if (testStr != null && testStr.length() > 0) {
            results.setSamplerData(testStr);
        }
    }

    @Override
    public Arguments getDefaultParameters() {
        Arguments params = new Arguments();
        params.addArgument("testStr","");
        return params;
    }

    @Override
    public SampleResult runTest(JavaSamplerContext javaSamplerContext) {
        int len = 0;
        results.sampleStart();     //定义一个事务，表示这是事务的起始点，类似于LoadRunner的lr.start_transaction
        len = testStr.length();
        results.sampleEnd();     //定义一个事务，表示这是事务的结束点，类似于LoadRunner的lr.end_transaction
        if(len < 5){
            System.out.println(testStr);
            results.setSuccessful(false);   //用于设置运行结果的成功或失败，如果是"false"则表示结果失败，否则则表示成功
        }else
            results.setSuccessful(true);
        return results;
    }

    //结束方法，实际运行时每个线程仅执行一次，在测试方法运行结束后执行，类似于LoadRunner中的end方法
    public void teardownTest(JavaSamplerContext arg0) {
    }

}
