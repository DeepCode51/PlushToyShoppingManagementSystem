package com.soft.admin.action;

import java.util.Date;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.soft.common.util.DateUtil;
import com.soft.common.util.FindProjectPath;
import com.soft.common.util.UploadFile;

@Controller
public class UploadAdminAction {
	public static String path = "prop/database.properties";  //保存数据库连接信息的属性文件的相对路径
	public static Properties props = new Properties();
	static{
		props = new Properties();
		try {
			props.load(UploadAdminAction.class.getClassLoader().getResourceAsStream(path));
		} catch (Exception e) {
			props = new Properties();
		}
	}
	
	/**
	 * @Title: UploadImg
	 * @Description: 上传文件
	 * @return String
	 */
	@RequestMapping(value="admin/UploadImg.action",method=RequestMethod.POST)
	public String UploadImg(@RequestParam("upload") MultipartFile file,String num,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		String returnPage = "uploadImg";
		try {
			//重命名该图片
			String old_name=file.getOriginalFilename();
			String file_name=DateUtil.dateToDateString(new Date(),"yyyyMMddHHmmssSSS")+old_name.substring(old_name.indexOf("."));
			//设置保存文件位置
			String savePath = props.getProperty("savePath");
			if ("1".equals(num)) {
				savePath = props.getProperty("savePath1");
				returnPage = returnPage+"1";
			}else if ("2".equals(num)) {
				savePath = props.getProperty("savePath2");
				returnPage = returnPage+"2";
			}else if ("3".equals(num)) {
				savePath = props.getProperty("savePath3");
				returnPage = returnPage+"3";
			}
			String saveFile=FindProjectPath.getRootPath(savePath+"\\"+file_name);
			//文件类型限制
			String allowedTypes = props.getProperty("allowedTypes");
			if ("1".equals(num)) {
				allowedTypes = props.getProperty("allowedTypes1");
			}else if ("2".equals(num)) {
				allowedTypes = props.getProperty("allowedTypes2");
			}else if ("3".equals(num)) {
				allowedTypes = props.getProperty("allowedTypes3");
			}
			//上传文件
			String errorString=UploadFile.upload(file, saveFile, file.getContentType(), file.getSize(), allowedTypes,Long.parseLong(props.getProperty("maximunSize")));
			//判断上传结果
			if(!"".equals(errorString))
			{
				System.out.println(errorString);
				model.addAttribute("tip", "no");
				model.addAttribute("errorString", errorString);
				return returnPage;
			}
			model.addAttribute("tip", "ok");
			model.addAttribute("filename",file_name);
			model.addAttribute("filenameGBK",old_name);
			model.addAttribute("filelength",Math.round(file.getSize()/1024.0));
			return returnPage;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.addAttribute("tip", "no");
			model.addAttribute("errorString", "后台服务器异常");
			return returnPage;
		}
	}
}
