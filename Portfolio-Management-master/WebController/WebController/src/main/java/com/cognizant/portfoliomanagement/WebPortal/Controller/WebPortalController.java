package com.cognizant.portfoliomanagement.WebPortal.Controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpRequest;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cognizant.portfoliomanagement.WebPortal.WebPortalApplication;
import com.cognizant.portfoliomanagement.WebPortal.FeignClient.CalculateNetworthFeignClient;
import com.cognizant.portfoliomanagement.WebPortal.Model.Asset;
import com.cognizant.portfoliomanagement.WebPortal.Model.AssetDetails;
import com.cognizant.portfoliomanagement.WebPortal.Model.SellObjectMap;
import com.cognizant.portfoliomanagement.WebPortal.Model.UserData;
import com.cognizant.portfoliomanagement.WebPortal.service.WebportalService;



@RestController
public class WebPortalController {

	@Autowired
	UserData admin;
	
	
	private static List<String> revokedTokens=new ArrayList<String>();

	private static Logger LOGGER = LoggerFactory.getLogger(WebPortalApplication.class);

	@Autowired
	WebportalService webportalService;
	
	@RequestMapping(path = "/logout", method = RequestMethod.GET)
	public ModelAndView getLogout(HttpSession session) {
		if (session != null && (String) session.getAttribute("token") != null
				&& webportalService.isSessionValid((String) session.getAttribute("token"))) {
			revokedTokens.add((String) session.getAttribute("token"));
			return new ModelAndView("login");
		}
		return new ModelAndView("Home");
	}
	
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public ModelAndView getLogin(HttpSession session) {
		LOGGER.info("Starting getLogin");
		if (session != null && (String) session.getAttribute("token") != null
				&& webportalService.isSessionValid((String) session.getAttribute("token"))&&!revokedTokens.contains((String) session.getAttribute("token"))) {
			LOGGER.info("Ending getLogin");
			return new ModelAndView("Home");
		}
		LOGGER.info("Ending getLogin");
		return new ModelAndView("login");
	}

	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public <user> ModelAndView postLogin(HttpSession session, ModelMap model, @ModelAttribute UserData user, ModelMap warning) {
		LOGGER.info("Starting postLogin");
		LOGGER.info("Ending postLogin");
		return new ModelAndView(webportalService.postLogin(user, session, warning));
	}
	
	@GetMapping("/Home")
	public ModelAndView getHomePage(HttpSession session) {
		LOGGER.info("Starting getHomePage");
		if (webportalService.isSessionValid((String) session.getAttribute("token"))&&!revokedTokens.contains((String) session.getAttribute("token"))) {
			LOGGER.info("Ending getHomePage");
			return new ModelAndView("Home");
		}
		LOGGER.info("Ending getHomePage");
		return new ModelAndView("login");
	}
	
	@Autowired
	private CalculateNetworthFeignClient networthFeignClient;

	
	@RequestMapping(value="/sellAssets", method = RequestMethod.GET)
	public ModelAndView showSupplyPage(HttpSession session,ModelMap model){
		LOGGER.info("Starting showSellAssets");
		if (webportalService.isSessionValid((String) session.getAttribute("token"))&&!revokedTokens.contains((String) session.getAttribute("token"))) {
			LOGGER.info("Ending showSellAssets");
			String s=(String)session.getAttribute("memberId");
			int i=Integer.parseInt(s);
			String token = (String)session.getAttribute("token");
			List<AssetDetails> Asset=new ArrayList<AssetDetails>();
			try {
				
			
			Asset=networthFeignClient.getAllAssets(token,i);
			} catch (Exception e) {
				System.out.println("\n\n\n\nerror\n\n");
			}
			//LOGGER.info("----------------------------------------------------------------------------"+Asset);
			model.put("Asset",Asset);
			return new ModelAndView("sellAssets");
		}
		LOGGER.info("Ending showSellAssets");
		return new ModelAndView("login");
	}
		
	@RequestMapping(value ="/viewNetworth", method = RequestMethod.GET)
	public ModelAndView showNetworth(HttpSession session,ModelMap model) {
		LOGGER.info("Starting viewNetworth");
		if (webportalService.isSessionValid((String) session.getAttribute("token"))&&!revokedTokens.contains((String) session.getAttribute("token"))) {
			LOGGER.info("Ending viewNetworth");
			String s=(String)session.getAttribute("memberId");
			int i=Integer.parseInt(s);
			String token = (String)session.getAttribute("token");
			model.put("networth",networthFeignClient.calculateNetworth(token,i));
			return new ModelAndView("viewNetworth");
		}
		LOGGER.info("Ending viewNetworth");
		return new ModelAndView("login");
	}

	
	@RequestMapping(path = "/viewNetworth", method = RequestMethod.POST)
	public <user> ModelAndView sellAssetsSelected(HttpSession session,@RequestParam("selected") String[] name,@RequestParam("quantity") String[] count,ModelMap model) {
		LOGGER.info("Starting postLogin");
		LOGGER.info("Ending postLogin");
		String s=(String)session.getAttribute("memberId");
		int i=Integer.parseInt(s);
		String token = (String)session.getAttribute("token");
		List<AssetDetails> list=new ArrayList<AssetDetails>();
		list=networthFeignClient.getAllAssets(token,i);
		
			SellObjectMap sell=webportalService.sellShares(list,i,name,count);
		Map<String,String> AssetMap=webportalService.convertToMap(name,count);
		double networth=networthFeignClient.sellPortfolioAssets(token,sell);
		model.put("networth",networthFeignClient.calculateNetworth(token,i));
		model.addAttribute("AssetMap",AssetMap);
		return new ModelAndView("viewNetworth");
	}
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }	
}
