//package com.cskaoyan.controller.util;
//
//
//import com.cskaoyan.bean.popularize.User;
//import com.cskaoyan.bean.popularize.UserExample;
//import com.cskaoyan.mapper.popularize.UserMapper;
//import com.google.gson.Gson;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * Created by little Stone
// * Date 2019/7/8 Time 20:55
// */
//@RestController
//@RequestMapping("/wx")
//public class WxAuthController {
///*
//
//	@Autowired
//    UserMapper userMapper;
//*/
//
//	/*@RequestMapping("/auth/login")
//	@ResponseBody
//	public Object login(@RequestBody String body, HttpServletRequest request) {
//		Gson gson = new Gson();
//		Map map = gson.fromJson(body, Map.class);
//		String username = (String) map.get("username");
//		String password = (String) map.get("password");
//
//		//*******************************
//		//根据username和password查询user信息
//		//*******************************
//		UserExample userExample = new UserExample();
//		userExample.createCriteria().andUsernameEqualTo(username).andPasswordEqualTo(password);
//		List<User> users = userMapper.selectByExample(userExample);
//		if(users==null||users.size()==0){
//			throw new RuntimeException("用户名或密码不正确");
//		}
//
//		// userInfo
//		UserInfo userInfo = new UserInfo();
//		userInfo.setNickName(username);
//		//userInfo.setAvatarUrl(user.getAvatar());
//
//
//		//********************************
//		//根据获得userid
//		int userId = users.get(0).getId();
//
//		//********************************
//		// token
//		UserToken userToken = UserTokenManager.generateToken(userId);
//		Map<Object, Object> result = new HashMap<Object, Object>();
//		result.put("token", userToken.getToken());
//		result.put("tokenExpire", userToken.getExpireTime().toString());
//		result.put("userInfo", userInfo);
//		return BaseRespVo.ok(result);
//	}
//*/
///*	@GetMapping("user/index")
//	public Object list(HttpServletRequest request) {
//		//前端写了一个token放在请求头中
//		//*************************
//		//获得请求头
//
//		String tokenKey = request.getHeader("X-Litemall-Token");
//		Integer userId = UserTokenManager.getUserId(tokenKey);
//		//通过请求头获得userId，进而可以获得一切关于user的信息
//		//**************************
//		if (userId == null) {
//			return BaseRespVo.fail();
//		}
//
//		Map<Object, Object> data = new HashMap<Object, Object>();
//		//***********************************
//		//根据userId查询订单信息
//		data.put("order", null);
//		//***********************************
//
//		return BaseRespVo.ok(data);
//	}*/
//}
