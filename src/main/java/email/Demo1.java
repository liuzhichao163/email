package email;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Demo1 {
	public static void main(String[] args) throws MessagingException {
		//创建环境
		Properties props = new Properties();
		props.setProperty("mail.smtp.auth", "true");//认证
		//使用smtp协议
		props.setProperty("mail.transport.protocol", "smtp");
		Session session = Session.getInstance(props);
		//打印一问一答信息
		session.setDebug(true);
		//创建邮件对象
		Message msg = new MimeMessage(session);
		msg.setText("你好吗？");//邮件内容
		msg.setFrom(new InternetAddress("liuzhichao95@sina.com"));//邮箱名称必须和发送者邮箱一致
		//发送邮件
		Transport transport = session.getTransport();
		transport.connect("smtp.sina.com", 25, "liuzhichao95", "lzc950318");//连服务器
		transport.sendMessage(msg, new Address[] {new InternetAddress("liu_ZC@163.com")});//发邮件
		//.send()静态方法一次性包括连接服务器、发送邮件、关闭资源
		//transport.send(msg, new Address[] {new InternetAddress("liu_ZC@souhu.com")});
		transport.close();//关闭
	}

}
