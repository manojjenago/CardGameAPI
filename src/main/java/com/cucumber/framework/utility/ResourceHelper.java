package com.cucumber.framework.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;

/**
 * @author manoj.jena
 *
 *         11-Nov-2023
 *
 */
public class ResourceHelper {

	public static String getResourcePath(String resource) {
		String path = getBaseResourcePath() + resource;
		return path;
	}
	
	public static String getBaseResourcePath() {
		URL resourceUrl = ResourceHelper.class.getResource("/");
		System.out.println("resourceUrl::="+resourceUrl );
		String path = resourceUrl.getPath();
//		String path = ResourceHelper.class.getClass().getResource("/").getPath();
		System.out.println("path::="+path );
		return path;
	}
	
	public static InputStream getResourcePathInputStream(String resource) throws FileNotFoundException {
		return new FileInputStream(ResourceHelper.getResourcePath(resource));
	}
	
}
