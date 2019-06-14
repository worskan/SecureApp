/*
 * package com.pmh.secureapp;
 * 
 * import java.util.Collection; import java.util.List;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.security.core.annotation.AuthenticationPrincipal; import
 * org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; import
 * org.springframework.stereotype.Controller; import
 * org.springframework.ui.Model; import
 * org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.PostMapping; import
 * org.springframework.web.bind.annotation.RequestMapping;
 * 
 * import groovy.transform.ToString;
 * 
 * @Controller public class HomeController {
 * 
 * @Autowired private RegistService service;
 * 
 * @Autowired private PhoneService pservice;
 * 
 * @Autowired private BoardService bservice;
 * 
 * @Autowired AccountRepository Arepo;
 * 
 * @RequestMapping("/") public String home() { return "home"; }
 * 
 * @RequestMapping("userList") public void userList(Model model) { List<Account>
 * userList = service.userList(); model.addAttribute("userList", userList); }
 * 
 * @RequestMapping("/login") public String loginPage(AccountPrincipal Apcal) {
 * 
 * return "login"; }
 * 
 * @RequestMapping("/logout") public void logoutPage() { }
 * 
 * @RequestMapping("/logout-success") public void logoutSuccess(String username)
 * { }
 * 
 * @RequestMapping("/regist") public void registPage() { }
 * 
 * @GetMapping("showUser") public void showUser(String username, Model model) {
 * Account account = service.findByUsername(username);
 * model.addAttribute("username", account); System.out.println("유저 전화번호: " +
 * account.phone); }
 * 
 * @PostMapping("/registRst") public String registProcess(Account account,
 * String username, String password) { System.out.println("아이디: " + username);
 * System.out.println("비밀번호: " + password); if ((account =
 * service.findByUsername(account.getUsername())) != null) { return
 * "registFail"; } else { BCryptPasswordEncoder passwordEncoder = new
 * BCryptPasswordEncoder(); Account account1 = new Account();
 * account1.setPassword(passwordEncoder.encode(password));
 * account1.setUsername(username); service.regist(account1); return
 * "registSuccess"; } }
 * 
 * @RequestMapping("/accessPhone") public void accessPhone(String username,
 * Model model) { model.addAttribute("username", username);
 * 
 * }
 * 
 * @PostMapping("/addPhone") public String addPhone(String username, String
 * other_phone) { Phone phone = new Phone(); Account account =
 * service.findByUsername(username); phone.setOtherPhone(other_phone);
 * phone.setUsername(username); System.out.println(username);
 * System.out.println(other_phone); service.getPhones(phone);
 * 
 * return "addPhone"; }
 * 
 * @RequestMapping("/boardInputView") public void boardInputView() {
 * 
 * }
 * 
 * @PostMapping("/boardInput") public String boardInput(String title, String
 * content, @AuthenticationPrincipal AccountPrincipal APcal) {
 * System.out.println(title); System.out.println(content); String now_username =
 * APcal.getUsername();
 * 
 * Board board = new Board(); board.setContent(content); board.setTitle(title);
 * board.setUsername(now_username); bservice.inputContent(board); return
 * "redirect:boardList"; }
 * 
 * @RequestMapping("/boardList") public void boardView(Model model) {
 * List<Board> board = bservice.allContent(); model.addAttribute("board",
 * board);
 * 
 * } }
 */