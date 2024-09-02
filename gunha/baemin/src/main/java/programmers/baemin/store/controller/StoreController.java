package programmers.baemin.store.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import programmers.baemin.store.domain.Store;
import programmers.baemin.store.domain.StoreRegisterDto;
import programmers.baemin.store.service.StoreService;

@Slf4j
@Controller
@RequiredArgsConstructor
public class StoreController {

	private final StoreService storeService;

	@GetMapping("/store/list")
	public String storeList(Model model) {
		log.info("url : store/list");
		List<Store> storeList = storeService.getStoreList();
		model.addAttribute("storeList", storeList);
		return "/store/store-list";
	}

	@PostMapping("/store/register")
	public String register(@ModelAttribute StoreRegisterDto registerDto, RedirectAttributes redirectAttributes) {
		log.info("url : /store [register]");
		Store save = storeService.save(registerDto);
		redirectAttributes.addFlashAttribute("message", "가게 등록이 완료되었습니다.");
		redirectAttributes.addAttribute("itemId", save.getId());
		return "redirect:/store/{itemId}";
	}

	@GetMapping("/store/register")
	public String registerForm() {
		return "/store/store-register-form";
	}

	@GetMapping("/store/{storeId}")
	public String storeInfo(@PathVariable int storeId, Model model) {
		Store store = storeService.getStore(storeId);
		model.addAttribute("store", store);
		return "/store/store-info";
	}

	@GetMapping("/store/{itemId}/update")
	public String updateForm(@PathVariable int storeId, Model model) {
		Store store = storeService.getStore(storeId);
		model.addAttribute("store", store);
		return "store/store-update";
	}
}
