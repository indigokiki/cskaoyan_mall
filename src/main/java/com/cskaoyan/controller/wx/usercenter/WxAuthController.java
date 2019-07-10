package com.cskaoyan.controller.wx.usercenter;

import com.cskaoyan.bean.CskaoyanMallOrderExample;
import com.cskaoyan.bean.CskaoyanMallUser;
import com.cskaoyan.bean.CskaoyanMallUserExample;
import com.cskaoyan.bean.wx.usercenter.UserFromWX;
import com.cskaoyan.mapper.CskaoyanMallOrderMapper;
import com.cskaoyan.mapper.CskaoyanMallUserMapper;
import com.cskaoyan.util.wxutil.BaseRespVo;
import com.cskaoyan.util.wxutil.UserInfo;
import com.cskaoyan.util.wxutil.UserToken;
import com.cskaoyan.util.wxutil.UserTokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by little Stone
 * Date 2019/7/8 Time 20:55
 */
@RestController
@RequestMapping("/wx")
public class WxAuthController {

	@Autowired
	CskaoyanMallUserMapper cskaoyanMallUserMapper;

	@Autowired
	CskaoyanMallOrderMapper cskaoyanMallOrderMapper;

	@RequestMapping("/auth/login")
	@ResponseBody
	public Object login(@RequestBody UserFromWX userFromWX, HttpServletRequest request) {


		//*******************************
		//根据username和password查询user信息
		//*******************************

		CskaoyanMallUserExample cskaoyanMallUserExample = new CskaoyanMallUserExample();
		CskaoyanMallUserExample.Criteria criteria = cskaoyanMallUserExample.createCriteria();
		criteria.andUsernameEqualTo(userFromWX.getUsername());
		List<CskaoyanMallUser> cskaoyanMallUsers = cskaoyanMallUserMapper.selectByExample(cskaoyanMallUserExample);
		if(cskaoyanMallUsers.size() == 0){
			return BaseRespVo.fail(402,"参数值不对");
		}
		CskaoyanMallUser cskaoyanMallUser = cskaoyanMallUsers.get(0);
		if(!userFromWX.getPassword().equals(cskaoyanMallUser.getPassword())){
			return BaseRespVo.fail(402,"参数值不对");
		}


		// userInfo
		UserInfo userInfo = new UserInfo();
		userInfo.setNickName(userFromWX.getUsername());
		//userInfo.setAvatarUrl(user.getAvatar());


		//********************************
		//根据获得userid
		int userId = cskaoyanMallUser.getId();
		//********************************
		// token
		UserToken userToken = UserTokenManager.generateToken(userId);

		Map<Object, Object> result = new HashMap<Object, Object>();
		result.put("token", userToken.getToken());
		result.put("tokenExpire", userToken.getExpireTime().toString());
		result.put("userInfo", userInfo);
		return BaseRespVo.ok(result);
	}

	@GetMapping("user/index")
	public Object list(HttpServletRequest request) {
		//前端写了一个token放在请求头中
		//*************************
		//获得请求头
		String tokenKey = request.getHeader("X-Litemall-Token");
		Integer userId = UserTokenManager.getUserId(tokenKey);
		//通过请求头获得userId，进而可以获得一切关于user的信息
		//**************************
		if (userId == null) {
			return BaseRespVo.fail();
		}

		Map<Object, Object> data = new HashMap<Object, Object>();


		//***********************************
		//根据userId查询订单信息
		Map<Object,Object> order = new HashMap<>();

		//unpaid
		CskaoyanMallOrderExample cskaoyanMallOrderExample = new CskaoyanMallOrderExample();
		CskaoyanMallOrderExample.Criteria criteria = cskaoyanMallOrderExample.createCriteria();
		criteria.andOrderStatusEqualTo((short)101).andDeletedNotEqualTo(true);
		long unpaid = cskaoyanMallOrderMapper.countByExample(cskaoyanMallOrderExample);

		//unrecv
		CskaoyanMallOrderExample cskaoyanMallOrderExample1 = new CskaoyanMallOrderExample();
		CskaoyanMallOrderExample.Criteria criteria1 = cskaoyanMallOrderExample1.createCriteria();
		criteria1.andOrderStatusEqualTo((short)301).andDeletedNotEqualTo(true);
		long unrecv = cskaoyanMallOrderMapper.countByExample(cskaoyanMallOrderExample1);

		//unship
		//cskaoyanMallOrderExample.clear();
		//为啥这句话会出错？？
		CskaoyanMallOrderExample cskaoyanMallOrderExample2 = new CskaoyanMallOrderExample();
		CskaoyanMallOrderExample.Criteria criteria2 = cskaoyanMallOrderExample2.createCriteria();
		criteria2.andOrderStatusEqualTo((short)201).andDeletedNotEqualTo(true);
		long unship = cskaoyanMallOrderMapper.countByExample(cskaoyanMallOrderExample2);


		//uncomment
		CskaoyanMallOrderExample cskaoyanMallOrderExample3 = new CskaoyanMallOrderExample();
		CskaoyanMallOrderExample.Criteria criteria3 = cskaoyanMallOrderExample3.createCriteria();
		criteria3.andCommentsIsNull().andOrderStatusGreaterThan((short)400).andDeletedNotEqualTo(true);
		long uncomment = cskaoyanMallOrderMapper.countByExample(cskaoyanMallOrderExample3);

		order.put("uncomment",uncomment);
		order.put("unpaid",unpaid);
		order.put("unrecv",unrecv);
		order.put("unship",unship);
		data.put("order", order);
		//***********************************

		return BaseRespVo.ok(data);
	}

	@RequestMapping("/auth/logout")
	public BaseRespVo logout(){
		return BaseRespVo.ok(null);
	}
}
