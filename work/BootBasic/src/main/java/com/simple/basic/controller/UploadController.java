package com.simple.basic.controller;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.simple.basic.command.UploadVO;

import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/fileupload")
public class UploadController {
	
	// 업로드 경로(application.properties에서 가져옴)
	@Value("${project.upload.path}")
	private String uploadPath;
	
	// 날짜 폴더 만드는 함수 - 윈도우 시스템 상 하나의 폴더에 파일이 65532개
	public String makeFolder() {
		String filepath = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		
		File file = new File(uploadPath + "/" + filepath);
		if(file.exists() == false) { // 해당 파일이 있으면 true
			file.mkdirs(); // make directories
		}
		
		return filepath;
	}
	
	// 업로드 화면
	@GetMapping("/upload")
	public String upload() {
		return "fileupload/upload";
	}
	
	// 업로드 요청
	@PostMapping("/upload_ok")
	public String upload_ok(@RequestParam("file") MultipartFile file) {
		
		// 1. 파일명
		String originName = file.getOriginalFilename();
		originName = originName.substring(originName.lastIndexOf("\\") + 1);
		// 2. 파일사이즈
		long size = file.getSize();
		
		// 3. 동일한 파일로 업로드가 되면, 기존 파일이 없어지기 때문에, 랜덤한 이름을 이용해서 파일명칭 바꿈
		String uuid = UUID.randomUUID().toString();
		
		// 날짜별로 폴더 생성
		String filepath = makeFolder(); // yyyyMMdd
		
		// 3. 업로드할 경로
		String savePath = uploadPath + "/" + filepath  + "/" + uuid + "_" + originName;
		
		System.out.println("파일명: " + originName); // 원본파일명 DB저장
		System.out.println("폴더명: " + filepath); // 폴더명 DB저장
		System.out.println("랜덤이름: " + uuid); // 랜덤한 이름 DB저장
		System.out.println("업로드할 경로: " + savePath);
//		System.out.println("파일 사이즈: " + size);

		
		
		try {
			File saveFile = new File(savePath);
			file.transferTo(saveFile); // 업로드
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "fileupload/upload_ok";
	}
	
	// multiple을 사용한 여러 파일 업로드 - 멀티파트객체가 여러개
	@PostMapping("/upload_ok2")
	public String upload_ok2(MultipartHttpServletRequest part) {
		
		List<MultipartFile> list = part.getFiles("file"); // input의 name값이 file임
		
		for(MultipartFile file : list) {
			// 요 내용은 위랑 똑같음
			// 1. 파일명
			String originName = file.getOriginalFilename();
			originName = originName.substring(originName.lastIndexOf("\\") + 1);
			// 2. 파일사이즈
			long size = file.getSize();
			
			// 3. 동일한 파일로 업로드가 되면, 기존 파일이 없어지기 때문에, 랜덤한 이름을 이용해서 파일명칭 바꿈
			String uuid = UUID.randomUUID().toString();
			
			// 날짜별로 폴더 생성
			String filepath = makeFolder(); // yyyyMMdd
			
			// 3. 업로드할 경로
			String savePath = uploadPath + "/" + filepath  + "/" + uuid + "_" + originName;
			
			System.out.println("파일명: " + originName); // 원본파일명 DB저장
			System.out.println("폴더명: " + filepath); // 폴더명 DB저장
			System.out.println("랜덤이름: " + uuid); // 랜덤한 이름 DB저장
			System.out.println("업로드할 경로: " + savePath);
//			System.out.println("파일 사이즈: " + size);

			try {
				File saveFile = new File(savePath);
				file.transferTo(saveFile); // 업로드
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		return "fileupload/upload_ok";
	}
	
	@PostMapping("/upload_ok3")
	public String upload_ok3(@RequestParam("file") List<MultipartFile> list) {
		
		// input들 중에 파일이 있는 경우만 담음( 파일을 안 넣은 경우, 필터처리)
		// MultipartFile이 비어있으면, 필터링
		list = list.stream().filter(m -> m.isEmpty() == false).collect(Collectors.toList());
		
		for(MultipartFile file : list) {
			// 요 내용은 위랑 똑같음
			// 1. 파일명
			String originName = file.getOriginalFilename();
			originName = originName.substring(originName.lastIndexOf("\\") + 1);
			// 2. 파일사이즈
			long size = file.getSize();
			
			// 3. 동일한 파일로 업로드가 되면, 기존 파일이 없어지기 때문에, 랜덤한 이름을 이용해서 파일명칭 바꿈
			String uuid = UUID.randomUUID().toString();
			
			// 날짜별로 폴더 생성
			String filepath = makeFolder(); // yyyyMMdd
			
			// 3. 업로드할 경로
			String savePath = uploadPath + "/" + filepath  + "/" + uuid + "_" + originName;
			
			System.out.println("파일명: " + originName); // 원본파일명 DB저장
			System.out.println("폴더명: " + filepath); // 폴더명 DB저장
			System.out.println("랜덤이름: " + uuid); // 랜덤한 이름 DB저장
			System.out.println("업로드할 경로: " + savePath);
//			System.out.println("파일 사이즈: " + size);

			try {
				File saveFile = new File(savePath);
				file.transferTo(saveFile); // 업로드
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return "fileupload/upload_ok";
	}
	
	@PostMapping("/upload_ok4")
	@ResponseBody // REST 방식 - 하지만, 넘어오는 데이터가 multipart form 형식이라, @RequestBody를 쓰지 않음
	public String upload_ok4(/* @RequestParam("writer") String writer,
							 @RequestParam("file") MultipartFile file*/ 
							  UploadVO vo) {
		
		MultipartFile file = vo.getFile(); // vo에 저장된 File객체
		
		// 밑은, 전 내용과 같음
		// 1. 파일명
		String originName = file.getOriginalFilename();
		originName = originName.substring(originName.lastIndexOf("\\") + 1);
		// 2. 파일사이즈
		long size = file.getSize();
		
		// 3. 동일한 파일로 업로드가 되면, 기존 파일이 없어지기 때문에, 랜덤한 이름을 이용해서 파일명칭 바꿈
		String uuid = UUID.randomUUID().toString();
		
		// 날짜별로 폴더 생성
		String filepath = makeFolder(); // yyyyMMdd
		
		// 3. 업로드할 경로
		String savePath = uploadPath + "/" + filepath  + "/" + uuid + "_" + originName;
		
		System.out.println("파일명: " + originName); // 원본파일명 DB저장
		System.out.println("폴더명: " + filepath); // 폴더명 DB저장
		System.out.println("랜덤이름: " + uuid); // 랜덤한 이름 DB저장
		System.out.println("업로드할 경로: " + savePath);
//		System.out.println("파일 사이즈: " + size);

		try {
			File saveFile = new File(savePath);
			file.transferTo(saveFile); // 업로드
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		
		
		return "success";
	}
	
	

	
	
	
	
	
	
	
}
