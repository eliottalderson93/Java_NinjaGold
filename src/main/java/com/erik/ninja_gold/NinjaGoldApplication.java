package com.erik.ninja_gold;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.http.HttpSession;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@SpringBootApplication
@Controller
public class NinjaGoldApplication {

	public static void main(String[] args) {
		SpringApplication.run(NinjaGoldApplication.class, args);
	}
	@RequestMapping("/")
	public String redirect() {
		return "redirect:/gold";
	}
	@SuppressWarnings("unchecked")
	@RequestMapping("/gold")
	public String render(HttpSession session, Model model) {
		ArrayList<String> activities = new ArrayList<String>();
		if (null == session.getAttribute("isInitialized")) {
			session.setAttribute("isInitialized", false);
		}
		if (session.getAttribute("isInitialized").equals(true)) { //has already been initialized
			ArrayList<String> seshAct = new ArrayList<String>();
			System.out.println(session.getAttribute("activityList"));
			seshAct = (ArrayList<String>)session.getAttribute("activityList");
			System.out.println(seshAct);
			System.out.println("-----loop---------");
			for(String str : seshAct) { //add the previous strings
				activities.add(str);
				System.out.println(str);
			}
			System.out.println("----session activity------");
			System.out.println((String)session.getAttribute("activity"));
			activities.add((String)session.getAttribute("activity")); //add the new string
			System.out.println("-------activities----------");
			System.out.println(activities);
			session.setAttribute("activityList", activities);
		}
		else { //initialize
			System.out.println("initializing");
			activities.add("Welcome to Ninja Gold!");
			session.setAttribute("isInitialized", true);
			session.setAttribute("gold", 0);
			session.setAttribute("activity", ""); //activity is the newest activity
		}
		//System.out.println("generating random numbers");
		int farmGold = ThreadLocalRandom.current().nextInt(10, 21);
		int caveGold = ThreadLocalRandom.current().nextInt(5, 11);
		int houseGold = ThreadLocalRandom.current().nextInt(2, 6);
		int casinoGold = ThreadLocalRandom.current().nextInt(-50, 51);
		model.addAttribute("farm",farmGold);
		model.addAttribute("cave",caveGold);
		model.addAttribute("house",houseGold);
		model.addAttribute("casino",casinoGold);
		model.addAttribute("activityList",activities);
		System.out.println("----end----");
		return "gold.jsp";
	}
	
	@PostMapping("/find")
	public String find(HttpSession session, @RequestParam(value="farm", required = false) String Farm, @RequestParam(value="house", required = false) String House, @RequestParam(value="casino", required = false) String Casino, @RequestParam(value="cave", required = false) String Cave) { //validation
		LocalTime this_time = LocalTime.now();
		String activity = "";
//		System.out.println(Farm);
//		System.out.println("----------");
		int curGold = Integer.valueOf(session.getAttribute("gold").toString());
		int addGold = 0;
		session.setAttribute("farm", Farm);
		session.setAttribute("cave", Cave);
		session.setAttribute("house", House);
		session.setAttribute("casino", Casino);
		session.setAttribute("timestamp",this_time);
		if (null != session.getAttribute("farm")) { //this one was posted
			activity = "You entered a farm and earned "+String.valueOf(session.getAttribute("farm"))+" gold. ("+ String.valueOf(session.getAttribute("timestamp"))+")";
			addGold = curGold+Integer.valueOf(Farm);
			session.setAttribute("gold", addGold);
		}
		if (null != session.getAttribute("cave")) {
			activity = "You entered a cave and earned "+String.valueOf(session.getAttribute("cave"))+" gold. ("+ String.valueOf(session.getAttribute("timestamp"))+")";
			addGold = curGold+Integer.valueOf(Cave);
			session.setAttribute("gold", addGold);		
		}
		if (null != session.getAttribute("house")) {
			activity = "You entered a house and earned "+String.valueOf(session.getAttribute("house"))+" gold. ("+ String.valueOf(session.getAttribute("timestamp"))+")";
			addGold = curGold+Integer.valueOf(House);
			session.setAttribute("gold", addGold);
		}
		if (null != session.getAttribute("casino")) {
			activity = "You entered a casino and earned "+String.valueOf(session.getAttribute("casino"))+" gold. ("+ String.valueOf(session.getAttribute("timestamp"))+")";
			addGold = curGold+Integer.valueOf(Casino);
			session.setAttribute("gold", addGold);
		}
		session.setAttribute("activity", activity);
//		System.out.print("Farm: ");
//		System.out.println(session.getAttribute("farm"));
//		System.out.print("Cave: ");
//		System.out.println(session.getAttribute("cave"));
//		System.out.print("House: ");
//		System.out.println(session.getAttribute("house"));
//		System.out.print("Casino: ");
//		System.out.println(session.getAttribute("casino"));
//		System.out.print("Timestamp: ");
//		System.out.println(session.getAttribute("timestamp"));
		return "redirect:/gold";
	}
	
	@GetMapping("/find")
	public String Redirect() {
		return "redirect:/gold";
	}
}
