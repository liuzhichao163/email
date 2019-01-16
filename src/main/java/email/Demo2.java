package email;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

//自己构建邮件
public class Demo2 {

	public static void main(String[] args) throws AddressException, MessagingException {
		//创建环境
		Properties props = new Properties();
		//认证
		props.setProperty("mail.smtp.auth", "true");
		//使用smtp协议
		props.setProperty("mail.transport.protocol", "smtp");
		//连接新浪的服务器，端口号不填写用默认的
		props.setProperty("mail.host", "smtp.sina.com");
		//传入用户名和密码
		Session session = Session.getInstance(props, new Authenticator() {//策略模式
			//此方法返回一个包含用户名和密码的对象
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("liuzhichao95@sina.com", "lzc950318");
			}
		});
		//创建邮件对象
		Message msg = new MimeMessage(session);
		//邮件显示发件人
		msg.setFrom(new InternetAddress("liuzhichao95@sina.com"));
		msg.setSubject("中文主题");//主题
		//收件人
		msg.setRecipients(RecipientType.TO, InternetAddress.parse("liu_ZC@163.com,liu_zc@sohu.com"));
		//设置邮件正文，参数一：内容 ； 参数二：内容的类型
		msg.setContent("<span style='color:red'>恭喜你中奖啦！！！</span>", "text/html;charset=gbk");
		Transport transport = session.getTransport();
		//进行连接服务器、发送、关闭资源
		Transport.send(msg);
	}

}
