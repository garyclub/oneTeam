package kr.co.oraclejava.file;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.renderable.ParameterBlock;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.imageio.ImageIO;
import javax.media.jai.JAI;
import javax.media.jai.RenderedOp;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/file")
public class FileController {
	@Value("${upload.url}")
	private String uploadUrl;
	
	private static final Logger logger = LoggerFactory.getLogger(FileController.class);
	@RequestMapping(value = "/upload", method=RequestMethod.GET)
	public void upload() {}
	
	@RequestMapping(value = "/upload", method=RequestMethod.POST)
	public ModelAndView uploadAction(FileUploadCommand fuc) throws IllegalStateException, IOException{
		CommonsMultipartFile cmf = fuc.getUpFile();
		
		long fileSize = cmf.getSize();
		String originalName = cmf.getOriginalFilename();
		String ext = originalName.substring(originalName.length() - 3).toLowerCase();
		boolean isImage = false;
		
		File saveFile = new File(uploadUrl, originalName);
		
		try {
			cmf.transferTo(saveFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			createUploadDir();
			cmf.transferTo(saveFile);
			//디렉토리가 없을경우 생성.
		}
		if (ext.equals("gif") || ext.equals("jpg") || 
				ext.equals("png") || ext.equals("bmp")) {
			isImage = true;
			createThumbnailImage(originalName, ext);
		} //파일을 생성한 후에 해야 하기 때문에 try문 다음에 실행.
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("file/uploadAction");
		mav.addObject("fileSize", fileSize);
		mav.addObject("originalName", originalName);
		mav.addObject("isImage", isImage);
		return mav;
	}

	private void createThumbnailImage(String originalName, String ext) throws IOException {
		ParameterBlock pb = new ParameterBlock();
		pb.add(uploadUrl + "/" + originalName);
		RenderedOp rOp = JAI.create("fileload", pb);
		BufferedImage bi = rOp.getAsBufferedImage();
		BufferedImage thumb = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = thumb.createGraphics();
		g.drawImage(bi, 0, 0, 100, 100, null);
		
		File file = new File(uploadUrl + "/sm_" + originalName);
		ImageIO.write(thumb, ext, file);
		
	}

	private void createUploadDir() {
		logger.info("upload 디렉토리를 생성합니다.");
		File dir = new File(uploadUrl);
		if (!dir.exists()) dir.mkdirs();
	}
	
	@RequestMapping("/download")
	public void download(String fileName, HttpServletResponse response) throws IOException {
		File file = new File(uploadUrl, fileName);
		
		response.setContentType("application/octet-stream");
		response.setContentLength((int) file.length());
		response.setHeader(
				"Content-Disposition", "attachment; fileName=\"" + fileName  +
				URLEncoder.encode(fileName, "UTF-8") + "\"");
		
		InputStream is = null;
		OutputStream os = response.getOutputStream();
		
		is = new FileInputStream(file);
		FileCopyUtils.copy(is, os);
		
		if (is != null) try{is.close();}catch(Exception e){}
		
		if (os != null) try{
			os.flush(); //프로그램을 버퍼단위로 복사해서 내보내는데 버퍼를 다 채우지 못한 경우
						//채우지 못한 버퍼를 내보내기 위해서.
			os.close();}catch(Exception e){}
	}
}
