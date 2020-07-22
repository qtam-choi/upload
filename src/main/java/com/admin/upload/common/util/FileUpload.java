package com.admin.upload.common.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;


public class FileUpload
{

	@Value("${file.savedir}")
	private String saveDir;

	private String file_kind;
	private String file_name_org;
	private String file_name;
	private String file_ext;
	private long  file_size;
	private String file_path;
	private String result;
	private String msg;
	
	public FileUpload() {
		file_kind = "";
		file_name_org ="";
		file_name = "";
		file_ext = "";
		file_size = 0;
		file_path = "";
		result = "S";
		msg = "";
	}
	
	public FileUpload( MultipartFile uploadFile, String client, String uploadDir, String fileName ) {


		String saveDirPath = File.separator + client +File.separator +uploadDir + File.separator ;
		String fullDirPath = saveDir + saveDirPath;

		String originalFileName = "";

		if(!uploadFile.isEmpty()){

			originalFileName = uploadFile.getOriginalFilename();
			String onlyFileName = originalFileName.substring(0, originalFileName.lastIndexOf("."));
			String extension = originalFileName.substring(originalFileName.lastIndexOf(".")+1);
			
			if(extension != null){
				if(extension.toLowerCase().equals("exe") || extension.toLowerCase().equals("asp") || extension.toLowerCase().equals("jsp")
					|| extension.toCharArray().equals("htm") || extension.toLowerCase().equals("html")  || extension.toLowerCase().equals("do") ){
					
					this.result = "E";
					this.msg = "저장불가능한 확장자 입니다.";
					
				}else{
					
					try{
						
						//디렉토리 만들기
						File dir = new File(fullDirPath);
						
						if (!dir.isDirectory()) {
							dir.mkdirs();
						}
						
						//파일 생성  
						if(fileName == null){
							fileName = client + CommonUtil.crefileName();
						}
						
						File uploadedFile = new File(fullDirPath, fileName);
							
						byte[] bytes = uploadFile.getBytes();
						BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadedFile));
						stream.write(bytes);
						stream.close();
						
						
						file_kind = client;
						file_name_org = originalFileName;
						file_name = fileName;
						file_ext = extension;
						file_size = uploadFile.getSize();
						file_path = saveDirPath + fileName;
						result = "S";
						msg = "";
						
					}catch(Exception e){
						e.printStackTrace();
						this.result = "E";
						this.msg = "파일 업로드 중 오류가 발생하였습니다.";
					}

					this.result = "S";
				}
				

				
			}else{
				this.result = "E";
				this.msg ="파일 확장자가 없습니다.";
			}
		}else{
			this.result ="E";
			this.msg = "파일이 없습니다.";
		}
		
	}
	
	public String getFileKind(){
		return this.file_kind;
	}
	
	public String getFileNameOrigine(){
		return this.file_name_org;
	}
	
	public String getFileName(){
		return this.file_name;
	}
	
	public String getFileExt(){
		return this.file_ext;
	}
	
	public long getFileSize(){
		return this.file_size;
	}
	
	public String getFilePath(){
		return this.file_path;
	}
	
	public String getResult(){
		return this.result;
	}
	
	public String getMsg(){
		return this.msg;
	}
	
	public Map getFileInfo(){
		
		Map infoMap = new HashMap();
		infoMap.put("file_kind", file_kind);
		infoMap.put("file_name_org", file_name_org);
		infoMap.put("file_name", file_name);
		infoMap.put("file_ext", file_ext);
		infoMap.put("file_size", file_size);
		infoMap.put("file_path", file_path);
		infoMap.put("result",result);
		infoMap.put("msg",msg);
		
		return infoMap;
	}
	
}