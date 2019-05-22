package com.qian.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.CountingOutputStream;

/**
 * @author Lu Kongwen
 * @version Create time：2016-6-17 上午9:22:15
 * @Description
 */
public class UserCard {

	public static void main(String args[]) throws IOException {

		String company = "001";

		int start = 1;
		int end = 30000;

		new UserCard().create(company, start, end);
	}

	public void create(String company, int start, int end) throws IOException {

		File cardNumberText = new File("D:/UserCard/CardNumber.txt");

		FileOutputStream cardNumberTextFos = new FileOutputStream(cardNumberText);

		CountingOutputStream cardNumberTextCountStream = new CountingOutputStream(cardNumberTextFos);

		String cSrc = "";

		try {

			for (int i = start; i <= end; i++) {

				cSrc = company + getCode(i);
				System.out.println("加密前的字串是：" + cSrc);

				cardNumberTextCountStream.write(cSrc.getBytes());
				cardNumberTextCountStream.write("\r\n".getBytes());

			}
		} finally {
			IOUtils.closeQuietly(cardNumberTextCountStream);
		}
	}

	public static String getCode(long intHao) {

		String STR_FORMAT = "0000000";

		DecimalFormat df = new DecimalFormat(STR_FORMAT);

		return df.format(intHao);

	}
}
