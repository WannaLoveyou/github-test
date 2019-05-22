package com.qian.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Lu Kongwen
 * @version Create time：2017-6-2 下午6:11:06
 * @Description
 */
public class UploadUtils {

	private static Logger log = Logger.getLogger(UploadUtils.class);

	public static final String BASE_UPLOAD_PATH = PathUtils.getProperty("upload.path");

	public static final String FAMILY_CHECK_ORDER_PHOTO_PATH = BASE_UPLOAD_PATH + "familyCheckOrderPhoto/";

	public static final String ORDER_PHOTO_PATH = BASE_UPLOAD_PATH + "order/";

	public static final String AIR_BOTTLE_IFNO_PATH = BASE_UPLOAD_PATH + "airBottleInfo/";

	public static List<String> uploadOrderPhotos(HttpServletRequest request) {

		return uploadPhotos(request, ORDER_PHOTO_PATH);
	}

	public static List<String> uploadAirBottleInfoPhotos(HttpServletRequest request) {

		return uploadPhotos(request, AIR_BOTTLE_IFNO_PATH);
	}

	public static List<String> uploadPhotos(HttpServletRequest request, String url) {

		List<String> photos = new ArrayList<String>();

		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

		log(multiRequest);// 记录日志，LogFilter拿不到参数

		Iterator<String> iter = multiRequest.getFileNames();

		int cnt = 0;

		while (iter.hasNext()) {

			String fileName = iter.next();

			List<MultipartFile> multipartFiles = multiRequest.getFiles(fileName);

			File file = new File(url);

			if (!file.exists()) {
				file.mkdir();
			}

			long timeFile = new Date().getTime();

			for (MultipartFile multipartFile : multipartFiles) {

				String saveFileName = multipartFile.getOriginalFilename();

				StringBuffer sb = new StringBuffer(url);

				StringBuffer saveName = new StringBuffer();
				saveName.append(timeFile).append("_").append(cnt++).append("_").append(saveFileName);

				sb.append(saveName);

				File newFile = new File(sb.toString());

				try {
					FileUtils.writeByteArrayToFile(newFile, multipartFile.getBytes());
				} catch (IOException e) {
					e.printStackTrace();
				}

				photos.add(saveName.toString());
			}

		}

		return photos;
	}

	public static void log(MultipartHttpServletRequest multiRequest) {

		ObjectMapper mapper = new ObjectMapper();

		String url = multiRequest.getRequestURI();

		String parameters = null;
		try {
			parameters = mapper.writeValueAsString(multiRequest.getParameterMap());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		StringBuffer sb = new StringBuffer();

		sb.append("url = {").append(url).append("}");
		sb.append(",").append("parameters = {").append(parameters).append("}");

		log.info(sb);

	}

	public static InputStream uploadFile(HttpServletRequest request) {

		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

		log(multiRequest);// 记录日志，LogFilter拿不到参数

		Iterator<String> iter = multiRequest.getFileNames();

		while (iter.hasNext()) {

			String fileName = iter.next();

			List<MultipartFile> multipartFiles = multiRequest.getFiles(fileName);

			for (MultipartFile multipartFile : multipartFiles) {
				try {
					return multipartFile.getInputStream();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		return null;
	}

	public static List<String> uploadFamilyCheckOrderPhotos(HttpServletRequest request) {

		List<String> photos = new ArrayList<String>();

		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

		log(multiRequest);// 记录日志，LogFilter拿不到参数

		Iterator<String> iter = multiRequest.getFileNames();

		int cnt = 0;

		while (iter.hasNext()) {

			String fileName = iter.next();

			List<MultipartFile> multipartFiles = multiRequest.getFiles(fileName);

			File file = new File(UploadUtils.FAMILY_CHECK_ORDER_PHOTO_PATH);

			if (!file.exists()) {
				file.mkdir();
			}

			long timeFile = new Date().getTime();

			for (MultipartFile multipartFile : multipartFiles) {

				String saveFileName = multipartFile.getOriginalFilename();

				StringBuffer sb = new StringBuffer(UploadUtils.FAMILY_CHECK_ORDER_PHOTO_PATH);

				StringBuffer saveName = new StringBuffer();
				saveName.append(timeFile).append("_").append(cnt++).append("_").append(saveFileName);

				sb.append(saveName);

				File newFile = new File(sb.toString());

				try {
					FileUtils.writeByteArrayToFile(newFile, multipartFile.getBytes());
				} catch (IOException e) {
					e.printStackTrace();
				}

				photos.add(saveName.toString());
			}

		}

		return photos;
	}
}
