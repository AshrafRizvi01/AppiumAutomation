package com.wuu.android.test.util;

import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;

/**
 * Contains functions regarding the email verification process
 * 
 * @author Ashraf Iftekhar, Feb 3, 2017
 *
 */
public class ReadVerificationEmail {

	/**
	 * Returns email verification code
	 * 
	 * @return String
	 */
	static String Content = "";
	static String Code = "";

	public static String emailReader() {
		Properties props = new Properties();
		props.setProperty("mail.store.protocol", "imaps");
		try {
			Session session = Session.getInstance(props, null);
			Store store = session.getStore();
			store.connect("imap.gmail.com", "mutualmobile333@gmail.com", "bl@ckb0x");
			Folder inbox = store.getFolder("INBOX");
			inbox.open(Folder.READ_ONLY);
			Message msg = inbox.getMessage(inbox.getMessageCount());
			if (msg.getSubject() != null) {
				Multipart mp = (Multipart) msg.getContent();
				for (int i = 0; i < mp.getCount(); i++) {
					BodyPart bp = mp.getBodyPart(i);
					if (bp.getContent().toString().contains("code=")) {
						Content = bp.getContent().toString();
						String[] str = Content.split("code=", 2);
						String[] str2 = str[1].split('"' + ">", 2);
						Code = str2[0];
					}
				}
			} else {
				System.out.println("Email Not Found");
			}

		} catch (Exception mex) {
			mex.printStackTrace();
		}
		System.out.println("New Accessed Code = " + Code);
		return Code;

	}

}
