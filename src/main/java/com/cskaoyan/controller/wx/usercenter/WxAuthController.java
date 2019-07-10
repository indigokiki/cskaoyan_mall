package com.cskaoyan.controller.wx.usercenter;

import com.cskaoyan.bean.*;
import com.cskaoyan.bean.wx.usercenter.UserFromWX;
import com.cskaoyan.mapper.CskaoyanMallOrderGoodsMapper;
import com.cskaoyan.mapper.CskaoyanMallOrderMapper;
import com.cskaoyan.mapper.CskaoyanMallUserMapper;
import com.cskaoyan.util.wxutil.BaseRespVo;
import com.cskaoyan.util.wxutil.UserInfo;
import com.cskaoyan.util.wxutil.UserToken;
import com.cskaoyan.util.wxutil.UserTokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
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

	@Autowired
	CskaoyanMallOrderGoodsMapper orderGoodsMapper;

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
	@Transactional(isolation = Isolation.SERIALIZABLE)
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

		/*
		//想了一下，下面该方法已经移动到了新增comment的方法里面了
		//每次请求index，都要更新一下order表中的comment，如果order中所有商品
		//都已经评论过，修改order中的comment使其不再出现在待评价状态
		CskaoyanMallOrderExample orderExample = new CskaoyanMallOrderExample();
		CskaoyanMallOrderExample.Criteria commentingCriteria = orderExample.createCriteria();
		commentingCriteria.andDeletedNotEqualTo(true).andOrderStatusGreaterThan((short)400).andUserIdEqualTo(userId);
		List<CskaoyanMallOrder> orderlist = cskaoyanMallOrderMapper.selectByExample(orderExample);
		for (CskaoyanMallOrder perorder : orderlist) {
			Integer orderid = perorder.getId();
			CskaoyanMallOrderGoodsExample orderGoodsExample = new CskaoyanMallOrderGoodsExample();
			CskaoyanMallOrderGoodsExample.Criteria criteria = orderGoodsExample.createCriteria();
			criteria.andDeletedNotEqualTo(true).andOrderIdEqualTo(orderid);
			List<CskaoyanMallOrderGoods> orderGoodsList = orderGoodsMapper.selectByExample(orderGoodsExample);
			//flag用于判断order对应的商品是否都已经评价过，如果一旦出现未评价的商品
			//则flag修改为false，否则flag为true
			boolean flag = true;
			for (CskaoyanMallOrderGoods orderGoods : orderGoodsList) {
				if(0 == orderGoods.getComment()){
					flag = false;
				}
			}
			//如果flag为true，则需要修改order中的comment，使其不再出现在待评价中
			if(true == flag){
				//这里暂时不清楚逻辑，设置为1使其不再被检索进待评价列表中
				perorder.setComments((short)1);
			}
			cskaoyanMallOrderMapper.updateByPrimaryKey(perorder);
		}*/


		//unpaid
		CskaoyanMallOrderExample cskaoyanMallOrderExample = new CskaoyanMallOrderExample();
		CskaoyanMallOrderExample.Criteria criteria = cskaoyanMallOrderExample.createCriteria();
		criteria.andOrderStatusEqualTo((short)101).andDeletedNotEqualTo(true).andUserIdEqualTo(userId);
		long unpaid = cskaoyanMallOrderMapper.countByExample(cskaoyanMallOrderExample);

		//unrecv
		CskaoyanMallOrderExample cskaoyanMallOrderExample1 = new CskaoyanMallOrderExample();
		CskaoyanMallOrderExample.Criteria criteria1 = cskaoyanMallOrderExample1.createCriteria();
		criteria1.andOrderStatusEqualTo((short)301).andDeletedNotEqualTo(true).andUserIdEqualTo(userId);
		long unrecv = cskaoyanMallOrderMapper.countByExample(cskaoyanMallOrderExample1);

		//unship
		//cskaoyanMallOrderExample.clear();
		//为啥这句话会出错？？
		CskaoyanMallOrderExample cskaoyanMallOrderExample2 = new CskaoyanMallOrderExample();
		CskaoyanMallOrderExample.Criteria criteria2 = cskaoyanMallOrderExample2.createCriteria();
		criteria2.andOrderStatusEqualTo((short)201).andDeletedNotEqualTo(true).andUserIdEqualTo(userId);
		long unship = cskaoyanMallOrderMapper.countByExample(cskaoyanMallOrderExample2);


		//uncomment
		CskaoyanMallOrderExample cskaoyanMallOrderExample3 = new CskaoyanMallOrderExample();
		CskaoyanMallOrderExample.Criteria criteria3 = cskaoyanMallOrderExample3.createCriteria();
		criteria3.andCommentsEqualTo((short)0).andOrderStatusGreaterThan((short)400).andDeletedNotEqualTo(true).andUserIdEqualTo(userId);
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
