import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hzxiongxin on 2016/11/22.
 */
public class PattrenTest {

    private static Pattern pattern;

    private static String patternStr;

    public void initParttern(){
        pattern = Pattern.compile(patternStr);
    }

    @Test
    public void test(){
        //测试java正则
        String str = "123";
        String str1 = "xx123";
        String str2 = "xxxxxx0123456789";//最大长度
        String str3 = "xxxxxxx0123456789";//超过长度
        String str4 = "xx_123";//使用错误字符
        patternStr = "^[a-zA-Z][a-zA-Z0-9]{1,15}";

        initParttern();

        matchString(str);

        matchString(str1);

        matchString(str2);

        matchString(str3);

        matchString(str4);
    }

    public void matchString(String str){
        System.out.println("当前测试字符串："+str);
        Matcher matcher = pattern.matcher(str);
        viewMatcher(matcher);
    }

    public void viewMatcher(Matcher matcher){
        if(matcher.matches()){
            System.out.println("matched");
        }else{
            System.out.println("not match");
        }
    }

}
