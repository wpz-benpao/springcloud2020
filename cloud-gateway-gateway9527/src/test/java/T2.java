import java.time.ZonedDateTime;

public class T2 {
    public static void main(String[] args) {
        ZonedDateTime zdt=ZonedDateTime.now();//默认时区
        System.out.println(zdt);//2020-11-28T16:56:40+08:00[Asia/Shanghai]
    }
}
