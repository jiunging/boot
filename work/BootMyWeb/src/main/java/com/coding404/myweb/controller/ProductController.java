package com.coding404.myweb.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.coding404.myweb.command.ProductUploadVO;
import com.coding404.myweb.command.ProductVO;
import com.coding404.myweb.product.service.ProductService;
import com.coding404.myweb.util.Criteria;
import com.coding404.myweb.util.PageVO;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	@Qualifier("productService")
	private ProductService productService;
	
	@GetMapping("/productList")
	public String list(Model model, Criteria cri, HttpSession session) { // 매개변수가 없으면 기본값, 있으면 생성자를 통해서 매개변수 전달
		
		String user_id = (String)session.getAttribute("user_id");
		
		
		
		// 목록을 가지고 나와서 데이터를 담고 나감
		ArrayList<ProductVO> list = productService.getList(cri, user_id);
		int total = productService.getTotal(cri, user_id);
		PageVO pageVO = new PageVO(cri, total); // 페이지네이션
		
		model.addAttribute("list", list);
		model.addAttribute("pageVO", pageVO);
		System.out.println(pageVO.toString());
		
		return "product/productList";
	}
	
	@GetMapping("/productReg")
	public String reg() {
		return "product/productReg";
	}
	
	@GetMapping("/productDetail")
	public String detail(@RequestParam("prod_id") int prod_id,
						Model model) {
		
		ProductVO vo = productService.getDetail(prod_id); // 상품 내용
		ArrayList<ProductUploadVO> imgs = productService.getImgs(prod_id); // 이미지들
		
		model.addAttribute("vo", vo);
		model.addAttribute("imgs", imgs);
		System.out.println("-f-sdfasfdsfdsaf-das-fda-sfdasf-dsa");
		System.out.println(imgs);
		// 화면에 데이터 출력
		
		
		return "product/productDetail";
	}
	
	// 상품등록요청
	@PostMapping("/productForm")
	public String productForm(ProductVO vo, RedirectAttributes ra, // 상품정보는 여기,
							  @RequestParam("file") List<MultipartFile> list ) { // 파일정보는 여기서 받음
		
		// 1. 공백인 이미지는 제거
		list = list.stream().filter(a -> a.isEmpty() == false).collect(Collectors.toList());
		// 2. 이미지 타입인지 검사
		for(MultipartFile file : list) {
			
			if( file.getContentType().contains("image") == false) {
				ra.addFlashAttribute("msg", "이미지는 필수로 선택하세요. png, jpg, jpeg 사이즈 500 x 500만 가능합니다");
				return "redirect:/product/productList";
			}
			
		}
		// 3. 이미지를 올린 경우는 서비스로 위임
		int result = productService.regist(vo, list);
			
		if(result == 1) { // 성공
			ra.addFlashAttribute("msg", "정상적으로 처리되었습니다");
		} else { // 실패
			ra.addFlashAttribute("msg", "등록에 실패했습니다. 관리자에게 문의하세요, 1566-9000");
		}
		
		// redirect는 컨트롤러를 태워서 가냐 안 가냐임, 있으면 태워서 감
		return "redirect:/product/productList";
	}
	
	// 업데이트요청
	@PostMapping("/updateForm")
	public String updateForm(ProductVO vo, RedirectAttributes ra) {

		// 1. 화면에서 넘어오는 값을 받는다.
		// 2. 서비스에 update메서드를 생성
		// 3. enddate, prod_name, price, count, discount, 설명, 내용 내용을 업데이트
		// 4. 업데이트 성공, 실패 여부 저장해서 목록화면으로 이동.
		int result = productService.update(vo);
		
		if(result == 1) { // 성공
			ra.addFlashAttribute("msg", "수정 성공");
		} else { // 실패
			ra.addFlashAttribute("msg", "수정 실패..");
		}
		return "redirect:/product/productList";
	}
	
	// 삭제요청
	@PostMapping("/deleteForm")
	public String deleteForm(@RequestParam("prod_id") int prod_id) {
		productService.delete(prod_id);
		
		return "redirect:/product/productList";
	}
	
	
	


}
