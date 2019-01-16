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
		// ��֤
		props.setProperty("mail.smtp.auth", "true");
		// ʹ��smtpЭ��
		props.setProperty("mail.transport.protocol", "smtp");
		// �������˵ķ��������˿ںŲ���д��Ĭ�ϵ�
		props.setProperty("mail.host", "smtp.sina.com");
		// �����û���������
		Session session = Session.getInstance(props, new Authenticator() {// ����ģʽ
			// �˷�������һ�������û���������Ķ���
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("liuzhichao95@sina.com", "lzc950318");
			}
		});
		
		MimeMessage msg = new MimeMessage(session);
		//���÷�����
		msg.setFrom(new InternetAddress("\""+MimeUtility.encodeText("��־��") +"\" <liuzhichao95@sina.com>"));//���÷�����
		msg.setSubject("�ʼ�����");//�����ʼ�����
		//�ռ���
		msg.setRecipients(RecipientType.TO, InternetAddress.parse("liu_ZC@163.com,liu_zc@sohu.com"));
		//�ռ����յ��ʼ��󣬻ظ����˵�ַ������ӻظ��������˵�ַ��
		msg.setReplyTo(new Address[] {new InternetAddress("liuzhichao@sina.com")});
		// 1������Ŀ�ܣ�һ�����������һ��htmlBody����������
		// ����ܣ������壺multipart/mixed��mixed�����ϵ�
		MimeMultipart msgMultipart = new MimeMultipart("mixed");
		// �����������õ��ʼ�������
		msg.setContent(msgMultipart);
		// ������������MimeBodyPart��ɣ�����htmlBody�͸��� �������
		MimeBodyPart content = new MimeBodyPart();// htmlBody
		MimeBodyPart attch1 = new MimeBodyPart();// ����1
		MimeBodyPart attch2 = new MimeBodyPart();// ����2
		// ������MimeBodyPart��htmlBody�͸�������ӵ������Ŀ����
		msgMultipart.addBodyPart(content);
		msgMultipart.addBodyPart(attch1);
		msgMultipart.addBodyPart(attch2);

		// 2����:��������Դ�л�õ�������ӵ�������
		DataSource ds1 = new FileDataSource("C:\\Users\\ASUS\\Desktop\\��ְ��������־���� - ���� (2).doc");
		DataHandler dh1 = new DataHandler(ds1);
		attch1.setDataHandler(dh1);
		// ���ø����ļ���MimeUtility.encodeText()������ת��ΪBase64�ֽ���
		attch1.setFileName(MimeUtility.encodeText("��ְ����.doc"));

		DataSource ds2 = new FileDataSource("C:\\Users\\ASUS\\Desktop\\��ְ��������־���� - ���� (2).doc");
		DataHandler dh2 = new DataHandler(ds2);
		attch2.setDataHandler(dh2);
		attch2.setFileName(MimeUtility.encodeText("vue.doc"));

		// 3���ģ�����html��ͼƬ������һ�������壨��ܣ���������newһ��MimeMultipart����
		// relater��������ϵ��html�����ͼƬ��������ϵ
		MimeMultipart bodyMultipart = new MimeMultipart("relater");
		// ���µĸ������ܸ�htmlBody
		content.setContent(bodyMultipart);
		// �µĸ�����������MimeBodyPart��ɣ�����html��ͼƬ�������
		MimeBodyPart htmlPart = new MimeBodyPart();
		MimeBodyPart girfPart = new MimeBodyPart();
		// ������MimBodyPart��html��ͼƬ����ӵ��µĸ������������
		bodyMultipart.addBodyPart(htmlPart);
		bodyMultipart.addBodyPart(girfPart);

		// ƴ��ͼƬ
		DataSource girfDs = new FileDataSource("C:\\Users\\ASUS\\Desktop\\΢��ͼƬ_20190115145952.jpg");
		DataHandler girfDh = new DataHandler(girfDs);
		girfPart.setDataHandler(girfDh);
		// ƴ��HTML
		htmlPart.setContent("<span style='color:red'>helloWord</span>", "text/html;chartset=gbk");

		// ������Ķ�����������
		msg.saveChanges();

		Transport transport = session.getTransport();
		// �������ӷ����������͡��ر���Դ
		Transport.send(msg);
		/*
		 * OutputStream ips = new FileOutputStream("F:\\111"); msg.writeTo(ips);
		 * ips.close();
		 */
	}

}
