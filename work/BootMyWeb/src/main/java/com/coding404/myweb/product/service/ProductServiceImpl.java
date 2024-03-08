package com.coding404.myweb.product.service;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.coding404.myweb.command.CategoryVO;
import com.coding404.myweb.command.ProductUploadVO;
import com.coding404.myweb.command.ProductVO;
import com.coding404.myweb.util.Criteria;

@Service("productService")
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductMapper productMapper;
	
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
	
	// 하나의 메서드 안에서 다중 insert or update를 처리하는 도중, 에러가 난다면? -> 롤백처리
	// 그게 transcational임
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int regist(ProductVO vo, List<MultipartFile> list) {
		// insert를 2군데 테이블에 처리
		// 파일 업로드 처리
		int result = productMapper.regist(vo);
		
		// 업로드 작업 처리
		for(MultipartFile file : list) {
			// 요 내용은 위랑 똑같음
			// 1. 파일명
			String filename = file.getOriginalFilename();
			filename = filename.substring(filename.lastIndexOf("\\") + 1);
			// 2. 파일사이즈
			long size = file.getSize();
			
			// 3. 동일한 파일로 업로드가 되면, 기존 파일이 없어지기 때문에, 랜덤한 이름을 이용해서 파일명칭 바꿈
			String uuid = UUID.randomUUID().toString();
			
			// 날짜별로 폴더 생성
			String filepath = makeFolder(); // yyyyMMdd
			
			// 3. 업로드할 경로
			String savePath = uploadPath + "/" + filepath  + "/" + uuid + "_" + filename;
			
			System.out.println("파일명: " + filename); // 원본파일명 DB저장
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
			// 업로드 이후에는 데이터베이스에 경로를 저장
			// prod_id는 서비스 영역에서 구할 수 있는 방법이 없음
			productMapper.registFile(ProductUploadVO.builder()
									 .filename(filename)
									 .filepath(filepath)
									 .uuid(uuid)
									 // .prod_id(null) // FK
									 .prod_writer(vo.getProd_writer() )
									 .build()
									 );
			
			
		}
		
		
		
		return result;
	}

	@Override
	public ArrayList<ProductVO> getList(Criteria cri) {
		
		return productMapper.getList(cri);
	}

	@Override
	public ProductVO getDetail(int prod_id) {
		
		return productMapper.getDetail(prod_id);
	}

	@Override
	public int update(ProductVO vo) {
		
		return productMapper.update(vo);
	}

	@Override
	public void delete(int prod_id) {
		productMapper.delete(prod_id);
	}

	@Override
	public int getTotal(Criteria cri) {
		
		return productMapper.getTotal(cri);
	}

	@Override
	public ArrayList<CategoryVO> getCategory() {
		
		return productMapper.getCategory();
	}

	@Override
	public ArrayList<CategoryVO> getCategoryChild(CategoryVO vo) {
		
		return productMapper.getCategoryChild(vo);
	}

	@Override
	public ArrayList<ProductUploadVO> getImgs(int prod_id) {
		
		return productMapper.getImgs(prod_id);
	}





}
