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

//�Լ������ʼ�
public class Demo2 {

	public static void main(String[] args) throws AddressException, MessagingException {
		//��������
		Properties props = new Properties();
		//��֤
		props.setProperty("mail.smtp.auth", "true");
		//ʹ��smtpЭ��
		props.setProperty("mail.transport.protocol", "smtp");
		//�������˵ķ��������˿ںŲ���д��Ĭ�ϵ�
		props.setProperty("mail.host", "smtp.sina.com");
		//�����û���������
		Session session = Session.getInstance(props, new Authenticator() {//����ģʽ
			//�˷�������һ�������û���������Ķ���
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("liuzhichao95@sina.com", "lzc950318");
			}
		});
		//�����ʼ�����
		Message msg = new MimeMessage(session);
		//�ʼ���ʾ������
		msg.setFrom(new InternetAddress("liuzhichao95@sina.com"));
		msg.setSubject("��������");//����
		//�ռ���
		msg.setRecipients(RecipientType.TO, InternetAddress.parse("liu_ZC@163.com,liu_zc@sohu.com"));
		//�����ʼ����ģ�����һ������ �� �����������ݵ�����
		msg.setContent("<span style='color:red'>��ϲ���н���������</span>", "text/html;charset=gbk");
		Transport transport = session.getTransport();
		//�������ӷ����������͡��ر���Դ
		Transport.send(msg);
	}

}
