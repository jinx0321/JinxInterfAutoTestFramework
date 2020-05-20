package AutoTest.Report;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
 
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;
 
public class ReportUtil {
    private static final NumberFormat DURATION_FORMAT = new DecimalFormat("#0.000");
    private static final NumberFormat PERCENTAGE_FORMAT = new DecimalFormat("#0.00%");
 
    public static String formatDate(long date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(date);
    }
 
    /**
     * ��������ʱ��
     * return ��,����3λС��
     */
    public String getTestDuration(ITestContext context) {
        long duration;
        duration = context.getEndDate().getTime() - context.getStartDate().getTime();
        return formatDuration(duration);
    }
 
    public static String formatDuration(long elapsed) {
        double seconds = (double) elapsed / 1000;
        return DURATION_FORMAT.format(seconds);
    }
 
    /**
     * ����ͨ����
     * return 2.22%,����2λС��
     */
    public String formatPercentage(int numerator, int denominator) {
        return PERCENTAGE_FORMAT.format(numerator / (double) denominator);
    }
 
    /**
     * ��ȡ�����������Զ��ŷָ�
     *
     * @param result
     * @return
     */
    public static String getParams(ITestResult result) {
        Object[] params = result.getParameters();
        List<String> list = new ArrayList<String>(params.length);
        for (Object o : params) {
            list.add(renderArgument(o));
        }
        return commaSeparate(list);
    }
 
    /**
     * ��ȡ�����ķ���
     *
     * @param result
     * @return
     */
    public String getDependMethods(ITestResult result) {
        String[] methods = result.getMethod().getMethodsDependedUpon();
        return commaSeparate(Arrays.asList(methods));
    }
 
    /**
     * ��ջ�켣���ݲ�ȷ����ô����������
     *
     * @param throwable
     * @return
     */
    public String getCause(Throwable throwable) {
        StackTraceElement[] stackTrace = throwable.getStackTrace(); //��ջ�켣
        List<String> list = new ArrayList<String>(stackTrace.length);
        for (Object o : stackTrace) {
            list.add(renderArgument(o));
        }
        return commaSeparate(list);
    }
 
    /**
     * ��ȡȫ����־�����Ϣ
     *
     * @return
     */
    public List<String> getAllOutput() {
        return Reporter.getOutput();
    }
 
    /**
     * ��testresult��ȡ��־�����Ϣ
     *
     * @param result
     * @return
     */
    public List<String> getTestOutput(ITestResult result) {
        return Reporter.getOutput(result);
    }
 
 
    /*��object ת��ΪString*/
    private static String renderArgument(Object argument) {
        if (argument == null) {
            return "null";
        } else if (argument instanceof String) {
            return "\"" + argument + "\"";
        } else if (argument instanceof Character) {
            return "\'" + argument + "\'";
        } else {
            return argument.toString();
        }
    }
 
    /*������ת��Ϊ�Զ��ŷָ����ַ���*/
    private static String commaSeparate(Collection<String> strings) {
        StringBuilder buffer = new StringBuilder();
        Iterator<String> iterator = strings.iterator();
        while (iterator.hasNext()) {
            String string = iterator.next();
            buffer.append(string);
            if (iterator.hasNext()) {
                buffer.append(", ");
            }
        }
        return buffer.toString();
    }
}

