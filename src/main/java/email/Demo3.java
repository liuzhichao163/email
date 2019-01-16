package email;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

public class Demo3 {

	public static void main(String[] args) throws MessagingException, IOException {
		Properties props = new Properties();
		// 认证
		props.setProperty("mail.smtp.auth", "true");
		// 使用smtp协议
		props.setProperty("mail.transport.protocol", "smtp");
		// 连接新浪的服务器，端口号不填写用默认的
		props.setProperty("mail.host", "smtp.sina.com");
		// 传入用户名和密码
		Session session = Session.getInstance(props, new Authenticator() {// 策略模式
			// 此方法返回一个包含用户名和密码的对象
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("liuzhichao95@sina.com", "lzc950318");
			}
		});
		
		MimeMessage msg = new MimeMessage(session);
		//设置发件人
		msg.setFrom(new InternetAddress("\""+MimeUtility.encodeText("刘志超") +"\" <liuzhichao95@sina.com>"));//设置发件人
		msg.setSubject("邮件主题");//设置邮件主题
		//收件人
		msg.setRecipients(RecipientType.TO, InternetAddress.parse("liu_ZC@163.com,liu_zc@sohu.com"));
		//收件人收到邮件后，回复到此地址（不添加回复到发件人地址）
		msg.setReplyTo(new Address[] {new InternetAddress("liuzhichao@sina.com")});
		// 1搭建最外层的框架：一个复杂体包含一个htmlBody和两个附件
		// （框架）复杂体：multipart/mixed中mixed代表混合的
		MimeMultipart msgMultipart = new MimeMultipart("mixed");
		// 将复杂体设置到邮件内容中
		msg.setContent(msgMultipart);
		// 复杂体有三个MimeBodyPart组成，设置htmlBody和附件 进行组合
		MimeBodyPart content = new MimeBodyPart();// htmlBody
		MimeBodyPart attch1 = new MimeBodyPart();// 附件1
		MimeBodyPart attch2 = new MimeBodyPart();// 附件2
		// 将三个MimeBodyPart（htmlBody和附件）添加到在外层的框架中
		msgMultipart.addBodyPart(content);
		msgMultipart.addBodyPart(attch1);
		msgMultipart.addBodyPart(attch2);

		// 2附件:将从数据源中获得的数据添加到附件中
		DataSource ds1 = new FileDataSource("C:\\Users\\ASUS\\Desktop\\求职简历（刘志超） - 副本 (2).doc");
		DataHandler dh1 = new DataHandler(ds1);
		attch1.setDataHandler(dh1);
		// 设置附件文件名MimeUtility.encodeText()将中文转换为Base64字节码
		attch1.setFileName(MimeUtility.encodeText("求职简历.doc"));

		DataSource ds2 = new FileDataSource("C:\\Users\\ASUS\\Desktop\\求职简历（刘志超） - 副本 (2).doc");
		DataHandler dh2 = new DataHandler(ds2);
		attch2.setDataHandler(dh2);
		attch2.setFileName(MimeUtility.encodeText("vue.doc"));

		// 3正文，包含html和图片，又是一个复杂体（框架），所以再new一个MimeMultipart对象
		// relater：依赖关系，html代码和图片是依赖关系
		MimeMultipart bodyMultipart = new MimeMultipart("relater");
		// 将新的复杂体框架给htmlBody
		content.setContent(bodyMultipart);
		// 新的复杂体有两个MimeBodyPart组成，设置html和图片进行组合
		MimeBodyPart htmlPart = new MimeBodyPart();
		MimeBodyPart girfPart = new MimeBodyPart();
		// 将两个MimBodyPart（html和图片）添加到新的复杂提框体框架中
		bodyMultipart.addBodyPart(htmlPart);
		bodyMultipart.addBodyPart(girfPart);

		// 拼接图片
		DataSource girfDs = new FileDataSource("C:\\Users\\ASUS\\Desktop\\微信图片_20190115145952.jpg");
		DataHandler girfDh = new DataHandler(girfDs);
		girfPart.setDataHandler(girfDh);
		// 拼接HTML
		htmlPart.setContent("<span style='color:red'>helloWord</span>", "text/html;chartset=gbk");

		// 将上面的对象生成内容
		msg.saveChanges();

		Transport transport = session.getTransport();
		// 进行连接服务器、发送、关闭资源
		Transport.send(msg);
		/*
		 * OutputStream ips = new FileOutputStream("F:\\111"); msg.writeTo(ips);
		 * ips.close();
		 */
	}

}
