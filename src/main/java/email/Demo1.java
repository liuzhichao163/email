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
		//��������
		Properties props = new Properties();
		props.setProperty("mail.smtp.auth", "true");//��֤
		//ʹ��smtpЭ��
		props.setProperty("mail.transport.protocol", "smtp");
		Session session = Session.getInstance(props);
		//��ӡһ��һ����Ϣ
		session.setDebug(true);
		//�����ʼ�����
		Message msg = new MimeMessage(session);
		msg.setText("�����");//�ʼ�����
		msg.setFrom(new InternetAddress("liuzhichao95@sina.com"));//�������Ʊ���ͷ���������һ��
		//�����ʼ�
		Transport transport = session.getTransport();
		transport.connect("smtp.sina.com", 25, "liuzhichao95", "lzc950318");//��������
		transport.sendMessage(msg, new Address[] {new InternetAddress("liu_ZC@163.com")});//���ʼ�
		//.send()��̬����һ���԰������ӷ������������ʼ����ر���Դ
		//transport.send(msg, new Address[] {new InternetAddress("liu_ZC@souhu.com")});
		transport.close();//�ر�
	}

}
